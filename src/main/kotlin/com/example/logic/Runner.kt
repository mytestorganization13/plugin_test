package com.example.logic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Runner : CommandLineRunner {
    @Autowired
    private lateinit var myComponent: MyComponent

    override fun run(vararg args: String?) {
        myComponent.createFile()
    }
}
