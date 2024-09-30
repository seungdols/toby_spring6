package com.dev.seungdols.config

import com.dev.seungdols.data.OrderRepository
import com.dev.seungdols.order.OrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.orm.jpa.JpaTransactionManager

@Configuration
@Import(DataConfig::class)
class OrderConfig {
  @Bean
  fun orderService(jpaTransactionManager: JpaTransactionManager): OrderService {
    return OrderService(orderRepository(), jpaTransactionManager)
  }

  @Bean
  fun orderRepository(): OrderRepository {
    return OrderRepository()
  }
}
