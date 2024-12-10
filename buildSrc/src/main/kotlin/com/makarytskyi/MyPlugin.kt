package com.makarytskyi

import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
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

    private val branchName = getNewBranchName()
    private val targetBranch = "main"
    private val githubToken = System.getenv("GITHUB_TOKEN")

    @TaskAction
    fun createPullRequest() {
        executeCommand("git checkout -b $branchName")
        //executeGradleBuild()
        Files.write(Paths.get("newFile.txt"), "Hello, World!".toByteArray())
        commitChanges()
        pushChanges()
        createPullRequestOnGitHub()
    }

    private fun executeCommand(command: String): String {
        val process = ProcessBuilder(command.split(" ")).start()
        return process.inputStream.bufferedReader().readText()
    }

    private fun getNewBranchName(): String {
        val command = "git log --merges --oneline main -n 1 --pretty=format:\"%s\""

        val process = ProcessBuilder("bash", "-c", command).start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val commitMessage = reader.readText().trim()

        val branch = commitMessage.split("from")[1].trim()
        return "$branch-added-files"
    }

    private fun executeGradleBuild() {
        val process = ProcessBuilder("./gradlew", "build", "-parallel").start()
        val output = process.inputStream.bufferedReader().readText()
        println(output)
    }

    private fun commitChanges() {
        executeCommand("git config --global user.email 'github-actions@github.com'")
        executeCommand("git config --global user.name 'github-actions'")
        executeCommand("git add .")
        executeCommand("git commit -m 'Add files'")
    }

    private fun pushChanges() {
        val gitPushCommand = "git push -u origin $branchName"
        executeCommand(gitPushCommand)
    }

    private fun createPullRequestOnGitHub() {
        val process = ProcessBuilder("gh", "pr", "create", "--head", branchName, "--base", targetBranch, "--title", "'Automated PR'", "--body", "'This PR is automatically created via Gradle plugin.'").start()
        val output = process.inputStream.bufferedReader().readText()
        println(output)
    }
}
