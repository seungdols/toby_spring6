package com.dev.seungdols

import com.dev.seungdols.config.DataConfig
import com.dev.seungdols.order.Order
import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.math.BigDecimal

fun main(args: Array<String>) {
  val beanFactory = AnnotationConfigApplicationContext(DataConfig::class.java)
  val emf: EntityManagerFactory = beanFactory.getBean(EntityManagerFactory::class.java)

  val entityManager = emf.createEntityManager()
  entityManager.transaction.begin()

  val order = Order(no = "100", totalAmount = BigDecimal("100"))
  entityManager.persist(order)

  println(order)

  entityManager.transaction.commit()
  entityManager.close()
}
