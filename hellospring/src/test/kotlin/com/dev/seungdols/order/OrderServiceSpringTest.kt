package com.dev.seungdols.order

import com.dev.seungdols.config.OrderConfig
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@ContextConfiguration(classes = [OrderConfig::class])
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class OrderServiceSpringTest(
  private val orderService: OrderService,
) : AnnotationSpec() {
  @Test
  fun `orderService의 createOrder 메소드 테스트`() {
    val order = orderService.createOrder("100", BigDecimal("100.00"))
    order.no shouldBe "100"
    order.totalAmount shouldBe BigDecimal("100.00")
  }
}
