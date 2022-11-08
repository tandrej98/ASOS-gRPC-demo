package sk.stuba.fei.asos.grpc.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sk.stuba.fei.asos.grpc.routes.getAllPlanes
import sk.stuba.fei.asos.grpc.routes.getPlaneById

fun Application.configureRouting() {
    routing {
        getPlaneById()
        getAllPlanes()
        get("/") {
            call.respondText("Hello, world!")
        }
    }
}