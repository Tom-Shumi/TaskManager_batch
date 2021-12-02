package com.ne.jp.shumipro_batch.service

import com.ne.jp.shumipro_batch.dto.DocumentDto
import com.ne.jp.shumipro_batch.mapper.ZeroSecondThinkingContentMapper
import org.springframework.stereotype.Service

@Service
class ZeroSecondThinkingContentService(private val zeroSecondThinkingContentMapper: ZeroSecondThinkingContentMapper) {

    fun fetchDocumentDto(): List<DocumentDto> {
        return zeroSecondThinkingContentMapper.fetchDocumentDto()
    }
}