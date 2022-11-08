package sk.stuba.fei.asos.project24.plane.grpc

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flow
import mu.KotlinLogging
import sk.stuba.fei.asos.project24.plane.*
import sk.stuba.fei.asos.project24.plane.config.Config
import sk.stuba.fei.asos.project24.plane.data.PlaneData
import sk.stuba.fei.asos.project24.plane.data.PlaneLocation
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration.Companion.seconds

class PlaneService(
    coroutineContext: CoroutineContext = Dispatchers.Default
): PlaneGrpcKt.PlaneCoroutineImplBase(coroutineContext) {
    private val log = KotlinLogging.logger {}
    private val locationIterator = PlaneLocation.locations.iterator()
    private lateinit var currentLocation: Location

    init {
        log.info("Starting plane service of plane ID: {}", PlaneData.id)
        if (!locationIterator.hasNext()) throw IllegalArgumentException("Locations list is empty")
    }

//    Location updates only while the dispatching is following the plane. Not Ideal
//    but Ok considering the scope of this demo.
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
        return PlaneInfo.newBuilder()
            .setId(PlaneData.id)
            .setName(PlaneData.name)
            .setType(PlaneData.type)
            .setCapacity(PlaneData.capacity ?: 0)
            .setStartCity(PlaneData.start)
            .setEndCity(PlaneData.destination)
            .build()
    }
}