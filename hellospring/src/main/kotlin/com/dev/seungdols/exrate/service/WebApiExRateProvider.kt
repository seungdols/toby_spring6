package com.dev.seungdols.exrate.service

import com.dev.seungdols.exrate.api.ApiTemplate
import com.dev.seungdols.payment.service.ExRateProvider
import java.math.BigDecimal

class WebApiExRateProvider(
  private val apiTemplate: ApiTemplate,
) : ExRateProvider {
  override fun getExRate(currency: String): BigDecimal {
    // 환율 가져오기 https://api.exchangerate-api.com/v4/latest/USD
    val url = "https://open.er-api.com/v6/latest/$currency"
    return apiTemplate.getForExRate(url)
  }
}
