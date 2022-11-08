package sk.stuba.fei.asos.project24.plane.data

import sk.stuba.fei.asos.project24.plane.Location

//TODO: implement production of planes location
//TODO: does not have to be an object, here just for completeness
object PlaneLocation {
    val locations: List<Location> = listOf(
        Location.newBuilder().setLatitude(0.0).setLongitude(0.0).build()
    )
}
