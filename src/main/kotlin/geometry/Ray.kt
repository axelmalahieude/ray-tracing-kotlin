package geometry

class Ray(
    val origin: Coordinate,
    val direction: Vector
) {
    fun pointAlongRay(t: Double): Coordinate {
        return origin + direction * t
    }
}