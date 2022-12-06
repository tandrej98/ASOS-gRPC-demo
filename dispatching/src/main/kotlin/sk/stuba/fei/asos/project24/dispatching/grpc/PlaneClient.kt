package sk.stuba.fei.asos.project24.dispatching.grpc

import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sk.stuba.fei.asos.project24.plane.*

class PlaneClient(
    clientAddress: String,
    clientPort: Int
) {
    private val channel = ManagedChannelBuilder.forAddress(clientAddress, clientPort).usePlaintext().build()
    private val stub = PlaneGrpcKt.PlaneCoroutineStub(channel)
    private lateinit var currentLocation: Location

    suspend fun initPlane(): Long {
        return stub.register(RegisterRequest.getDefaultInstance()).id
            .also {
                CoroutineScope(Dispatchers.Default).launch {
                    stub.followLocation(LocationRequest.getDefaultInstance()).collect{ currentLocation = it }
                }
            }
    }

    fun currentLocation(): Location {
        return currentLocation
    }

    suspend fun planeInformation(): PlaneInfo {
        return stub.information(PlaneInfoRequest.getDefaultInstance())
    }

}