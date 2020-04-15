package graphics

import geometry.Mat4
import geometry.Vector
import util.LinearAlgebra
import kotlin.math.acos

class Camera(
    val eye: Vector,
    lookAt: Vector
) {
    companion object {
        // eye starts such that image plane is xy-plane
        val STARTING_POSITION = Vector(0.0, 0.0, -1.0, Vector.POINT) // see Viewport.kt
    }

    var transform = Mat4.identity()

    init {
        val oldCameraDir = (lookAt - STARTING_POSITION).normalized()
        val newCameraDir = (lookAt - eye).normalized()
        val axis = LinearAlgebra.crossProduct(oldCameraDir, newCameraDir).normalized()
        val angle = acos(LinearAlgebra.dotProduct(oldCameraDir, newCameraDir))
        val cameraRotation = if (oldCameraDir.parallel(newCameraDir)) {
            Mat4.identity() // no need to rotate our camera if we're looking down same axis as START
        } else {
            Mat4.rotateAroundPoint(axis, angle, STARTING_POSITION) // rotate START around itself
        }
        val cameraTranslation = Mat4.translation(eye - STARTING_POSITION) // move to new eye location
        // TODO: Need final rotation in accordance with up direction so camera isn't skewed

        transform = cameraTranslation * cameraRotation

        if (transform * STARTING_POSITION != eye) {
            throw Exception("Bad camera rotation matrix; check parameters")
        }
    }
}