package com.dev.seungdols.payment.service

import com.dev.seungdols.payment.vo.Payment
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

class PaymentService(
  private val exRateProvider: ExRateProvider,
  private val clock: Clock,
) {
  fun prepare(
    orderId: Long,
    currency: String,
    foreignCurrencyAmount: BigDecimal,
  ): Payment {
    val exchangeRate = exRateProvider.getExRate(currency)
    val convertedAmount = foreignCurrencyAmount.multiply(exchangeRate)
    val validUntil = LocalDateTime.now(clock).plusMinutes(30)

    return Payment(orderId, currency, foreignCurrencyAmount, exchangeRate, convertedAmount, validUntil)
  }
}
