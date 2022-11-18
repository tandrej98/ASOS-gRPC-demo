package sk.stuba.fei.asos.project24.plane.config

import sk.stuba.fei.asos.project24.plane.EnvKeys

object Config {
    val grpcPort: Int = System.getenv(EnvKeys.CONFIG_PORT_KEY)?.toInt() ?: 8080
    val delaySeconds: Long = System.getenv(EnvKeys.CONFIG_DELAY_KEY)?.toLong() ?: 5L
}