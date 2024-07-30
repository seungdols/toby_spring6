package com.dev.seungdols.payment.vo

import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

data class Payment(
  val orderId: Long,
  val currency: String,
  val foreignCurrencyAmount: BigDecimal,
  val exchangeRate: BigDecimal,
  val convertedAmount: BigDecimal,
  val validUntil: LocalDateTime,
) {
  fun isValid(clock: Clock): Boolean {
    return LocalDateTime.now(clock).isBefore(validUntil)
  }

  companion object {
    fun createPrepared(
      orderId: Long,
      currency: String,
      foreignCurrencyAmount: BigDecimal,
      exchangeRate: BigDecimal,
      now: LocalDateTime,
    ): Payment {
      val convertedAmount = foreignCurrencyAmount.multiply(exchangeRate)
      val validUntil = now.plusMinutes(30)

      return Payment(
        orderId,
        currency,
        foreignCurrencyAmount,
        exchangeRate,
        convertedAmount,
        validUntil,
      )
    }
  }
}
