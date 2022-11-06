package sk.stuba.fei.asos.grpc.utils

import sk.stuba.fei.asos.grpc.data.PlainInfo
import kotlin.random.Random

private var latitude = 42.12345678
private var longitude = 17.45678341
var planeData = PlainInfo(12345, "Boeing 747", "Best Plane", 524, "Paris", "London", latitude, longitude)

fun setLocation() {
    planeData.setLatitude(Random.nextDouble(-0.5, 0.5))
    planeData.setLongitude(Random.nextDouble(-0.5, 0.5))
}