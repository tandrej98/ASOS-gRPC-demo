package sk.stuba.fei.asos.project24.plane.data

import sk.stuba.fei.asos.project24.plane.EnvKeys
import sk.stuba.fei.asos.project24.plane.Type

object PlaneData {
    val id: String = System.getenv(EnvKeys.PLANE_ID_KEY)
        ?: throw IllegalArgumentException("Plane ID cannot be empty")
    val type: Type = Type.valueOf(System.getenv(EnvKeys.PLANE_TYPE_KEY))
    val name: String = System.getenv(EnvKeys.PLANE_NAME_KEY)
        ?: throw IllegalArgumentException("Plane name cannot be empty")
    val capacity: Int? = System.getenv(EnvKeys.PLANE_CAP_KEY)?.toInt()
    val start: String = System.getenv(EnvKeys.PLANE_START_KEY)
        ?: throw IllegalArgumentException("Plane starting place cannot be empty")
    val destination: String = System.getenv(EnvKeys.PLANE_DEST_KEY)
        ?: throw IllegalArgumentException("Plane destination destination ")
}
