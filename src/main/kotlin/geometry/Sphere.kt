package geometry

import java.awt.Color

class Sphere(
    override val location : Coordinate,
    val radius : Double,
    var color : Color = Color(255, 0, 0, 255)
) : SceneObject {
    // will need a way to find if point is in or out of sphere
    fun intersect(line: Line) {

    }

    // a way to find where line intersects point (will need direction, since there will be 2 points)

}