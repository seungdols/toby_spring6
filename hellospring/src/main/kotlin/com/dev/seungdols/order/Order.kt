package com.dev.seungdols.order

import java.math.BigDecimal

data class Order(
  val id: Long = 0,
  val no: String,
  val totalAmount: BigDecimal,
)
