package com.dev.seungdols.payment.service

import com.dev.seungdols.payment.vo.Payment

class PaymentService {
  fun prepare(): Payment {
    return Payment()
  }
}

fun main() {
  val paymentService = PaymentService()
  val payment = paymentService.prepare()
  println(payment)
}
