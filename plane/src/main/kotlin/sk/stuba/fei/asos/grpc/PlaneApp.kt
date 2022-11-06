package sk.stuba.fei.asos.grpc

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import sk.stuba.fei.asos.grpc.utils.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation){
        json()
    }
    routing {
        get("/") {
            call.respondText("Hello, world!")
        }
        get("/plane"){
            call.respond(
                HttpStatusCode.OK,
                planeData
            )
            setLocation()
        }
    }
}