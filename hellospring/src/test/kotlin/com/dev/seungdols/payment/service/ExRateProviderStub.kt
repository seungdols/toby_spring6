package com.dev.seungdols.payment.service

import java.math.BigDecimal

class ExRateProviderStub(
  private val exRate: BigDecimal,
) : ExRateProvider {
  override fun getExRate(currency: String): BigDecimal {
    return exRate
  }
}
