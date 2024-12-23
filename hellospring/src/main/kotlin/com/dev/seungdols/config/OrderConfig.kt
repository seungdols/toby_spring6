package com.dev.seungdols.config

import com.dev.seungdols.data.JdbcOrderRepository
import com.dev.seungdols.order.OrderRepository
import com.dev.seungdols.order.OrderService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
@Import(DataConfig::class)
class OrderConfig {
  @Bean
  fun orderService(
    orderRepository: OrderRepository,
    transactionManager: PlatformTransactionManager,
  ): OrderService {
    return OrderService(orderRepository, transactionManager)
  }

  @Bean
  fun orderRepository(dataSource: DataSource): OrderRepository {
    return JdbcOrderRepository(dataSource)
  }
}
