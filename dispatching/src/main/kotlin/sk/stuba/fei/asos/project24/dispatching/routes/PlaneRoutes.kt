package sk.stuba.fei.asos.project24.dispatching.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sk.stuba.fei.asos.project24.dispatching.utils.planes
import sk.stuba.fei.asos.project24.dispatching.utils.setNewLocation
import sk.stuba.fei.asos.project24.dispatching.utils.getPlaneById

fun Route.getPlaneById() {
    get("/planes/{id}"){
        val id = call.parameters["id"]?.toLong()
        if(id != null) {
            var plane = getPlaneById(id)
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