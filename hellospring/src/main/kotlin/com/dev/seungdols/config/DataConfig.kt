package com.dev.seungdols.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
class DataConfig {
  @Bean
  fun dataSource(): DataSource {
    return EmbeddedDatabaseBuilder()
      .setType(EmbeddedDatabaseType.H2)
      .build()
  }

  @Bean
  fun localContainerEntityManagerFactoryBean(): LocalContainerEntityManagerFactoryBean {
    val emf = LocalContainerEntityManagerFactoryBean()
    emf.dataSource = dataSource()
    emf.setPackagesToScan("com.dev.seungdols")
    emf.jpaVendorAdapter =
      HibernateJpaVendorAdapter().apply {
        setDatabase(Database.H2)
        setGenerateDdl(true)
        setShowSql(true)
      }
    return emf
  }
}
