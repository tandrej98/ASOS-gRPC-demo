package sk.stuba.fei.asos.project24.plane

import sk.stuba.fei.asos.project24.plane.grpc.Server

fun main() {
    val grpcServer = Server()
    grpcServer.start()
}