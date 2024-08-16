package com.dev.seungdols.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "orders")
data class Order(
  @Id @GeneratedValue
  val id: Long = 0,
  @Column(unique = true)
  val no: String,
  val totalAmount: BigDecimal,
)
