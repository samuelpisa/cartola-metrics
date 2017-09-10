package br.com.cartola.metrics

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
open class MetricsApplication {

    fun main(args: Array<String>) {
        SpringApplication.run(MetricsApplication::class.java, *args)
    }
}
