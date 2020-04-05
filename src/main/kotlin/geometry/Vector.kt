package geometry

import kotlin.math.sqrt

class Vector() {

    companion object {
        val VEC_I = Vector(1.0, 0.0, 0.0)
        val VEC_J = Vector(0.0, 1.0, 0.0)
        val VEC_K = Vector(0.0, 0.0, 1.0)
    }

    var i: Double = 0.0
    var j: Double = 0.0
    var k: Double = 0.0

    constructor(c: Coordinate) : this() {
        this.i = c.x
        this.j = c.y
        this.k = c.z
    }

    constructor(i : Double, j : Double, k : Double) : this() {
        this.i = i
        this.j = j
        this.k = k
    }

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