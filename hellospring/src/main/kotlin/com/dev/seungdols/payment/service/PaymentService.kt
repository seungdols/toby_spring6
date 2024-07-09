package com.dev.seungdols.payment.service

import com.dev.seungdols.payment.vo.Payment
import java.math.BigDecimal
import java.time.LocalDateTime

abstract class PaymentService {
  fun prepare(
    orderId: Long,
    currency: String,
    foreignCurrencyAmount: BigDecimal,
  ): Payment {
    val exchangeRate = getExRate(currency)
    val convertedAmount = foreignCurrencyAmount.multiply(exchangeRate)
    val validUntil = LocalDateTime.now().plusMinutes(30)

    return Payment(orderId, currency, foreignCurrencyAmount, exchangeRate, convertedAmount, validUntil)
  }

  abstract fun getExRate(currency: String): BigDecimal
}
