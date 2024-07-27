package com.dev.seungdols.exrate.vo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class ExRateData(
  val result: String,
  val rates: Map<String, BigDecimal>,
)
