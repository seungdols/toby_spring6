package com.dev.seungdols.data

import com.dev.seungdols.order.Order
import jakarta.persistence.EntityManagerFactory

class OrderRepository(
  private val emf: EntityManagerFactory,
) {
  fun save(order: Order) {
    val entityManager = emf.createEntityManager()
    val transaction = entityManager.transaction
    transaction.begin()

    try {
      entityManager.persist(order)
      entityManager.flush()

      transaction.commit()
    } catch (e: Exception) {
      if (transaction.isActive) {
        transaction.rollback()
      }
      throw e
    } finally {
      if (emf.isOpen) {
        entityManager.close()
      }
    }
  }
}
