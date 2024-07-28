package com.dev.seungdols.payment.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class PaymentServiceTest : AnnotationSpec() {
  val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

  @Test
  fun prepare() {
    testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000), this.clock)
    testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000), this.clock)
    testAmount(BigDecimal.valueOf(3_000), BigDecimal.valueOf(30_000), this.clock)
  }

  @Test
  fun validUntil() {
    val paymentService = PaymentService(ExRateProviderStub(BigDecimal(1_000)), this.clock)

    val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

    val now = LocalDateTime.now(clock)
    payment.validUntil shouldBe now.plusMinutes(30)
  }

  private fun testAmount(
    exRate: BigDecimal,
    convertedAmount: BigDecimal,
    clock: Clock,
  ) {
    val paymentService = PaymentService(ExRateProviderStub(exRate), clock)

    val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

    payment.exchangeRate shouldBe exRate
    payment.convertedAmount shouldBe convertedAmount
  }
}
