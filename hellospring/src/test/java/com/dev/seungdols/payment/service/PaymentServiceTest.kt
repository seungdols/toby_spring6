package com.dev.seungdols.payment.service

import com.dev.seungdols.exrate.service.WebApiExRateProvider
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.time.LocalDateTime

class PaymentServiceTest : AnnotationSpec() {
  @Test
  fun prepare() {
    val paymentService = PaymentService(WebApiExRateProvider())

    val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

    payment.exchangeRate.shouldNotBeNull()

    payment.convertedAmount shouldBe payment.exchangeRate.multiply(payment.foreignCurrencyAmount)

    payment.validUntil.shouldBeAfter(LocalDateTime.now())
  }
}
