package com.ne.jp.shumipro_batch.service

import com.ne.jp.shumipro_batch.elasticsearch.ElasticsearchClientRepository
import org.elasticsearch.action.delete.DeleteRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.client.indices.CreateIndexResponse
import org.elasticsearch.index.reindex.DeleteByQueryRequest
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(private val elasticsearchClientRepository: ElasticsearchClientRepository) {

    fun createIndex(indexName: String) {
        val request = CreateIndexRequest(indexName)
         elasticsearchClientRepository.createIndex(request)
    }

    fun registerDocument(indexName: String, id: Int, content: Map<String, Any>) : Boolean {
        val request = IndexRequest(indexName).id(id.toString()).source(content)

        return elasticsearchClientRepository.registerDocument(request)
    }

    fun deleteAllDocument(indexName: String) {
        elasticsearchClientRepository.deleteAllDocument(DeleteByQueryRequest(indexName))
    }
}