package com.ne.jp.shumipro_batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.boot.SpringApplication
import kotlin.system.exitProcess


@SpringBootApplication
class ShumiproBatchApplication

fun main(args: Array<String>) {
//	runApplication<ShumiproBatchApplication>(*args)
	exitProcess(SpringApplication.exit(runApplication<ShumiproBatchApplication>(*args)))
}