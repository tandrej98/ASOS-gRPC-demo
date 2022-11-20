package sk.stuba.fei.asos.project24.dispatching

import io.ktor.server.application.*
import sk.stuba.fei.asos.project24.dispatching.grpc.Planes
import sk.stuba.fei.asos.project24.dispatching.plugins.configureRouting
import sk.stuba.fei.asos.project24.dispatching.plugins.configureSerialization

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    Planes.discoverPlanes()
    configureSerialization()
    configureRouting()
}