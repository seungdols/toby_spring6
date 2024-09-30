package com.dev.seungdols

import com.dev.seungdols.config.OrderConfig
import com.dev.seungdols.data.OrderRepository
import com.dev.seungdols.order.OrderService
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.orm.jpa.JpaTransactionManager
import java.math.BigDecimal

fun main(args: Array<String>) {
  val beanFactory = AnnotationConfigApplicationContext(OrderConfig::class.java)
  val orderRepository = beanFactory.getBean(OrderRepository::class.java)
  val jpaTransactionManager = beanFactory.getBean(JpaTransactionManager::class.java)

  val orderService = OrderService(orderRepository, jpaTransactionManager)
  orderService.createOrder(no = "100", BigDecimal("100.00"))
}
