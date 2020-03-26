package geometry

class Coordinate(
    val x : Double,
    val y : Double,
    val z : Double
) {
    operator fun plus(arg: Vector): Coordinate {
        return Coordinate(arg.i + x, arg.j + y, arg.k + z)
    }

    operator fun minus(arg: Coordinate): Vector {
        return Vector(x - arg.x, y - arg.y, z - arg.z)
    }
}