package sk.stuba.fei.asos.project24.plane.grpc

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import mu.KotlinLogging
import sk.stuba.fei.asos.project24.plane.*
import sk.stuba.fei.asos.project24.plane.config.Config
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.seconds

class PlaneService(
    coroutineContext: CoroutineContext = Dispatchers.Default,
    locations: List<Location>
): PlaneGrpcKt.PlaneCoroutineImplBase(coroutineContext) {
    private val log = KotlinLogging.logger {}
    private val locationIterator = locations.iterator()
    private lateinit var currentLocation: Location

    init {
//        TODO: log plane ID
        log.info("Starting plane service of plane {}", "TODO: ID")
        if (locations.isEmpty()) throw IllegalArgumentException("Locations list is empty")
    }

    override fun followLocation(request: LocationRequest) = flow {
        while (context.isActive) {
            if (locationIterator.hasNext()) {
                currentLocation = locationIterator.next()
            }
            emit(currentLocation)
            delay(Config.delaySeconds.seconds)
        }
    }

    override suspend fun currentLocation(request: LocationRequest): Location {
        return currentLocation
    }

    override suspend fun information(request: PlaneInfoRequest): PlaneInfo {
//        TODO: return real plane info object
        return PlaneInfo.newBuilder().build()
    }
}