package com.dev.seungdols.order

import java.math.BigDecimal

data class OrderRequest(
  val no: String,
  val totalAmount: BigDecimal,
)
