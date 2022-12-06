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

    init {
        log.info("Starting plane service of plane ID: {}", PlaneData.id)
    }

    override fun followLocation(request: LocationRequest) = flow {
        while (true) {
            emit(PlaneLocation.currentLocation)
            delay(Config.delaySeconds.seconds)
        }
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

    override suspend fun register(request: RegisterRequest): RegisterResponse {
        return RegisterResponse.newBuilder().setId(PlaneData.id).build()
    }
}