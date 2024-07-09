package com.dev.seungdols.payment

import com.dev.seungdols.payment.service.PaymentService
import java.math.BigDecimal

fun main() {
  val paymentService = PaymentService()
  val payment = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment)
}
