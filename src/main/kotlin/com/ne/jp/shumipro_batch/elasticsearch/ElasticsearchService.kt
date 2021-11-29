package com.ne.jp.shumipro_batch.elasticsearch

import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.client.indices.CreateIndexResponse
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(private val elasticsearchClientRepository: ElasticsearchClientRepository) {

    fun createIndex(): String? {
        val request = CreateIndexRequest("ZeroSecondThinkingTheme")
        val response = elasticsearchClientRepository.createIndex(request)
        return if (response is CreateIndexResponse && response.isAcknowledged) {
            "ok"
        } else {
            "ng"
        }
    }
}