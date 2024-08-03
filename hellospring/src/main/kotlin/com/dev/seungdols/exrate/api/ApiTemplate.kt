package com.dev.seungdols.exrate.api

import com.fasterxml.jackson.core.JsonProcessingException
import io.github.oshai.kotlinlogging.KotlinLogging
import java.io.IOException
import java.math.BigDecimal
import java.net.URI
import java.net.URISyntaxException

class ApiTemplate(
  private val apiExecutor: ApiExecutor,
  private val exRateExtractor: ExRateExtractor,
) {
  private val log = KotlinLogging.logger {}

  fun getForExRate(url: String): BigDecimal {
    return getForExRate(apiExecutor, exRateExtractor, url)
  }

  fun getForExRate(
    apiExecutor: ApiExecutor,
    url: String,
  ): BigDecimal {
    return getForExRate(apiExecutor, exRateExtractor, url)
  }

  fun getForExRate(
    exRateExtractor: ExRateExtractor,
    url: String,
  ): BigDecimal {
    return getForExRate(apiExecutor, exRateExtractor, url)
  }

  private fun getForExRate(
    apiExecutor: ApiExecutor,
    exRateExtractor: ExRateExtractor,
    url: String,
  ): BigDecimal {
    val uri =
      try {
        URI(url)
      } catch (e: URISyntaxException) {
        throw RuntimeException(e)
      }

    val response =
      try {
        apiExecutor.execute(uri)
      } catch (e: IOException) {
        throw RuntimeException(e)
      }

    val exchangeRate =
      try {
        exRateExtractor.extract(response)
      } catch (e: JsonProcessingException) {
        throw RuntimeException(e)
      }

    log.info { "환율 정보: $exchangeRate" }

    return exchangeRate
  }
}
