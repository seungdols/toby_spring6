package com.dev.seungdols.order

interface OrderRepository {
  fun save(order: Order)
}
