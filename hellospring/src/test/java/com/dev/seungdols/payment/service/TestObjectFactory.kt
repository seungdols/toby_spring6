package com.dev.seungdols.payment.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.BigDecimal

@Configuration
class TestObjectFactory {
  @Bean
  fun paymentService(): PaymentService {
    return PaymentService(exRateProvider())
  }

  @Bean
  fun exRateProvider(): ExRateProvider {
    return ExRateProviderStub(BigDecimal(1_000))
  }
}
