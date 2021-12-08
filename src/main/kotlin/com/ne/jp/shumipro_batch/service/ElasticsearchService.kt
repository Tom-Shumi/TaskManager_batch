package com.ne.jp.shumipro_batch.service

import com.ne.jp.shumipro_batch.dto.DocumentDto
import com.ne.jp.shumipro_batch.elasticsearch.ElasticsearchClientRepository
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.index.reindex.DeleteByQueryRequest
import org.springframework.stereotype.Service

@Service
class ElasticsearchService(private val elasticsearchClientRepository: ElasticsearchClientRepository) {

    fun createIndex(indexName: String) {
        val request = CreateIndexRequest(indexName)
         elasticsearchClientRepository.createIndex(request)
    }

    fun registerDocument(indexName: String, document: DocumentDto) : Boolean {
        val request = createIndexRequest(indexName, document.id, document)

        return elasticsearchClientRepository.registerDocument(request)
    }

    fun bulkRegisterDocument(indexName: String, documentList: List<DocumentDto>): Boolean  {
        val request = BulkRequest()
        for ((index, document) in documentList.withIndex()) {
            request.add(createIndexRequest(indexName, index + 1, document))
        }

        return elasticsearchClientRepository.bulkRegisterDocument(request)
    }

    fun deleteAllDocument(indexName: String) {
        elasticsearchClientRepository.deleteAllDocument(DeleteByQueryRequest(indexName))
    }

    private fun createIndexRequest(indexName: String, index: Int, document: DocumentDto): IndexRequest {
        return IndexRequest(indexName).id(index.toString()).source(document.toMap())
    }
}