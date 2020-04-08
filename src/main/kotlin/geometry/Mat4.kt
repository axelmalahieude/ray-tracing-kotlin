package geometry

import util.LinearAlgebra

class Mat4 {

    // TODO: Implement a "look at" method to angle the camera appropriately

    private val SIZE = 4

    // Initialize matrix to identity
    protected val entries : Array<Array<Double>> = Array(SIZE) { Array(SIZE) {0.0} }

    constructor() {
        for (i in 0 until SIZE) {
            entries[i][i] = 1.0
        }
    }

    constructor(x: Vector, y: Vector, z: Vector, p: Vector) {
        entries[0][0] = x.i
        entries[1][0] = x.j
        entries[2][0] = x.k
        entries[3][0] = x.w

        entries[0][1] = y.i
        entries[1][1] = y.j
        entries[2][1] = y.k
        entries[3][1] = y.w

        entries[0][2] = z.i
        entries[1][2] = z.j
        entries[2][2] = z.k
        entries[3][2] = z.w

        entries[0][3] = p.i
        entries[1][3] = p.j
        entries[2][3] = p.k
        entries[3][3] = p.w
    }

    /**
     * From UCLA CS174A tiny-graphics WebGL library
     * (I took the class in Winter 2020)
     *
     * https://github.com/encyclopedia-of-code/tiny-graphics-js
     */
    fun lookAt(eye: Vector, at: Vector, up: Vector): Mat4 {
        var k = (eye - at).normalized()
        val i = LinearAlgebra.crossProduct(up, k).normalized()
        val j = LinearAlgebra.crossProduct(k, i).normalized()
        k *= -1.0

        val translation = Mat4(Vector.VEC_I, Vector.VEC_J, Vector.VEC_K, Vector(i.dot(eye), j.dot(eye), k.dot(eye), 1.0))
        val rotation = Mat4(i, j, k, Vector(0.0, 0.0, 0.0, 1.0))

        return translation * rotation
    }

    /**
     * Linear transformations
     */

    fun scale(factor: Double): Mat4 {
        for (i in 0 until SIZE - 1) {
            entries[i][i] = entries[i][i] * factor
        }
        return this
    }

    fun translate(x: Double, y: Double, z: Double): Mat4 {
        entries[0][SIZE - 1] = entries[0][SIZE - 1] + x
        entries[1][SIZE - 1] = entries[1][SIZE - 1] + y
        entries[2][SIZE - 1] = entries[2][SIZE - 1] + z
        return this
    }

    /**
     * Overloaded shortcut functions for linear transformations
     */

    fun scale(factor: Int): Mat4 {
        return scale(factor.toDouble())
    }

    fun translate(x: Int, y: Int, z: Int): Mat4 {
        return translate(x.toDouble(), y.toDouble(), z.toDouble())
    }

    /**
     * Overrides
     */

    operator fun times(v: Vector): Vector {
        val i = v.i * entries[0][0] + v.j * entries[0][1] + v.k * entries[0][2] + v.w * entries[0][3]
        val j = v.i * entries[1][0] + v.j * entries[1][1] + v.k * entries[1][2] + v.w * entries[1][3]
        val k = v.i * entries[2][0] + v.j * entries[2][1] + v.k * entries[2][2] + v.w * entries[2][3]
        val w = v.i * entries[3][0] + v.j * entries[3][1] + v.k * entries[3][2] + v.w * entries[3][3]
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