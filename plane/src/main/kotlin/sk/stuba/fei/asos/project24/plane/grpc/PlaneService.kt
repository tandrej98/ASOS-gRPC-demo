package sk.stuba.fei.asos.project24.plane.grpc

import io.grpc.Status
import io.grpc.StatusRuntimeException
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
    private var _currentLocation: Location? = null
    private val currentLocation: Location
        get() = _currentLocation ?: throw StatusRuntimeException(Status.INTERNAL)

    init {
        log.info("Starting plane service of plane ID: {}", PlaneData.id)
        if (!locationIterator.hasNext()) throw IllegalArgumentException("Locations list is empty")
//      A race condition between this thread and the emitting coroutine can occur
//       but this solution is enough for this demonstration
        Dispatchers.Default.dispatch(context) {
            while (true) {
                if (locationIterator.hasNext()) {
                    _currentLocation = locationIterator.next()
                }
                Thread.sleep(Config.delaySeconds * 1000)
            }
        }
    }

    override fun followLocation(request: LocationRequest) = flow {
        emit(currentLocation)
        delay(Config.delaySeconds.seconds)
    }

    override suspend fun currentLocation(request: LocationRequest) = currentLocation

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

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        return RegisterResponse.newBuilder().setId(PlaneData.id).build()
    }
}