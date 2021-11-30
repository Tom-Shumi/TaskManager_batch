package com.ne.jp.shumipro_batch.tasklet

import com.ne.jp.shumipro_batch.elasticsearch.ElasticsearchService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
@StepScope
class ElasticsearchMigrationTasklet(private val elasticsearchService: ElasticsearchService): Tasklet {

    companion object {
        const val INDEX_NAME_THEME = "zero_second_thinking_theme"
    }

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        elasticsearchService.registerDocument(INDEX_NAME_THEME, 1, "test")
        return RepeatStatus.FINISHED
    }
}