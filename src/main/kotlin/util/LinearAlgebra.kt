package util

import geometry.Vector
import kotlin.math.sqrt

/**
 * Various linear algebra computations
 */
object LinearAlgebra {

    fun dotProduct(a: Vector, b: Vector): Double {
        return a.i * b.i + a.j * b.j + a.k * b.k
    }

    fun crossProduct(a: Vector, b: Vector): Vector {
        val ii = a.j * b.k - a.k * b.j
        val jj = a.k * b.i - a.i * b.k
        val kk = a.i * b.j - a.j * b.i
        return Vector(ii, jj, kk)
    }

    fun distance(c1: Vector, c2: Vector): Double {
        return sqrt((c1.i - c2.i) * (c1.i - c2.i) + (c1.j - c2.j) * (c1.j - c2.j) + (c1.k - c2.k) * (c1.k - c2.k))
    }
}