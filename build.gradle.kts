plugins {
    kotlin("jvm") version "2.0.0"
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.0"
    `kotlin-dsl`
    `java-gradle-plugin`
}

group = "com.makarytskyi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
        exclude(group = "ch.qos.logback", module = "logback-classic")
        exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    }
}

tasks.register("commit") {
    doLast {
        val commitMessage = "Automatically added changed files with generated ids"
        val commitCommand = "git add -A && git commit -m '$commitMessage'"
        executeCommand(commitCommand)
    }
}

tasks.register("push") {
    doFirst {
        val gitPushCommand = "git push -u origin master"
        executeCommand(gitPushCommand)
    }
}

fun executeCommand(command: String): String {
    val split = command.split(" ")
    val process = ProcessBuilder(split).start()
    val output = process.inputStream.bufferedReader().readText()
    println("error ======== ${process.errorStream.bufferedReader().readText()}")
    process.waitFor()
    return output
}

