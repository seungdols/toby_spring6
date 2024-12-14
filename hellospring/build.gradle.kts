
dependencies {
  implementation(Dependencies.spring)
  implementation(Dependencies.kotlin)
  implementation(Dependencies.jackson)
  implementation(Dependencies.queryDsl)
  testImplementation(Dependencies.kotest)
  testImplementation(Dependencies.springTest)
  testImplementation(Dependencies.jpa)
  runtimeOnly(Dependencies.h2)
//  implementation(Dependencies.h2)
//  testImplementation(Dependencies.test)
}
