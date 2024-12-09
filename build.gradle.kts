import com.makarytskyi.MyPlugin

plugins {
    kotlin("jvm") version "2.0.0"
    `java-gradle-plugin`
}

apply<MyPlugin>()

tasks.named("build") {
    dependsOn("myTask")
}

group = "com.makarytskyi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
