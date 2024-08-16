package com.dev.seungdols.data

import com.dev.seungdols.order.Order
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class OrderRepository() {
  @PersistenceContext
  lateinit var entityManager: EntityManager

  fun save(order: Order) {
    entityManager.persist(order)
  }
}
