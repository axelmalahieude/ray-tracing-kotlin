package graphics

import geometry.Ray
import geometry.Vector
import util.LinearAlgebra

/**
 * Handles all camera and image plane calculations
 *
 * Does not handle field of view or non-equal side lengths (non-unit aspect ratio)
 */
class Viewport(
    val width: Int, // number of columns
    val height: Int // number of rows
) {
    private val imagePlaneZ = 0.0 // image plane is xy-plane

    // locate camera at -xMax to have 90 degree field of view
    private val eye = Vector(0.0, 0.0, -1.0)

    /**
     * Calculate ray from eye to center of specified pixel
     */
    fun getRay(row: Int, col: Int): Ray {
        val direction = getPixelCoordinate(row, col) - eye
        return Ray(eye, direction)
    }

    /**
     * Get camera coordinates for a specific pixel in image plane.
     * Adjusted to get center of pixel, instead of a corner of the pixel
     *
     * bottom left = row 0, column 0
     * top right   = row xMax, column xMax
     *
     * See https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-generating-camera-rays/generating-camera-rays
     */
    private fun getPixelCoordinate(row: Int, col: Int): Vector {
        val x = 2 * (col + 0.5) / width - 1
        val y = 1 - 2 * (row + 0.5) / height
        val z = imagePlaneZ
//        val x = -xMax + col + 0.5
//        val y = -yMax + row + 0.5

        return Vector(x, y, z)
    }
}