package com.ne.jp.shumipro_batch.config

import com.ne.jp.shumipro_batch.tasklet.ElasticsearchMigrationTasklet
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.context.annotation.Configuration
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.context.annotation.Bean
import org.springframework.batch.core.launch.support.RunIdIncrementer


@EnableBatchProcessing
@Configuration
class BatchConfig(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory,
    val elasticsearchMigrationTasklet: ElasticsearchMigrationTasklet
) {

    @Bean
    fun elasticsearchMigrationJob (): Job {
        return jobBuilderFactory
            .get("elasticsearchMigrationJob")
            .incrementer(RunIdIncrementer())
            .start(elasticsearchMigrationStep())
            .build()
    }

    fun elasticsearchMigrationStep (): Step {
        return stepBuilderFactory.get("elasticsearchMigrationStep")
            .tasklet(elasticsearchMigrationTasklet)
            .build()
    }
}