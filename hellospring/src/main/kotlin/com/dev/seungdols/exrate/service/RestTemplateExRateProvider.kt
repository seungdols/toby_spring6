package com.dev.seungdols.exrate.service

import com.dev.seungdols.exrate.vo.ExRateData
import com.dev.seungdols.payment.service.ExRateProvider
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal

class RestTemplateExRateProvider(
  private val restTemplate: RestTemplate,
) : ExRateProvider {
  override fun getExRate(currency: String): BigDecimal {
    val url = "https://open.er-api.com/v6/latest/$currency"
    val response = restTemplate.getForObject(url, ExRateData::class.java)
    return response?.rates?.get("KRW") ?: throw IllegalArgumentException("Unsupported currency: $currency")
  }
}
