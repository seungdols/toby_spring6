package com.dev.seungdols.exrate.api

import java.math.BigDecimal

interface ExRateExtractor {
  fun extract(
    response: String,
    currency: String,
  ): BigDecimal
}
