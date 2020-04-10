package graphics

import geometry.Mat4
import geometry.Vector
import util.LinearAlgebra
import kotlin.math.acos

class Camera(
    eye: Vector,
    lookAt: Vector
) {
    companion object {
        // eye starts such that image plane is xy-plane
        val STARTING_POSITION = Vector(0.0, 0.0, -1.0, Vector.POINT) // see Viewport.kt
    }

    var transform = Mat4.identity()
    // TODO: Investigate why transform * STARTING_POSITION != eye; slightly off
    // effect is somewhat neutralized by explicitly using eye instead of transform * STARTING_POSITION
    // this implies that the transform matrix isn't correct
    // therefore the image may be slightly distorted since we need to use transform matrix to transform
    //   each of the pixel coordinates

    init {
        val oldCameraDir = (lookAt - STARTING_POSITION).normalized()
        val newCameraDir = (lookAt - eye).normalized()
        val axis = LinearAlgebra.crossProduct(oldCameraDir, newCameraDir).normalized()
        val angle = acos(LinearAlgebra.dotProduct(oldCameraDir, newCameraDir))
        val cameraRotation = Mat4.rotation(axis, angle)
        val cameraTranslation = Mat4.translation(eye - STARTING_POSITION)
        // TODO: Need final rotation in accordance with up direction so camera isn't skewed
        transform = cameraTranslation * cameraRotation

        println(transform * STARTING_POSITION)
        println(eye)
    }
}