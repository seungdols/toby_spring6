package com.dev.seungdols.exrate.service

import com.dev.seungdols.exrate.api.ApiExecutor
import com.dev.seungdols.exrate.api.ErApiExRateExtractor
import com.dev.seungdols.exrate.api.ExRateExtractor
import com.dev.seungdols.exrate.api.SimpleApiExecutor
import com.dev.seungdols.payment.service.ExRateProvider
import com.fasterxml.jackson.core.JsonProcessingException
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
    return runApiForExRate(SimpleApiExecutor(), ErApiExRateExtractor(), url, currency)
  }

  private fun runApiForExRate(
    apiExecutor: ApiExecutor,
    exRateExtractor: ExRateExtractor,
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

    val exchangeRate =
      try {
        exRateExtractor.extract(response, currency)
      } catch (e: JsonProcessingException) {
        throw RuntimeException(e)
      }

    log.info { "환율 정보: $exchangeRate" }

    return exchangeRate
  }
}
