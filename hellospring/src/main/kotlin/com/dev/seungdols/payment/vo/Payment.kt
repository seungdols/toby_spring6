package com.dev.seungdols.payment.vo

import java.math.BigDecimal
import java.time.LocalDateTime

data class Payment(
  val orderId: Long,
  val currency: String,
  val foreignCurrencyAmount: BigDecimal,
  val exchangeRate: BigDecimal,
  val convertedAmount: BigDecimal,
  val validUntil: LocalDateTime
)
