package com.dev.seungdols.payment

import com.dev.seungdols.payment.service.ObjectFactory
import com.dev.seungdols.payment.service.PaymentService
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

fun main(args: Array<String>) {
  val beanFactory = AnnotationConfigApplicationContext(ObjectFactory::class.java)
  val paymentService = beanFactory.getBean(PaymentService::class.java)

  val payment = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment)
}
