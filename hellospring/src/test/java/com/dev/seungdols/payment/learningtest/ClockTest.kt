package com.dev.seungdols.payment.learningtest

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.date.shouldBeAfter
import io.kotest.matchers.shouldBe
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class ClockTest : AnnotationSpec() {
  @Test
  fun `clock Test`() {
    val clock = Clock.systemDefaultZone()

    val dt1 = LocalDateTime.now(clock)
    val dt2 = LocalDateTime.now(clock)

    dt2 shouldBeAfter dt1
  }

  @Test
  fun `fixedClock`() {
    val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

    val dt1 = LocalDateTime.now(clock)
    val dt2 = LocalDateTime.now(clock)

    dt1 shouldBe dt2
  }
}
