package com.dev.seungdols.exrate.service

import com.dev.seungdols.exrate.api.ApiExecutor
import com.dev.seungdols.exrate.api.SimpleApiExecutor
import com.dev.seungdols.exrate.vo.ExRateData
import com.dev.seungdols.payment.service.ExRateProvider
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.math.BigDecimal
import java.net.URI
import java.net.URISyntaxException

class WebApiExRateProvider : ExRateProvider {
  private val log = KotlinLogging.logger {}

  override fun getExRate(currency: String): BigDecimal {
    // 환율 가져오기 https://api.exchangerate-api.com/v4/latest/USD
    val url = "https://open.er-api.com/v6/latest/$currency"
    return runApiForExRate(SimpleApiExecutor(), url, currency)
  }

  private fun runApiForExRate(
    apiExecutor: ApiExecutor,
    url: String,
    currency: String,
  ): BigDecimal {
    val uri =
      try {
        URI(url)
      } catch (e: URISyntaxException) {
        throw RuntimeException(e)
      }

    val response =
      try {
        apiExecutor.execute(uri.toURL())
      } catch (e: IOException) {
        throw RuntimeException(e)
      }

    val exRateData =
      try {
        extractExRate(response)
      } catch (e: JsonProcessingException) {
        throw RuntimeException(e)
      }
    // 금액 계산
    val exchangeRate = exRateData.rates[currency]

    log.info { "환율 정보: $exchangeRate" }

    requireNotNull(exchangeRate) { "환율 정보가 없습니다." }
    return exchangeRate
  }

  private fun extractExRate(response: String): ExRateData {
    val objectMapper = ObjectMapper()
    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    objectMapper.registerKotlinModule()
    return objectMapper.readValue(response, object : TypeReference<List<ExRateData>>() {}).first()
  }
}
