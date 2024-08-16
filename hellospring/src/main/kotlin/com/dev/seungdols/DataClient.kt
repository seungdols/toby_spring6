package com.dev.seungdols

import com.dev.seungdols.config.DataConfig
import com.dev.seungdols.data.OrderRepository
import com.dev.seungdols.order.Order
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

fun main(args: Array<String>) {
  val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
  val orderRepository = beanFactory.getBean(OrderRepository::class.java)
  val jpaTransactionManager = beanFactory.getBean(JpaTransactionManager::class.java)
  try {
    TransactionTemplate(jpaTransactionManager).execute {
      orderRepository.save(Order(no = "100", totalAmount = BigDecimal("100.00")))
      orderRepository.save(Order(no = "100", totalAmount = BigDecimal("100.00")))
    }
  } catch (e: DataIntegrityViolationException) {
    println("Order already exists")
  }
}
