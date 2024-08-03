package com.dev.seungdols.exrate.service

import com.dev.seungdols.exrate.api.ApiTemplate
import com.dev.seungdols.exrate.api.ErApiExRateExtractor
import com.dev.seungdols.exrate.api.HttpClientExecutor
import com.dev.seungdols.payment.service.ExRateProvider
import com.dev.seungdols.payment.service.PaymentService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Clock

@Configuration
class PaymentConfig {
  @Bean
  fun paymentService(): PaymentService {
    return PaymentService(cachedExRateProvider(), clock())
  }

  @Bean
  fun cachedExRateProvider(): ExRateProvider {
    return CachedExRateProvider(exRateProvider())
  }

  @Bean
  fun apiTemplate(): ApiTemplate {
    return ApiTemplate(HttpClientExecutor(), ErApiExRateExtractor())
  }

  @Bean
  fun restTemplate(): RestTemplate {
    return RestTemplate()
  }

  @Bean
  fun exRateProvider(): ExRateProvider {
    return RestTemplateExRateProvider(restTemplate())
  }

  @Bean
  fun clock(): Clock {
    return Clock.systemDefaultZone()
  }
}
