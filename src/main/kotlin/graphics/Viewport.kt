package graphics

import geometry.Ray
import geometry.Vector
import util.LinearAlgebra

/**
 * Handles all camera and image plane calculations
 *
 * ASSUMPTIONS
 * - camera is looking down z axis, centered at (0, 0, n)
 * - image plane is centered on z axis, directly in front of camera
 * - each pixel has dimensions 1x1 units in camera space (makes math easy)
 */
class Viewport(
    width: Int, // number of columns
    height: Int // number of rows
) {
    private val xMax = width / 2.0
    private val yMax = height / 2.0
    private val imagePlaneZ = 0.0 // image plane is xy-plane

    // locate camera at -xMax to have 90 degree field of view
    private val eye = Vector(0.0, 0.0, -xMax)

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
     */
    private fun getPixelCoordinate(row: Int, col: Int): Vector {
        val x = -xMax + col + 0.5
        val y = -yMax + row + 0.5

        return Vector(x, y, imagePlaneZ)
    }
}