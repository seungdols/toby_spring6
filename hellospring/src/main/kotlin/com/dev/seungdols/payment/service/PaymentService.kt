package com.dev.seungdols.payment.service

import com.dev.seungdols.payment.vo.ExRateData
import com.dev.seungdols.payment.vo.Payment
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL
import java.time.LocalDateTime

class PaymentService {
  fun prepare(
    orderId: Long,
    currency: String,
    foreignCurrencyAmount: BigDecimal,
  ): Payment {
    // 환율 가져오기 https://api.exchangerate-api.com/v4/latest/USD
    val url = URL("https://open.er-api.com/v6/latest/$currency")
    val httpURLConnection = url.openConnection() as HttpURLConnection
    val exRateData =
      httpURLConnection.inputStream.bufferedReader().use {
        val message = it.readLines().toString()

        val objectMapper = ObjectMapper()
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        objectMapper.registerKotlinModule()
        objectMapper.readValue(message, object : TypeReference<List<ExRateData>>() {})
      }.first()
    // 금액 계산
    val exchangeRate = exRateData.rates["KRW"]
    requireNotNull(exchangeRate) { "환율 정보가 없습니다." }
    val convertedAmount = foreignCurrencyAmount.multiply(exchangeRate)
    // 유효 시간 계산
    val validUntil = LocalDateTime.now().plusMinutes(30)
    return Payment(orderId, currency, foreignCurrencyAmount, exchangeRate, convertedAmount, validUntil)
  }
}

fun main() {
  val paymentService = PaymentService()
  val payment = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment)
}
