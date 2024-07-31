package com.dev.seungdols.exrate.api

import java.net.URL

interface ApiExecutor {
  fun execute(url: URL): String
}
