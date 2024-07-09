package com.dev.seungdols.payment

import com.dev.seungdols.payment.service.ObjectFactory
import java.math.BigDecimal

fun main() {
  val objectFactory = ObjectFactory()
  val paymentService = objectFactory.paymentService()
  val payment = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment)
}
