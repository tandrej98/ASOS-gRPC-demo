package sk.stuba.fei.asos.project24.dispatching.grpc

import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.flow.Flow
import sk.stuba.fei.asos.project24.plane.*

class PlaneClient(
    clientAddress: String,
    clientPort: Int
) {
    private val channel = ManagedChannelBuilder.forAddress(clientAddress, clientPort).usePlaintext().build()
    private val stub = PlaneGrpcKt.PlaneCoroutineStub(channel)
    lateinit var locationFlow: Flow<Location>

    suspend fun initPlane(): Long {
        return stub.register(RegisterRequest.getDefaultInstance())
            .also {
                locationFlow = stub.followLocation(LocationRequest.getDefaultInstance())
            }.id
    }

    suspend fun currentLocation(): Location {
        return stub.currentLocation(LocationRequest.getDefaultInstance())
    }

    suspend fun planeInformation(): PlaneInfo {
        return stub.information(PlaneInfoRequest.getDefaultInstance())
    }

}