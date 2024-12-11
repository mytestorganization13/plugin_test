package com.example.logic

import java.nio.file.Files
import java.nio.file.Paths
import java.util.UUID
import kotlin.io.path.writeText
import org.springframework.stereotype.Component

@Component
class Creator {
    fun create() {
        val created = Files.createFile(Paths.get("${UUID.randomUUID()}.txt"))
        created.writeText("This is test text")
    }
}
