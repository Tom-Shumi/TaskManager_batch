package com.ne.jp.shumipro_batch.dto

data class DocumentDto (
    var id: Int,
    var username: String,
    var content: String
) {
    fun toMap(): Map<String, Any> {
        return mapOf("id" to id,
            "username" to username,
            "content" to content)
    }
}