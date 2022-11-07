import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application")
    kotlin("jvm")
}

application {
    mainClass.set("sk.stuba.fei.asos.project24.plane.PlaneAppKt")
}

group = rootProject.group
val grpcVersion: String by rootProject.extra
val grpcKotlinVersion: String by rootProject.extra
val coroutinesVersion: String by rootProject.extra
val logbackVersion: String by rootProject.extra
val kotlinLogVersion: String by rootProject.extra

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    implementation(project(":plane-grpc-api"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.grpc:grpc-netty:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLogVersion")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}