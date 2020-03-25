package util

import geometry.Vector
import kotlin.math.sqrt

object LinearAlgebra {
    /**
     * Various linear algebra computations
     */

    fun normalize(v: Vector): Vector {
        return v / sqrt(v.i * v.i + v.j * v.j + v.k * v.k)
    }
}