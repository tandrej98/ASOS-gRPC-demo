package sk.stuba.fei.asos.project24.dispatching.data

import kotlinx.serialization.Serializable
import sk.stuba.fei.asos.project24.plane.Location

@Serializable
data class PlaneInfo(
    private val id: Long,
    private val type: String,
    private val name: String,
    private val capacity: Int,
    private val startDestination: String,
    private val finalDestination: String,
    private var latitude: Double,
    private var longitude: Double,
    private var status: String
){
    companion object {
        fun fromGrpcResponses(
            info: sk.stuba.fei.asos.project24.plane.PlaneInfo,
            location: Location
        ): PlaneInfo {
            return PlaneInfo(
                id = info.id,
                type = info.type.name,
                name = info.name,
                capacity = info.capacity,
                startDestination = info.startCity,
                finalDestination = info.endCity,
                latitude = location.latitude,
                longitude = location.longitude,
                status = location.status.name
            )
        }
    }
    fun setLatitude(latitude: Double) {
        this.latitude += latitude
    }

    fun setLongitude(longitude: Double) {
        this.longitude += longitude
    }

    fun getId(): Long{
        return this.id
    }
}