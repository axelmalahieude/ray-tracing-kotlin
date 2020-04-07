package geometry

import util.LinearAlgebra

class Ray(
    val origin: Vector,
    dirUnnormalized: Vector
) {

    // Take care of normalizing the direction in here so we don't miss it elsewhere
    val direction = dirUnnormalized.normalized()

    fun pointAlongRay(t: Double): Vector {
        return origin + direction * t
    }

    override fun toString(): String {
        return "origin: $origin, direction: $direction"
    }
}