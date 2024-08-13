package com.dev.seungdols

import com.dev.seungdols.config.DataConfig
import com.dev.seungdols.data.OrderRepository
import com.dev.seungdols.order.Order
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

fun main(args: Array<String>) {
  val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
  val orderRepository = beanFactory.getBean(OrderRepository::class.java)

  val order = Order(no = "2021-0001", totalAmount = BigDecimal("100.00"))
  orderRepository.save(order)

  println("Order saved: $order")
}
