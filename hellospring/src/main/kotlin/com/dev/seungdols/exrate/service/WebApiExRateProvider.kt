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
import java.net.URI
import java.net.URL

class WebApiExRateProvider : ExRateProvider {
  private val log = KotlinLogging.logger {}

  override fun getExRate(currency: String): BigDecimal {
    // 환율 가져오기 https://api.exchangerate-api.com/v4/latest/USD
    val url = URI("https://open.er-api.com/v6/latest/$currency").toURL()
    val response =
      executeApi(url)
    val exRateData = parseExRate(response).first()
    // 금액 계산
    val exchangeRate = exRateData.rates[currency]

    log.info { "환율 정보: $exchangeRate" }

    requireNotNull(exchangeRate) { "환율 정보가 없습니다." }
    return exchangeRate
  }

  private fun executeApi(url: URL): String {
    val httpURLConnection = url.openConnection() as HttpURLConnection
    return httpURLConnection.inputStream.bufferedReader().use {
      it.readLines().toString()
    }
  }

  private fun parseExRate(response: String): List<ExRateData> {
    val objectMapper = ObjectMapper()
    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    objectMapper.registerKotlinModule()
    return objectMapper.readValue(response, object : TypeReference<List<ExRateData>>() {})
  }
}
