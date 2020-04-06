package geometry

import kotlin.math.sqrt

class Vector(
    var i: Double,
    var j: Double,
    var k: Double
) {

    companion object {
        val VEC_I = Vector(1.0, 0.0, 0.0)
        val VEC_J = Vector(0.0, 1.0, 0.0)
        val VEC_K = Vector(0.0, 0.0, 1.0)
    }

    fun normalized(): Vector {
        val magnitude = sqrt(i * i + j * j + k * k)
        return Vector(i / magnitude, j / magnitude, k / magnitude)
    }

    /**
     * Vector scaling
     */
    operator fun times(n: Double): Vector {
        return Vector(i * n, j * n, k * n)
    }
    operator fun div(n: Double): Vector {
        return this * (1 / n)
    }

    /**
     * Vector arithmetic
     */
    operator fun minus(v: Vector): Vector {
        return Vector(i - v.i, j - v.j, k - v.k)
    }
    operator fun plus(v: Vector): Vector {
        return Vector(i + v.i, j + v.j, k + v.k)
    }

    override fun toString(): String {
        return "<$i, $j, $k>"
    }
}