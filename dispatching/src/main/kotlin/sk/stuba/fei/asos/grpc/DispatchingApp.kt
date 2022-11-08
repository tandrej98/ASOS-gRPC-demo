package sk.stuba.fei.asos.grpc

import io.ktor.server.application.*
import sk.stuba.fei.asos.grpc.plugins.configureRouting
import sk.stuba.fei.asos.grpc.plugins.configureSerialization

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureRouting()
}