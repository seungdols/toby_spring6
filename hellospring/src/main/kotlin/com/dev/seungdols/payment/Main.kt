package com.dev.seungdols.payment

import com.dev.seungdols.payment.service.PaymentService
import com.dev.seungdols.payment.service.WebApiExRateProvider
import java.math.BigDecimal

fun main() {
  val paymentService = PaymentService(WebApiExRateProvider())
  val payment = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment)
}
