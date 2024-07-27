package com.dev.seungdols.exrate.service

import com.dev.seungdols.exrate.vo.ExRateData
import com.dev.seungdols.payment.service.ExRateProvider
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import java.net.HttpURLConnection
import java.net.URL

class WebApiExRateProvider : ExRateProvider {
  val log = KotlinLogging.logger {}

  override fun getExRate(currency: String): BigDecimal {
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

    log.info { "환율 정보: $exchangeRate" }

    requireNotNull(exchangeRate) { "환율 정보가 없습니다." }
    return exchangeRate
  }
}
