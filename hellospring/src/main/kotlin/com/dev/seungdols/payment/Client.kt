package com.dev.seungdols.payment

import com.dev.seungdols.payment.service.ObjectFactory
import com.dev.seungdols.payment.service.PaymentService
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
  val beanFactory = AnnotationConfigApplicationContext(ObjectFactory::class.java)
  val paymentService = beanFactory.getBean(PaymentService::class.java)

  val payment1 = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment1)

  val payment2 = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment2)

  TimeUnit.SECONDS.sleep(3)

  val payment3 = paymentService.prepare(100L, "USD", BigDecimal("100.00"))
  println(payment3)
}
