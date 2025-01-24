package com.dev.seungdols.order

import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

class OrderServiceTxProxy(
  private val target: OrderService,
  private val transactionManager: PlatformTransactionManager,
) : OrderService {
  override fun createOrder(
    no: String,
    total: BigDecimal,
  ): Order {
    return TransactionTemplate(transactionManager).execute {
      target.createOrder(no, total)
    } ?: throw IllegalStateException("Failed to create order")
  }

  override fun createOrders(orderRequests: List<OrderRequest>): List<Order> {
    return TransactionTemplate(transactionManager).execute {
      target.createOrders(orderRequests)
    } ?: emptyList()
  }
}
