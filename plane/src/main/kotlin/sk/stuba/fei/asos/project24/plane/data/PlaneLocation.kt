package sk.stuba.fei.asos.project24.plane.data

import kotlinx.coroutines.*
import sk.stuba.fei.asos.project24.plane.Location
import sk.stuba.fei.asos.project24.plane.Status
import sk.stuba.fei.asos.project24.plane.location
import kotlin.math.max
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

object PlaneLocation {
    private const val LAT_LIMIT = 90
    private const val LON_LIMIT = 180
    private const val DURATION = 900.0

    private var latStart: Double = (Random.nextInt(-LAT_LIMIT, LAT_LIMIT)).toDouble()
    private val latStop: Double = Random.nextInt(-LAT_LIMIT, LAT_LIMIT).toDouble()
    private var lonStart: Double = Random.nextInt(-LON_LIMIT, LON_LIMIT).toDouble()
    private val lonStop: Double = Random.nextInt(-LON_LIMIT, LON_LIMIT).toDouble()
    private val delimLat: Double = (latStop - latStart) / DURATION
    private val delimLon: Double = (lonStop - lonStart) / DURATION
    private var counter = 0
    var currentLocation: Location = Location.getDefaultInstance()
        private set

    init {
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if (hasNext()) {
                    currentLocation = nextLocation()
                }
                delay(1.seconds)
            }
        }
    }

    private fun nextLocation() = location {
            latitude = latStart
            longitude = lonStart
            status = when (counter) {
                0, DURATION.toRange() -> Status.LANDED
                else -> Status.AIRBORNE
            }
        }.also {
            counter++
            latStart += delimLat
            lonStart += delimLon
        }

    private fun hasNext() = counter < DURATION

    private fun Double.toRange() = max((this - 1).toInt(), 0)
}
