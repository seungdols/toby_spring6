package com.dev.seungdols

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
  @GetMapping("/hello")
  fun hello(
    @RequestParam("name") name: String,
  ): String {
    return "Hello, $name!"
  }
}
