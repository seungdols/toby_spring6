package com.dev.seungdols.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
@Transactional
class OrderServiceImpl(
  private val orderRepository: OrderRepository,
) : OrderService {
  override fun createOrder(
    no: String,
    total: BigDecimal,
  ): Order {
    val order = Order(no = no, totalAmount = total)

    this.orderRepository.save(order)
    return order
  }

  override fun createOrders(orderRequests: List<OrderRequest>): List<Order> {
    return orderRequests.map { createOrder(it.no, it.totalAmount) }
  }
}
