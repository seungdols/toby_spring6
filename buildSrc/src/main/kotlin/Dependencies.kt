import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {
  val spring = listOf(
    "org.springframework.boot:spring-boot-starter-data-jpa",
    "org.springframework.boot:spring-boot-starter-data-redis",
    "org.springframework.boot:spring-boot-starter-web",
  )

  val test = listOf(
    "org.springframework.boot:spring-boot-starter-test",
    "io.kotest:kotest-runner-junit5:5.6.2",
    "io.kotest:kotest-assertions-core:5.6.2",
    "io.kotest.extensions:kotest-extensions-spring:1.1.3",
    "io.kotest.extensions:kotest-extensions-testcontainers:2.0.2",
  )

  val h2 = "com.h2database:h2"

  val mysql = "com.mysql:mysql-connector-j"

  val queryDsl = "com.querydsl:querydsl-jpa:5.0.0:jakarta"

  val springDoc = "org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0"

  val kotlin = listOf(
    "org.jetbrains.kotlin:kotlin-reflect",
    "io.github.oshai:kotlin-logging-jvm:5.1.4"
  )

  val jackson = listOf(
//    "com.fasterxml.jackson.core:jackson-databind:2.17.1",
    "com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.1",
    "com.fasterxml.jackson.module:jackson-module-kotlin",
  )

  val kotest = listOf(
    "io.kotest:kotest-assertions-core:5.6.2",
    "io.kotest:kotest-runner",
    "io.kotest:kotest-runner-junit5:5.6.2",
  )

  val springTest = listOf(
    "org.springframework.boot:spring-boot-starter-test",
    "io.kotest.extensions:kotest-extensions-spring:1.1.3",
  )
}

fun DependencyHandler.api(dependencies: List<Any>) {
  dependencies.forEach {
    add("api", it)
  }
}

fun DependencyHandler.implementation(dependencies: List<Any>) {
  dependencies.forEach {
    add("implementation", it)
  }
}

fun DependencyHandler.compileOnly(dependencies: List<Any>) {
  dependencies.forEach {
    add("compileOnly", it)
  }
}

fun DependencyHandler.testImplementation(dependencies: List<String>) {
  dependencies.forEach {
    add("testImplementation", it)
  }
}

fun DependencyHandler.kapt(dependencies: List<String>) {
  dependencies.forEach {
    add("kapt", it)
  }
}
