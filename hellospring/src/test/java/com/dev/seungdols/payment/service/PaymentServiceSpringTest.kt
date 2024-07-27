package com.dev.seungdols.payment.service

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [TestObjectFactory::class])
class PaymentServiceSpringTest(
  val paymentService: PaymentService,
) : AnnotationSpec() {
  @Test
  fun `paymentService의 prepare 메소드 테스트`() {
    val payment = paymentService.prepare(1L, "USD", BigDecimal.TEN)
    payment.exchangeRate shouldBe BigDecimal(1_000)
    payment.convertedAmount shouldBe BigDecimal(10_000)
  }
}
