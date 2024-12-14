package com.dev.seungdols

import com.dev.seungdols.config.OrderConfig
import com.dev.seungdols.order.OrderService
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

fun main(args: Array<String>) {
  val beanFactory = AnnotationConfigApplicationContext(OrderConfig::class.java)
  val orderService = beanFactory.getBean(OrderService::class.java)

  val order = orderService.createOrder(no = "100", BigDecimal("100.00"))
  println(order)
}
