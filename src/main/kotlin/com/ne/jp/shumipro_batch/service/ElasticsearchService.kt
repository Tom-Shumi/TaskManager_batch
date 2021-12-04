package com.ne.jp.shumipro_batch.service

import com.ne.jp.shumipro_batch.dto.DocumentDto
import com.ne.jp.shumipro_batch.elasticsearch.ElasticsearchClientRepository
import org.elasticsearch.action.bulk.BulkRequest
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

    fun registerDocument(indexName: String, document: DocumentDto) : Boolean {
        val request = createIndexRequest(indexName, document)

        return elasticsearchClientRepository.registerDocument(request)
    }

    fun bulkRegisterDocument(indexName: String, documentList: List<DocumentDto>): Boolean  {
        val request = BulkRequest()
        for (document in documentList) {
            request.add(createIndexRequest(indexName, document))
        }

        return elasticsearchClientRepository.bulkRegisterDocument(request)
    }

    fun deleteAllDocument(indexName: String) {
        elasticsearchClientRepository.deleteAllDocument(DeleteByQueryRequest(indexName))
    }

    private fun createIndexRequest(indexName: String, document: DocumentDto): IndexRequest {
        return IndexRequest(indexName).id(document.id.toString()).source(document.toMap())
    }
}