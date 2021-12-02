package com.ne.jp.shumipro_batch.mapper

import com.ne.jp.shumipro_batch.dto.DocumentDto
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface ZeroSecondThinkingThemeMapper {

    @Select("SELECT " +
            "id, " +
            "username, " +
            "theme as content " +
            "FROM zero_second_thinking_theme " +
            "ORDER BY id")
    fun fetchDocumentDto(): List<DocumentDto>
}