package com.ne.jp.shumipro_batch.config

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParametersIncrementer

class Incrementer : JobParametersIncrementer {

    override fun getNext(parameters: JobParameters?): JobParameters {
        val jobParameters = JobParameters();
        return JobParametersBuilder(jobParameters).addLong("timestamp", System.currentTimeMillis()).toJobParameters();
    }
}