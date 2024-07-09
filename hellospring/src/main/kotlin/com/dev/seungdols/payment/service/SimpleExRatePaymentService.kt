package com.dev.seungdols.payment.service

import java.math.BigDecimal

class SimpleExRatePaymentService : PaymentService() {
  override fun getExRate(currency: String): BigDecimal {
    if (currency === "USD") {
      return BigDecimal("1000")
    }

    throw IllegalArgumentException("Unsupported currency: $currency")
  }
}
