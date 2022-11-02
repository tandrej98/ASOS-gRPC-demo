import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application")
    kotlin("jvm") version "1.7.20"
}

application {
    mainClass.set("sk.stuba.fei.asos.project24.dispatching.DispatchingAppKt")
}

group = "sk.stuba.fei.asos.project24"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}