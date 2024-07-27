package com.dev.seungdols.payment.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectFactory {
  @Bean
  fun paymentService(): PaymentService {
    return PaymentService(cachedExRateProvider())
  }

  @Bean
  fun cachedExRateProvider(): ExRateProvider {
    return CachedExRateProvider(exRateProvider())
  }

  @Bean
  fun exRateProvider(): ExRateProvider {
    return WebApiExRateProvider()
  }
}
