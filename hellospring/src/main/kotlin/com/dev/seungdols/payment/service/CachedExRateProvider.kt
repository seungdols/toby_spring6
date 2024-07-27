package com.dev.seungdols.payment.service

import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import java.time.LocalDateTime

class CachedExRateProvider(
  private val target: ExRateProvider,
) : ExRateProvider {
  lateinit var cachedExRate: BigDecimal
  lateinit var cacheExpiryTime: LocalDateTime

  private val log = KotlinLogging.logger {}

  override fun getExRate(currency: String): BigDecimal {
    if (!::cachedExRate.isInitialized || cacheExpiryTime.isBefore(LocalDateTime.now())) {
      cachedExRate = target.getExRate(currency)
      cacheExpiryTime = LocalDateTime.now().plusSeconds(3)

      log.info { "환율 정보를 캐싱했습니다: $cachedExRate" }
    }

    return cachedExRate
  }
}
