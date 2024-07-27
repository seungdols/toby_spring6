package com.dev.seungdols.payment.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class PaymentServiceTest : AnnotationSpec() {
  @Test
  fun prepare() {
    testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000))
    testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000))
    testAmount(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000))
  }

  private fun testAmount(
    exRate: BigDecimal,
    convertedAmount: BigDecimal,
  ) {
    val paymentService = PaymentService(ExRateProviderStub(exRate))

    val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

    payment.exchangeRate shouldBe exRate
    payment.convertedAmount shouldBe convertedAmount
  }
}
