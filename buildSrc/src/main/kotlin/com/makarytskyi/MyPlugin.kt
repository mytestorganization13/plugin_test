package com.makarytskyi

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction

class MyPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.register("myTask", CreatePullRequestTask::class.java)
    }
}

open class CreatePullRequestTask : DefaultTask() {

    private val branchName = "feature/pr-branch-${System.currentTimeMillis()}"
    private val targetBranch = "main"
    private val githubToken = System.getenv("GITHUB_TOKEN")

    @TaskAction
    fun createPullRequest() {
        executeCommand("git checkout -b $branchName")
        executeGradleBuild()
        commitChanges()
        pushChanges()
        createPullRequestOnGitHub()
    }

    private fun executeCommand(command: String) {
        val process = ProcessBuilder(command.split(" ")).start()
        val output = process.inputStream.bufferedReader().readText()
        println(output)
    }

    private fun executeGradleBuild() {
        val process = ProcessBuilder("./gradlew", "build", "-parallel").start()
        val output = process.inputStream.bufferedReader().readText()
        println(output)
    }

    private fun commitChanges() {
        println("Committing changes...")
        executeCommand("git config --global user.email 'github-actions@github.com'")
        executeCommand("git config --global user.name 'github-actions'")
        executeCommand("git add .")
        executeCommand("git commit -m 'Add files'")
    }

    private fun pushChanges() {
        println("Pushing changes to GitHub...")
        val gitPushCommand = "git push -u origin $branchName"
        executeCommand(gitPushCommand)
    }

    private fun createPullRequestOnGitHub() {
        println("Creating pull request from $branchName to $targetBranch...")
        val process = ProcessBuilder("gh", "pr", "create", "--head", branchName, "--base", targetBranch, "--title", "Automated PR", "--body", "This PR is automatically created via Gradle plugin.").start()
        val output = process.inputStream.bufferedReader().readText()
        println(output)
    }
}
