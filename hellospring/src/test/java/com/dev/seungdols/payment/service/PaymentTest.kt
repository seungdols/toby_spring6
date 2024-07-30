package com.dev.seungdols.payment.service

import com.dev.seungdols.payment.vo.Payment
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.time.Clock
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

class PaymentTest : AnnotationSpec() {
  @Test
  fun `Payment 도메인의 createPrepared 메소드 테스트`() {
    val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

    val payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN, BigDecimal(1_000), LocalDateTime.now(clock))

    payment.exchangeRate shouldBe BigDecimal(1_000)
    payment.convertedAmount shouldBe BigDecimal(10_000)
    payment.validUntil shouldBe LocalDateTime.now(clock).plusMinutes(30)
  }

  @Test
  fun `isValid 테스트`() {
    val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())
    val payment = Payment.createPrepared(1L, "USD", BigDecimal.TEN, BigDecimal(1_000), LocalDateTime.now(clock))

    payment.isValid(clock).shouldBeTrue()
    payment.isValid(Clock.offset(clock, Duration.of(30, ChronoUnit.MINUTES))).shouldBeFalse()
  }
}
