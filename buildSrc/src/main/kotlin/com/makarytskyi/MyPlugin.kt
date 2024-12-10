package com.makarytskyi

import java.io.File
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("myTask", MyTask::class.java)
    }
}

open class MyTask : DefaultTask() {
    @TaskAction
    fun createFile() {
        val file = File(project.projectDir, "abc.txt")

        if (file.createNewFile()) {
            println("File abc.txt created at: ${file.absolutePath}")
        } else {
            println("File abc.txt already exists at: ${file.absolutePath}")
        }

        file.writeText("This is a sample text inside abc.txt file.")
    }
}
