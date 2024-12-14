
dependencies {
  implementation(Dependencies.spring)
  implementation(Dependencies.kotlin)
  implementation(Dependencies.jackson)
  implementation(Dependencies.jpa)
  implementation(Dependencies.queryDsl)
  testImplementation(Dependencies.kotest)
  testImplementation(Dependencies.springTest)
  runtimeOnly(Dependencies.h2)
  testImplementation(kotlin("test"))
}
