package com.dev.seungdols.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
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
  fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
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

  @Bean
  fun persistenceAnnotationBeanPostProcessor(): PersistenceAnnotationBeanPostProcessor {
    return PersistenceAnnotationBeanPostProcessor()
  }

  @Bean
  fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
    return JpaTransactionManager(emf)
  }
}
