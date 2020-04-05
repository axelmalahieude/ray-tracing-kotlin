package geometry

class Coordinate() {

    var x: Double = 0.0
    var y: Double = 0.0
    var z: Double = 0.0

    constructor(v: Vector) : this() {
        this.x = v.i
        this.y = v.j
        this.z = v.k
    }

    constructor(i : Double, j : Double, k : Double) : this() {
        this.x = i
        this.y = j
        this.z = k
    }
    operator fun plus(arg: Vector): Coordinate {
        return Coordinate(arg.i + x, arg.j + y, arg.k + z)
    }

    operator fun minus(arg: Coordinate): Vector {
        return Vector(x - arg.x, y - arg.y, z - arg.z)
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }
}