package com.dev.seungdols.payment.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

@Configuration
class TestPaymentConfig {
  @Bean
  fun paymentService(): PaymentService {
    return PaymentService(exRateProvider(), clock())
  }

  @Bean
  fun exRateProvider(): ExRateProvider {
    return ExRateProviderStub(BigDecimal(1_000))
  }

  @Bean
  fun clock(): Clock {
    return Clock.fixed(Instant.now(), ZoneId.systemDefault())
  }
}
