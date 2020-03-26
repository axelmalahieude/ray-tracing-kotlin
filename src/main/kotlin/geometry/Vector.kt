package geometry

class Vector(
    val i : Double,
    val j : Double,
    val k : Double
) {

    operator fun div(n: Double): Vector {
        return Vector(i / n, j / n, k / n)
    }

    operator fun times(n: Double): Vector {
        return Vector(i * n, j * n, k * n)
    }

    override fun toString(): String {
        return "<$i, $j, $k>"
    }
}