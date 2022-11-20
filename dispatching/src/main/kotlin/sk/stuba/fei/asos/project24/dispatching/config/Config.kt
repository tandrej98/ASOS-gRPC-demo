package sk.stuba.fei.asos.project24.dispatching.config

import sk.stuba.fei.asos.project24.dispatching.EnvKeys

object Config {
    val discoveryStart: Int = System.getenv(EnvKeys.DISCOVERY_START)?.toInt() ?: 1
    val discoveryEnd: Int = System.getenv(EnvKeys.DISCOVERY_END)?.toInt() ?: 2
}