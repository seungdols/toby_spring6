package com.dev.seungdols.order

import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

@Service
class OrderService(
  private val orderRepository: OrderRepository,
  private val transactionManager: PlatformTransactionManager,
) {
  fun createOrder(
    no: String,
    total: BigDecimal,
  ): Order {
    val order = Order(no = no, totalAmount = total)

    this.orderRepository.save(order)
    return order
  }

  fun createOrders(orderRequests: List<OrderRequest>): List<Order> {
    return TransactionTemplate(transactionManager).execute {
      orderRequests.map { createOrder(it.no, it.totalAmount) }
    } ?: emptyList()
  }
}
