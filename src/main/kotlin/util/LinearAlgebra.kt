package util

import geometry.Coordinate
import geometry.Vector
import kotlin.math.sqrt

/**
 * Various linear algebra computations
 */
object LinearAlgebra {

    fun normalize(v: Vector): Vector {
        return v / sqrt(v.i * v.i + v.j * v.j + v.k * v.k)
    }

    /**
     * Find vector from start to end coordinate
     */
    fun calcVector(start: Coordinate, end: Coordinate): Vector {
        return Vector(end.x - start.x, end.y - start.y, end.z - start.z)
    }

    fun dotProduct(a: Vector, b: Vector): Double {
        return a.i * b.i + a.j * b.j + a.k * b.k
    }

    fun crossProduct(a: Vector, b: Vector): Vector {
        val ii = a.j * b.k - a.k * b.j
        val jj = a.k * b.i - a.i * b.k
        val kk = a.i * b.j - a.j * b.i
        return Vector(ii, jj, kk)
    }

    fun distance(c1: Coordinate, c2: Coordinate): Double {
        return sqrt((c1.x - c2.x) * (c1.x - c2.x) + (c1.y - c2.y) * (c1.y - c2.y) + (c1.z - c2.z) * (c1.z - c2.z))
    }
}