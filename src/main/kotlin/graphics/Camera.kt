package graphics

import geometry.Mat4
import geometry.Vector
import util.LinearAlgebra
import kotlin.math.acos

class Camera(
    val eye: Vector,
    val lookAt: Vector
) {
    companion object {
        val STARTING_POSITION = Vector(0.0, 0.0, -1.0, Vector.POINT)
    }

    var transform = Mat4.identity()

    init {
        val axis = LinearAlgebra.crossProduct(STARTING_POSITION, eye).normalized()
        val angle = acos(LinearAlgebra.dotProduct(STARTING_POSITION, eye.normalized()))
        transform = Mat4.rotateAroundPoint(axis, angle, lookAt)
    }
}