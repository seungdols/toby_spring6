package com.dev.seungdols.payment.service

class ObjectFactory {
  fun paymentService(): PaymentService {
    return PaymentService(exRateProvider())
  }

  fun exRateProvider(): ExRateProvider {
    return WebApiExRateProvider()
  }
}
