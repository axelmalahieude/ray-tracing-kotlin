import geometry.Coordinate
import geometry.Vector
import util.LinearAlgebra.normalize

data class Coordinate2D(val x: Double, val y: Double)

fun main() {
    //
    // Set up camera and image plane, with everything along z axis

    // set up camera
    val eye = Coordinate(0.0, 0.0, 0.0)

    // set up image plane
    val width = 480.0
    val height = 480.0

    // center image plane at (0, 0) by recomputing bounds
    val maxX = width / 2
    val maxY = height / 2
    val lowerLeft = Coordinate2D(-maxX, -maxY)
    println(lowerLeft)

//    val scene = Scene()
//    val sphere = Sphere(Coordinate(0.0, 0.0, 0.0), 1.0, Color(255, 0, 0, 255))
//    scene.addObject(sphere)
}

// move all this to a new viewport class that handles it all
fun calcPixelCoordinate(r: Int, c: Int) {
    // row, column

}

