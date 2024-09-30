package com.dev.seungdols.order

import com.dev.seungdols.data.OrderRepository
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.stereotype.Service
import org.springframework.transaction.support.TransactionTemplate
import java.math.BigDecimal

@Service
class OrderService(
  private val orderRepository: OrderRepository,
  private val jpaTransactionManager: JpaTransactionManager,
) {
  fun createOrder(
    no: String,
    total: BigDecimal,
  ): Order {
    val order = Order(no = no, totalAmount = total)

    TransactionTemplate(jpaTransactionManager).execute {
      orderRepository.save(order)
    }

    return order
  }
}
