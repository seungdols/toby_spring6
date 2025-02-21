package com.dev.seungdols.order

import java.math.BigDecimal

class Order(
  var id: Long = 0,
  val no: String,
  val totalAmount: BigDecimal,
) {
  constructor() : this(0, "", BigDecimal.ZERO)
}
