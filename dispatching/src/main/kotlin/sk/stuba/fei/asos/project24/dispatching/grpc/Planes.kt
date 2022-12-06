package sk.stuba.fei.asos.project24.dispatching.grpc

import io.grpc.StatusException
import io.grpc.StatusRuntimeException
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import sk.stuba.fei.asos.project24.dispatching.config.Config
import sk.stuba.fei.asos.project24.plane.Location
import sk.stuba.fei.asos.project24.plane.PlaneInfo

private const val PLANES_GRPC_PORT = 8080
object Planes {
    private val log = KotlinLogging.logger {}
    private val registeredPlanes: MutableMap<Long, PlaneClient> = mutableMapOf()

    fun discoverPlanes() {
        (Config.discoveryStart..Config.discoveryEnd).forEach {
            val client = PlaneClient("plane-$it", PLANES_GRPC_PORT)
            runBlocking {
                try {
                    val id = client.initPlane()
                    registeredPlanes[id] = client
                    log.info("Registered plane on port {}", it)
                } catch (e: StatusException) {
                    log.warn("Plane on port {} could not be registered", it, e)
                }
            }
        }
    }

    suspend fun planeInfo(id: Long): PlaneInfo {
        return registeredPlanes[id]?.planeInformation()
            ?: throw IllegalArgumentException("Plane with id $id not registered")
    }

    fun planeCurrentLocation(id: Long): Location {
        return registeredPlanes[id]?.currentLocation()
            ?: throw IllegalArgumentException("Plane with id $id not registered")
    }

    suspend fun allPlaneInformation(): List<Pair<PlaneInfo, Location>> {
        return registeredPlanes.mapNotNull {
            try {
                (it.value.planeInformation() to it.value.currentLocation())
            } catch (e: StatusRuntimeException) {
                log.warn("Call to plane {} failed", it.key, e)
                null
            }
        }
    }
}