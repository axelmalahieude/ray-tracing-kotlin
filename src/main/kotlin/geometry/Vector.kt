package geometry

import kotlin.math.sqrt

class Vector(
    var i : Double,
    var j : Double,
    var k : Double
) {

    operator fun div(n: Double): Vector {
        return Vector(i / n, j / n, k / n)
    }

    operator fun times(n: Double): Vector {
        return Vector(i * n, j * n, k * n)
    }

    operator fun minus(v: Vector): Vector {
        return Vector(i - v.i, j - v.j, k - v.k)
    }

    override fun toString(): String {
        return "<$i, $j, $k>"
    }

    fun normalized(): Vector {
        val magnitude = sqrt(i * i + j * j + k * k)
        return Vector(i / magnitude, j / magnitude, k / magnitude)
    }
}