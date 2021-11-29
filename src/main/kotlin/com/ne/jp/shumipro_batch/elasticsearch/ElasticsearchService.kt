package com.ne.jp.shumipro_batch.elasticsearch

import org.elasticsearch.client.indices.CreateIndexRequest
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(private val elasticsearchClientRepository: ElasticsearchClientRepository) {

    fun createIndex(): String? {
        val request = CreateIndexRequest("ZeroSecondThinkingTheme")
        return if (elasticsearchClientRepository.createIndex(request).isAcknowledged()) {
            "ok"
        } else {
            "ng"
        }
    }
}