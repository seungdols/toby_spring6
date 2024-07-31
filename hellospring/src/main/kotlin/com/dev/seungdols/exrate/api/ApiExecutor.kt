package com.dev.seungdols.exrate.api

import java.net.URI

interface ApiExecutor {
  fun execute(uri: URI): String
}
