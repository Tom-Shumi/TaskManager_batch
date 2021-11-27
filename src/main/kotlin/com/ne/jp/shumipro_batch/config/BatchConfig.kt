package com.ne.jp.shumipro_batch.config

import com.ne.jp.shumipro_batch.tasklet.DummyTasklet
import com.ne.jp.shumipro_batch.tasklet.ElasticsearchMigrationTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.context.annotation.Configuration
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.context.annotation.Bean


@EnableBatchProcessing
@Configuration
class BatchConfig (val jobBuilderFactory: JobBuilderFactory,
                   val stepBuilderFactory: StepBuilderFactory,
                   val elasticsearchMigrationTasklet: ElasticsearchMigrationTasklet,
                   val dummyTasklet: DummyTasklet) {

    @Bean
    fun helloWorldJob1 (helloWorldStep1: Step): Job {
        return jobBuilderFactory["helloWorldJob1"]
            .flow(helloWorldStep1)
            .end()
            .build()
    }
    @Bean
    fun helloWorldStep1 (): Step {
        return stepBuilderFactory["helloWorldStep1"]
            .tasklet(elasticsearchMigrationTasklet)
            .build()
    }

    @Bean
    fun helloWorldJob2(helloWorldStep2: Step): Job {
        return jobBuilderFactory["helloWorldJob2"]
            .flow(helloWorldStep2)
            .end()
            .build()
    }
    @Bean
    fun helloWorldStep2(): Step {
        return stepBuilderFactory["helloWorldStep2"]
            .tasklet(dummyTasklet)
            .build()
    }
}