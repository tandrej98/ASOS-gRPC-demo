package sk.stuba.fei.asos.project24.plane.config

object Config {
    val grpcPort: Int = System.getenv("GRPC_PORT")?.toInt() ?: 8080
    val delaySeconds: Long = System.getenv("DELAY_SECONDS")?.toLong() ?: 5L
}