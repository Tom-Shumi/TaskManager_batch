package com.ne.jp.shumipro_batch.tasklet

import com.ne.jp.shumipro_batch.dto.DocumentDto
import com.ne.jp.shumipro_batch.service.ElasticsearchService
import com.ne.jp.shumipro_batch.service.ZeroSecondThinkingContentService
import com.ne.jp.shumipro_batch.service.ZeroSecondThinkingThemeService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
@StepScope
class ElasticsearchMigrationTasklet(private val elasticsearchService: ElasticsearchService,
                                    private val zeroSecondThinkingThemeService: ZeroSecondThinkingThemeService,
                                    private val zeroSecondThinkingContentService: ZeroSecondThinkingContentService): Tasklet {

    companion object {
        const val INDEX_NAME_THEME = "zero_second_thinking_theme"
        const val INDEX_NAME_CONTENT = "zero_second_thinking_content"
    }

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {

        deleteDocument()
        registerDocument()

        return RepeatStatus.FINISHED
    }

    private fun deleteDocument() {
        elasticsearchService.deleteAllDocument(INDEX_NAME_THEME)
        elasticsearchService.deleteAllDocument(INDEX_NAME_CONTENT)
    }

    private fun registerDocument() {
        registerTheme()
        registerContent()
    }

    private fun registerTheme() {
        val themeList = zeroSecondThinkingThemeService.fetchDocumentDto()
        register(INDEX_NAME_THEME, themeList)
    }

    private fun registerContent() {
        val themeList = zeroSecondThinkingContentService.fetchDocumentDto()
        register(INDEX_NAME_CONTENT, themeList)
    }

    private fun register(indexName: String, documentList: List<DocumentDto>) {
        elasticsearchService.bulkRegisterDocument(indexName, documentList)
    }

}