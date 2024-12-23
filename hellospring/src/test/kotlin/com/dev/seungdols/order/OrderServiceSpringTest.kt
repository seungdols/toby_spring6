package com.dev.seungdols.order

import com.dev.seungdols.config.OrderConfig
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal
import javax.sql.DataSource

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [OrderConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OrderServiceSpringTest(
  private val orderService: OrderService,
  private val dataSource: DataSource,
) : AnnotationSpec() {
  val jdbcClient = JdbcClient.create(dataSource)

  @Test
  fun `orderService의 createOrder 테스트`() {
    val order = orderService.createOrder("10", BigDecimal("100.00"))
    order.id shouldBeGreaterThan 0
    order.no shouldBe "10"
    order.totalAmount shouldBe BigDecimal("100.00")
  }

  @Test
  fun `orderService의 createOrders 테스트`() {
    val orderRequests =
      listOf(
        OrderRequest("100", BigDecimal("100.00")),
        OrderRequest("200", BigDecimal("200.00")),
      )
    val orders = orderService.createOrders(orderRequests)
    orders.size shouldBe 2
    for (order in orders) {
      order.id shouldBeGreaterThan 0
    }
    orders[0].no shouldBe "100"
    orders[0].totalAmount shouldBe BigDecimal("100.00")
    orders[1].no shouldBe "200"
    orders[1].totalAmount shouldBe BigDecimal("200.00")
  }

  @Test
  fun `orderService의 createDuplicateOrders 테스트`() {
    val orderRequests =
      listOf(
        OrderRequest("300", BigDecimal("100.00")),
        OrderRequest("300", BigDecimal("200.00")),
      )

    shouldThrow<DataIntegrityViolationException> {
      orderService.createOrders(orderRequests)
    }

    jdbcClient.sql(
      """
      select count(*) from orders where no = '300'
      """.trimIndent(),
    ).query(Long::class.java).single() shouldBe 0
  }
}
