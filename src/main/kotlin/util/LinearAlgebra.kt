package util

import geometry.Vector
import kotlin.math.sqrt

/**
 * Various linear algebra computations
 */
object LinearAlgebra {

    fun dotProduct(a: Vector, b: Vector): Double {
        return a.x * b.x + a.y * b.y + a.z * b.z
    }

    fun crossProduct(a: Vector, b: Vector): Vector {
        val ii = a.y * b.z - a.z * b.y
        val jj = a.z * b.x - a.x * b.z
        val kk = a.x * b.y - a.y * b.x
        return Vector(ii, jj, kk, Vector.VECTOR) // cross product always returns a vector
    }

    fun distance(c1: Vector, c2: Vector): Double {
        // homogeneous coordinate has no impact on distance
        return sqrt((c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y) + (c1.z - c2.z) * (c1.z - c2.z))
    }
}