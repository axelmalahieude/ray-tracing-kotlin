package geometry

import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Vector implementation that uses homogeneous coordinates;
 * i.e., vectors in 3D space represented with 4 coordinate, the 4th
 * signifying whether it is a vector or a point
 */
class Vector(
    var x: Double,
    var y: Double,
    var z: Double,
    var w: Double
) {

    // Ensure homogeneous coordinates; if w != 1.0 or 0.0 then illegal vector operations likely occurred;
    // examples are arithmetic between point and vector and summing two points, and possibly matrix operations
    init {
        if (w != 0.0 && w != 1.0) {
            throw InstantiationException("Expected homogeneous coordinate 1 or 0; was $w")
        }
    }

    companion object {
        val POINT = 1.0
        val VECTOR = 0.0
        val VEC_I = Vector(1.0, 0.0, 0.0, 0.0)
        val VEC_J = Vector(0.0, 1.0, 0.0, 0.0)
        val VEC_K = Vector(0.0, 0.0, 1.0, 0.0)
        val VEC_W = Vector(0.0, 0.0, 0.0, 1.0)
    }

    fun normalized(): Vector {
        val magnitude = sqrt(x * x + y * y + z * z)
        return Vector(x / magnitude, y / magnitude, z / magnitude, w)
    }

    fun parallel(v: Vector): Boolean {
        return abs(this.normalized().dot(v.normalized())) == 1.0
    }

    fun dot(v: Vector): Double {
        return x * v.x + y * v.y + z * v.z
    }

    fun isPoint(): Boolean {
        return w == POINT
    }

    fun isVector(): Boolean {
        return w == VECTOR
    }

    /**
     * Vector scaling
     */
    operator fun times(n: Double): Vector {
        return Vector(x * n, y * n, z * n, w) // homogeneous coordinate doesn't change
    }
    operator fun div(n: Double): Vector {
        return this * (1 / n)
    }

    /**
     * Vector arithmetic
     */
    operator fun minus(v: Vector): Vector {
        return Vector(x - v.x, y - v.y, z - v.z, w - v.w)
    }
    operator fun plus(v: Vector): Vector {
        return Vector(x + v.x, y + v.y, z + v.z, w + v.w)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Vector) return false
        return other.w == w && other.y == y && other.z == z && other.x == x
    }

    override fun toString(): String {
        return "<$x, $y, $z, $w>"
    }
}