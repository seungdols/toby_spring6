package com.dev.seungdols.payment.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestPaymentConfig::class])
class PaymentServiceSpringTest(
  val paymentService: PaymentService,
  val clock: Clock,
) : AnnotationSpec() {
  @Test
  fun `paymentService의 prepare 메소드 테스트`() {
    val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)
    payment.exchangeRate shouldBe BigDecimal(1_000)
    payment.convertedAmount shouldBe BigDecimal(10_000)
  }

  @Test
  fun validUntil() {
    val paymentService = PaymentService(ExRateProviderStub(BigDecimal(1_000)), this.clock)

    val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)

    val now = LocalDateTime.now(clock)
    payment.validUntil shouldBe now.plusMinutes(30)
  }
}
