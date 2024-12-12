package com.example.logic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class Runner {

    @Autowired
    private lateinit var myComponent: MyComponent

    @EventListener(ApplicationReadyEvent::class)
    fun run(vararg args: String?) {
        myComponent.createFile()
    }
}
