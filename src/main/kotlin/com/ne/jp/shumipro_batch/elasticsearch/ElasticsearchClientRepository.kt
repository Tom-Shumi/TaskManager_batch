package com.ne.jp.shumipro_batch.elasticsearch

import org.elasticsearch.action.DocWriteResponse
import org.elasticsearch.action.delete.DeleteRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.client.indices.CreateIndexResponse
import org.springframework.stereotype.Repository
import org.elasticsearch.client.RestHighLevelClient

import org.elasticsearch.client.RequestOptions

import org.elasticsearch.action.search.SearchRequest

import org.elasticsearch.action.search.SearchResponse
import java.lang.Exception
import org.elasticsearch.index.reindex.BulkByScrollResponse

import org.elasticsearch.index.query.QueryBuilders

import org.elasticsearch.index.reindex.DeleteByQueryRequest





@Repository
class ElasticsearchClientRepository(
    private val elasticsearchClientConfig: ElasticsearchClientConfig,
    private var restHighLevelClient: RestHighLevelClient,
) {

    fun setClient(restHighLevelClient: RestHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient
    }

    fun search(request: SearchRequest): SearchResponse? {
        return try {
            restHighLevelClient.search(request, RequestOptions.DEFAULT)
        } catch (e: Exception) {
            setClient(elasticsearchClientConfig.getRecreateClient())
            null
        }
    }

    fun createIndex(request: CreateIndexRequest) {
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT)
    }

    fun deleteAllDocument(request: DeleteByQueryRequest) {
        request.setQuery(QueryBuilders.matchAllQuery())
        restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT)
    }

    fun registerDocument(request: IndexRequest): Boolean {
        return try {
            val response = restHighLevelClient.index(request, RequestOptions.DEFAULT)
            response.result == DocWriteResponse.Result.CREATED;
        } catch (e: Exception) {
            e.printStackTrace()
            setClient(elasticsearchClientConfig.getRecreateClient())
            false
        }
    }
}