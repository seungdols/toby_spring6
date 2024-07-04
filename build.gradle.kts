import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.bootJar { enabled = false }
tasks.jar { enabled = true }

plugins {
  id("org.springframework.boot") version "3.2.2"
  id("io.spring.dependency-management") version "1.1.4"
  id("org.jlleitschuh.gradle.ktlint") version "12.0.2"
  kotlin("jvm") version "1.9.20"
  kotlin("plugin.spring") version "1.9.20"
  kotlin("plugin.jpa") version "1.9.20"
  kotlin("kapt") version "1.8.22"
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
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }

  dependencies {
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      freeCompilerArgs += "-Xjsr305=strict"
      jvmTarget = JavaVersion.VERSION_17.majorVersion
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }
}
