package com.dev.seungdols.exrate.service

import com.dev.seungdols.payment.service.ExRateProvider
import java.math.BigDecimal

class SimpleExRateProvider : ExRateProvider {
  override fun getExRate(currency: String): BigDecimal {
    if (currency === "USD") {
      return BigDecimal("1000")
    }

    throw IllegalArgumentException("Unsupported currency: $currency")
  }
}
