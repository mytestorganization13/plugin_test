package com.example.logic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

//@Component
class RunnerClass : CommandLineRunner {
    @Autowired
    private lateinit var component: MyComponent

    override fun run(vararg args: String?) {
        println("Hello, Spring Boot with Kotlin!")
        component.createFile()
    }
}
