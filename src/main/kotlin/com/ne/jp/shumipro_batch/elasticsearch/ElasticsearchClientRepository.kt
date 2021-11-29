package com.ne.jp.shumipro_batch.elasticsearch

import org.elasticsearch.client.indices.CreateIndexRequest
import org.elasticsearch.client.indices.CreateIndexResponse
import org.springframework.stereotype.Repository
import org.elasticsearch.client.RestHighLevelClient

import org.elasticsearch.client.RequestOptions

import org.elasticsearch.action.search.SearchRequest

import org.elasticsearch.action.search.SearchResponse
import java.lang.Exception


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

    fun createIndex(request: CreateIndexRequest): CreateIndexResponse? {
        return try {
            restHighLevelClient.indices().create(request, RequestOptions.DEFAULT)
        } catch (e: Exception) {
            println(e.cause)
            println(e.message)
            setClient(elasticsearchClientConfig.getRecreateClient())
            null
        }
    }
}