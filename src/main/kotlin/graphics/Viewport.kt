package graphics

import geometry.Mat4
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
    val height: Int, // number of rows
    val eye: Vector,
    val cameraTransform: Mat4
) {
    private val imagePlaneZ = 0.0 // image plane is xy-plane

    init {
    }

    /**
     * Calculate ray from eye to center of specified pixel
     */
    fun getRay(row: Int, col: Int): Ray {
        val pixel = cameraTransform * getPixelCoordinate(row, col)
        val origin = (cameraTransform * eye)
        val direction = pixel - origin
        return Ray(origin, direction)
    }

    /**
     * Get camera coordinates for a specific pixel in image plane.
     * Adjusted to get center of pixel, instead of a corner of the pixel
     *
     * See https://www.scratchapixel.com/lessons/3d-basic-rendering/ray-tracing-generating-camera-rays/generating-camera-rays
     */
    private fun getPixelCoordinate(row: Int, col: Int): Vector {
        val x = 2 * (col + 0.5) / width - 1
        val y = 1 - 2 * (row + 0.5) / height
        val z = imagePlaneZ

        return Vector(x, y, z, Vector.POINT)
    }
}