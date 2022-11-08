package sk.stuba.fei.asos.grpc.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sk.stuba.fei.asos.grpc.utils.planes
import sk.stuba.fei.asos.grpc.utils.setNewLocation

fun Route.getPlaneById() {
    get("/planes/{id}"){
        val id = call.parameters["id"]?.toLong()
        if(id != null) {
            var plane = sk.stuba.fei.asos.grpc.utils.getPlaneById(id)
            call.respond(
                HttpStatusCode.OK,
                plane ?: "This plain does not exist"
            )
            setNewLocation(plane)
        }
    }
}

fun Route.getAllPlanes() {
    get("/planes"){
        call.respond(
            HttpStatusCode.OK,
            planes
        )
    }
}