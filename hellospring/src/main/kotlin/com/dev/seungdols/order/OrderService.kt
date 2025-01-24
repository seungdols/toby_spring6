package com.dev.seungdols.order

import java.math.BigDecimal

interface OrderService {
  fun createOrder(
    no: String,
    total: BigDecimal,
  ): Order

  fun createOrders(orderRequests: List<OrderRequest>): List<Order>
}
