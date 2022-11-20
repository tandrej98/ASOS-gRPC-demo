package sk.stuba.fei.asos.project24.dispatching.routes

import io.grpc.StatusRuntimeException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import sk.stuba.fei.asos.project24.dispatching.data.PlaneInfo
import sk.stuba.fei.asos.project24.dispatching.grpc.Planes

fun Route.getPlaneById() {
    get("/planes/{id}"){
        val id = call.parameters["id"]?.toLong()
        if(id != null) {
            try {
                val info = Planes.planeInfo(id)
                val location = Planes.planeCurrentLocation(id)

                call.respond(
                    HttpStatusCode.OK,
                    PlaneInfo.fromGrpcResponses(info, location)
                )
            } catch (e: IllegalArgumentException) {
                call.respond(
                    HttpStatusCode.NotFound,
                    "This plane has not been registered by the dispatching"
                )
            } catch (e: StatusRuntimeException) {
                call.respond(
                    HttpStatusCode.ServiceUnavailable,
                    "This plane has not been initialized yet"
                )
            }
        }
    }
}

fun Route.getAllPlanes() {
    get("/planes"){
        call.respond(
            HttpStatusCode.OK,
            Planes.allPlaneInformation().map {
                PlaneInfo.fromGrpcResponses(it.first, it.second)
            }
        )
    }
}