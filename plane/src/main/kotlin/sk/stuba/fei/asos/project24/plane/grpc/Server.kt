package sk.stuba.fei.asos.project24.plane.grpc

import io.grpc.Server
import io.grpc.ServerBuilder
import kotlinx.coroutines.Dispatchers
import mu.KotlinLogging
import sk.stuba.fei.asos.project24.plane.config.Config
import java.util.concurrent.Executors

class Server {
    private val log = KotlinLogging.logger {}
    private val server: Server = ServerBuilder
        .forPort(Config.grpcPort)
        .addService(PlaneService(Dispatchers.Default))
        .build()
    private val executor = Executors.newSingleThreadExecutor()

    fun start() {
        log.info("Starting gRPC API server on port {}", Config.grpcPort)
        executor.submit {
            server.start()
            server.awaitTermination()
        }
    }
}