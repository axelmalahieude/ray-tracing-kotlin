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
    val camera: Camera
) {

    companion object {
        const val IMAGE_PLANE_Z = 0.0 // image plane is xy-plane --> see Camera.kt
    }

    /**
     * Calculate ray from eye to center of specified pixel
     */
    fun getRay(row: Int, col: Int): Ray {
        val pixel = camera.transform * getPixelCoordinate(row, col)
        val origin = camera.transform * Camera.STARTING_POSITION // precompute this
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
        val z = IMAGE_PLANE_Z

        return Vector(x, y, z, Vector.POINT)
    }
}