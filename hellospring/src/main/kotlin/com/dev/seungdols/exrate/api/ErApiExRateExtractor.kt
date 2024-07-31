package com.dev.seungdols.exrate.api

import com.dev.seungdols.exrate.vo.ExRateData
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.math.BigDecimal

class ErApiExRateExtractor : ExRateExtractor {
  override fun extract(response: String): BigDecimal {
    val objectMapper = ObjectMapper()
    objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    objectMapper.registerKotlinModule()
    val exRateData = objectMapper.readValue(response, object : TypeReference<List<ExRateData>>() {}).first()
    return exRateData.rates["KRW"] ?: throw RuntimeException("환율 정보가 없습니다.")
  }
}
