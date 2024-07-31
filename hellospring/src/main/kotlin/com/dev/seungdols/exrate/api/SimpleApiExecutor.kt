package com.dev.seungdols.exrate.api

import java.net.HttpURLConnection
import java.net.URI

class SimpleApiExecutor : ApiExecutor {
  override fun execute(uri: URI): String {
    val httpURLConnection = uri.toURL().openConnection() as HttpURLConnection
    return httpURLConnection.inputStream.bufferedReader().use {
      it.readLines().toString()
    }
  }
}
