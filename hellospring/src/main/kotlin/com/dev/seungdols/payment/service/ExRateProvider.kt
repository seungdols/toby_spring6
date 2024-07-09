package com.dev.seungdols.payment.service

import java.math.BigDecimal

interface ExRateProvider {
  fun getExRate(currency: String): BigDecimal
}
