package com.ne.jp.shumipro_batch.service

import com.ne.jp.shumipro_batch.elasticsearch.ElasticsearchClientRepository
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.client.indices.CreateIndexResponse
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(private val elasticsearchClientRepository: ElasticsearchClientRepository) {

    fun createIndex(indexName: String): String? {
        val request = CreateIndexRequest(indexName)
        val response = elasticsearchClientRepository.createIndex(request)
        return if (response is CreateIndexResponse && response.isAcknowledged) {
            "ok"
        } else {
            "ng"
        }
    }

    fun registerDocument(indexName: String, id: Int, content: String) : Boolean {
        val request = IndexRequest(indexName).id(id.toString())
            .source("content", content);
        return elasticsearchClientRepository.registerDocument(request)
    }
}