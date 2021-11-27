package com.ne.jp.shumipro_batch.tasklet

import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
@StepScope
class DummyTasklet: Tasklet {

    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus {
        println("DummyTasklet");
        return RepeatStatus.FINISHED;
    }
}