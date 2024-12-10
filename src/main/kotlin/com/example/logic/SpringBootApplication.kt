package com.example.logic

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.example.logic"])
@SpringBootApplication
class SpringBootApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringBootApplication::class.java, *args)
}
