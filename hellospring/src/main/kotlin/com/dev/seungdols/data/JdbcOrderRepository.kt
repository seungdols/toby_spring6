package com.dev.seungdols.data

import com.dev.seungdols.order.Order
import com.dev.seungdols.order.OrderRepository
import jakarta.annotation.PostConstruct
import org.springframework.jdbc.core.simple.JdbcClient
import javax.sql.DataSource

class JdbcOrderRepository(
  private val dataSource: DataSource,
) : OrderRepository {
  val jdbcClient = JdbcClient.create(dataSource)

  @PostConstruct
  fun initDb() {
    jdbcClient.sql(
      """
      create table orders (id bigint not null, no varchar(255), totalAmount numeric(38,2), primary key (id));
      alter table if exists orders drop constraint if exists UK43egxxciqr9ncgmxbdx2avi8n;
      alter table if exists orders add constraint UK43egxxciqr9ncgmxbdx2avi8n unique (no);
      create sequence orders_SEQ start with 1 increment by 50;
      """.trimIndent(),
    ).update()
  }

  override fun save(order: Order) {
    val id =
      jdbcClient.sql(
        """
        select next value for orders_SEQ
        """.trimIndent(),
      ).query(Long::class.java).single()

    jdbcClient.sql(
      """
      insert into orders (id, no, totalAmount) values (?, ?, ?)
      """.trimIndent(),
    )
      .params(id, order.no, order.totalAmount)
      .update()
  }
}
