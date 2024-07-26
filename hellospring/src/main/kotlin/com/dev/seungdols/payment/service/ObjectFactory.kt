package com.dev.seungdols.payment.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectFactory {
  @Bean
  fun paymentService(): PaymentService {
    return PaymentService(exRateProvider())
  }

  @Bean
  fun exRateProvider(): ExRateProvider {
    return WebApiExRateProvider()
  }
}
