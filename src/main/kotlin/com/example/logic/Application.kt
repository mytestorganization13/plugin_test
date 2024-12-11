package com.example.logic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    @Autowired
    lateinit var component: MyComponent

    @Bean
    fun commandLineRunner(): CommandLineRunner {
        return CommandLineRunner {
            println("Hello, Spring Boot with Kotlin!")
            component.createFile()
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
