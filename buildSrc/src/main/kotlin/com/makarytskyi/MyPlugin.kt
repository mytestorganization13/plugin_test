package com.makarytskyi

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
    fun greet() {
        println("Hello from MyGradlePlugin!")
    }
}
