package com.example.logic

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MyComponent {
    @Autowired
    private lateinit var creator: Creator

    fun createFile() {
        creator.create()
    }
}
