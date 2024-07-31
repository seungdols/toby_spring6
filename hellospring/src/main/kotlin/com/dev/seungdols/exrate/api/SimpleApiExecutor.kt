package com.dev.seungdols.exrate.api

import java.net.HttpURLConnection
import java.net.URL

class SimpleApiExecutor : ApiExecutor {
  override fun execute(url: URL): String {
    val httpURLConnection = url.openConnection() as HttpURLConnection
    return httpURLConnection.inputStream.bufferedReader().use {
      it.readLines().toString()
    }
  }
}
