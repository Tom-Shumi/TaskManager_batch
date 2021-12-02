package com.ne.jp.shumipro_batch.mapper

import com.ne.jp.shumipro_batch.dto.DocumentDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ZeroSecondThinkingContentMapper {

    @Select("SELECT " +
            "zstc.id, " +
            "zstt.username, " +
            "zstc.content as content " +
            "FROM zero_second_thinking_content zstc " +
            "INNER JOIN zero_second_thinking_theme zstt " +
            "ON zstc.theme_id = zstt.id " +
            "ORDER BY zstc.id")
    fun fetchDocumentDto(): List<DocumentDto>
}