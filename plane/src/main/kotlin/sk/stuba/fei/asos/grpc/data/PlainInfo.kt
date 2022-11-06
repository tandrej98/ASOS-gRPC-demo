package sk.stuba.fei.asos.grpc.data

import kotlinx.serialization.Serializable

@Serializable
data class PlainInfo(
    private val id: Long,
    private val type: String,
    private val name: String,
    private val capacity: Int,
    private val startDestination: String,
    private val finalDestination: String,
    private var latitude: Double,
    private var longitude: Double
){
    fun setLatitude(latitude: Double) {
        this.latitude += latitude
    }

    fun setLongitude(longitude: Double) {
        this.longitude += longitude
    }
}