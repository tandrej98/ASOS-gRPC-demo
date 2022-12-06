package sk.stuba.fei.asos.project24.plane

import sk.stuba.fei.asos.project24.plane.data.PlaneLocation
import sk.stuba.fei.asos.project24.plane.grpc.Server

fun main() {
    PlaneLocation //Trigger the init block
    val grpcServer = Server()
    grpcServer.start()
}