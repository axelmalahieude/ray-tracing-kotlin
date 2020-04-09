package geometry

import util.LinearAlgebra
import kotlin.math.cos
import kotlin.math.sin

class Mat4 {

    companion object {
        const val SIZE = 4
        fun calcRotation(from: Vector, to: Vector): Mat4 {
            val i = from.normalized()
            val k = LinearAlgebra.crossProduct(from, to).normalized()
            val j = LinearAlgebra.crossProduct(k, from).normalized()
            return Mat4(i, j, k, Vector(0.0, 0.0, 0.0, 1.0))
        }

        fun identity(): Mat4 {
            return Mat4()
        }

        fun scale(factor: Double): Mat4 {
            // note that this matrix only looks nice because it is symmetric
            return Mat4(Vector.VEC_I * factor, Vector.VEC_J * factor, Vector.VEC_K * factor, Vector.VEC_W)
        }

        fun translation(x: Double, y: Double, z: Double): Mat4 {
            return Mat4(Vector.VEC_I, Vector.VEC_J, Vector.VEC_K, Vector(x, y, z, Vector.POINT))
        }

        /**
         * Rotation angle should be in radians, axis should be normalized
         * https://en.wikipedia.org/wiki/Rotation_matrix
         */
        fun rotation(axis: Vector, angle: Double): Mat4 {
            val i = Vector(
                cos(angle) + axis.x * axis.x * (1 - cos(angle)),
                axis.y * axis.x * (1 - cos(angle)) + axis.z * sin(angle),
                axis.z * axis.x * (1 - cos(angle)) - axis.y * sin(angle),
                Vector.VECTOR
            )
            val j = Vector(
                axis.x * axis.y * (1 - cos(angle)) - axis.z * sin(angle),
                cos(angle) + axis.y * axis.y * (1 - cos(angle)),
                axis.z * axis.y * (1 - cos(angle)) + axis.x * sin(angle),
                Vector.VECTOR
            )
            val k = Vector(
                axis.x * axis.z * (1 - cos(angle)) + axis.y * sin(angle),
                axis.y * axis.z * (1 - cos(angle)) - axis.x * sin(angle),
                cos(angle) + axis.z * axis.z * (1 - cos(angle)),
                Vector.VECTOR
            )
            val w = Vector(0.0, 0.0, 0.0, 1.0)
            return Mat4(i, j, k, w)
        }

        fun rotateAroundPoint(axis: Vector, angle: Double, point: Vector): Mat4 {
            val translationToOrigin = translation(-point.x, -point.y, -point.z)
            val rotation = rotation(axis, angle)
            val translationBack = translation(point.x, point.y, point.z)
            return translationBack * rotation * translationToOrigin
        }
    }

    private val entries : Array<Array<Double>> = Array(SIZE) { Array(SIZE) {0.0} }

    private constructor() {
        // identity matrix by default
        for (i in 0 until SIZE) {
            entries[i][i] = 1.0
        }
    }

    private constructor(i: Vector, j: Vector, k: Vector, p: Vector) {
        entries[0][0] = i.x
        entries[1][0] = i.y
        entries[2][0] = i.z
        entries[3][0] = i.w

        entries[0][1] = j.x
        entries[1][1] = j.y
        entries[2][1] = j.z
        entries[3][1] = j.w

        entries[0][2] = k.x
        entries[1][2] = k.y
        entries[2][2] = k.z
        entries[3][2] = k.w

        entries[0][3] = p.x
        entries[1][3] = p.y
        entries[2][3] = p.z
        entries[3][3] = p.w
    }

    private constructor(m00: Double, m01: Double, m02: Double, m03: Double,
                m10: Double, m11: Double, m12: Double, m13: Double,
                m20: Double, m21: Double, m22: Double, m23: Double,
                m30: Double, m31: Double, m32: Double, m33: Double) {
        entries[0][0] = m00
        entries[1][0] = m10
        entries[2][0] = m20
        entries[3][0] = m30

        entries[0][1] = m01
        entries[1][1] = m11
        entries[2][1] = m21
        entries[3][1] = m31

        entries[0][2] = m02
        entries[1][2] = m12
        entries[2][2] = m22
        entries[3][2] = m32

        entries[0][3] = m03
        entries[1][3] = m13
        entries[2][3] = m23
        entries[3][3] = m33
    }

    /**
     * Overrides for matrix * vector and matrix * matrix
     */

    operator fun times(v: Vector): Vector {
        val i = v.x * entries[0][0] + v.y * entries[0][1] + v.z * entries[0][2] + v.w * entries[0][3]
        val j = v.x * entries[1][0] + v.y * entries[1][1] + v.z * entries[1][2] + v.w * entries[1][3]
        val k = v.x * entries[2][0] + v.y * entries[2][1] + v.z * entries[2][2] + v.w * entries[2][3]
        val w = v.x * entries[3][0] + v.y * entries[3][1] + v.z * entries[3][2] + v.w * entries[3][3]
        return Vector(i, j, k, w)
    }

    operator fun times(m: Mat4): Mat4 {
        val product = Mat4()
        for (r in 0 until SIZE) {
            for (c in 0 until SIZE) {
                var sum = 0.0
                for (i in 0 until SIZE) {
                    sum += entries[r][i] * m.entries[i][c]
                }
                product.entries[r][c] = sum
            }
        }
        return product
    }

    override fun toString(): String {
        var string = "Matrix...\n"
        for (r in 0 until SIZE) {
            for (c in 0 until SIZE) {
                string += "${entries[r][c]}  "
            }
            string += "\n"
        }
        return "$string...end matrix"
    }
}