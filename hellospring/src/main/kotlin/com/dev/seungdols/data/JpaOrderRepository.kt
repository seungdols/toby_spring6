package com.dev.seungdols.data

import com.dev.seungdols.order.Order
import com.dev.seungdols.order.OrderRepository
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class JpaOrderRepository() : OrderRepository {
  @PersistenceContext
  lateinit var entityManager: EntityManager

  override fun save(order: Order) {
    entityManager.persist(order)
  }
}
