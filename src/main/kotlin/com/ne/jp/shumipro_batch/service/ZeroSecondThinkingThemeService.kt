package com.ne.jp.shumipro_batch.service

import com.ne.jp.shumipro_batch.dto.DocumentDto
import com.ne.jp.shumipro_batch.mapper.ZeroSecondThinkingThemeMapper
import org.springframework.stereotype.Service

@Service
class ZeroSecondThinkingThemeService(private val zeroSecondThinkingThemeMapper: ZeroSecondThinkingThemeMapper) {

    fun fetchDocumentDto(): List<DocumentDto> {
        return zeroSecondThinkingThemeMapper.fetchDocumentDto()
    }
}