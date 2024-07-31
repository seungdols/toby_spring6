package com.dev.seungdols.exrate.api

import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class HttpClientExecutor : ApiExecutor {
  override fun execute(uri: URI): String {
    val client = HttpClient.newHttpClient()
    val request =
      HttpRequest.newBuilder()
        .uri(uri)
        .GET()
        .build()
    return client.send(request, HttpResponse.BodyHandlers.ofString()).body()
  }
}
