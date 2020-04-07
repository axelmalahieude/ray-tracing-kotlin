package geometry

class Mat4 {

    // TODO: Implement a "look at" method to angle the camera appropriately

    private val SIZE = 4

    // Initialize matrix to identity
    private val entries : Array<Array<Double>> = Array(SIZE) { Array(SIZE) {0.0} }
    init {
        for (i in 0 until SIZE) {
            entries[i][i] = 1.0
        }
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
     * Shortcut functions for linear transformations;
     * same as above but with ints instead of doubles
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