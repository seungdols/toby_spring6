import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }

plugins {
  id("org.springframework.boot") version "3.3.2"
  id("io.spring.dependency-management") version "1.1.4"
  id("org.jlleitschuh.gradle.ktlint") version "12.0.2"
  kotlin("jvm") version "2.0.0"
  kotlin("plugin.spring") version "2.0.0"
  kotlin("plugin.jpa") version "2.0.0"
  kotlin("kapt") version "2.0.0"
}

version = "0.0.1-SNAPSHOT"

allprojects {
  group = "com.dev.seungdols"
  repositories {
    mavenCentral()
  }
}

subprojects {
  apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
    plugin("kotlin-kapt")
    plugin("org.jlleitschuh.gradle.ktlint")
    plugin("idea")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
  }

  java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  dependencies {
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
  }

  tasks.withType<KotlinCompile> {
    compilerOptions {
      freeCompilerArgs.add("-Xjsr305=strict")
      jvmTarget.set(JvmTarget.JVM_21)
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}
