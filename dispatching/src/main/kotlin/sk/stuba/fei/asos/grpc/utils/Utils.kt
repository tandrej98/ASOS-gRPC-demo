package sk.stuba.fei.asos.grpc.utils

import sk.stuba.fei.asos.grpc.data.PlaneInfo
import kotlin.random.Random

val planes = listOf(
    PlaneInfo(1, "Boeing 747", "Best Plane", 524, "Paris", "London", createLat(), createLon()),
    PlaneInfo(2, "Airbus A380", "Fastest Plane", 853, "Canada", "Germany", createLat(), createLon()),
    PlaneInfo(3, "Airbus A320", "Awesome Plane", 200, "Prague", "Barcelona", createLat(), createLon()),
    PlaneInfo(4, "Boeing 727", "Most Safety Plane", 129, "China", "Japan", createLat(), createLon())
)

fun getPlaneById(id: Long): PlaneInfo? {
    return planes.find { it.getId() == id }
}

fun setNewLocation(planeData: PlaneInfo?) {
    planeData?.setLatitude(Random.nextDouble(-0.5, 0.5))
    planeData?.setLongitude(Random.nextDouble(-0.5, 0.5))
}

fun createLat(): Double = Random.nextDouble(-90.0, 90.0)
fun createLon(): Double = Random.nextDouble(-180.0, 180.0)