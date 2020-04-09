import geometry.Mat4
import geometry.Sphere
import geometry.Vector
import graphics.Scene
import graphics.Viewport
import raytrace.Raytracer
import util.LinearAlgebra
import java.awt.Color
import kotlin.math.acos


fun main() {

    // Set up camera and image plane
    val width = 480
    val height = 480


    val eye = Vector(0.0, 0.0, -1.0, Vector.POINT) // eye starts such that image plane is xy-plane
    val newEye = Vector(0.01, 0.01, 0.0, Vector.POINT)
    val axis = LinearAlgebra.crossProduct(eye, newEye).normalized()
    val angle = acos(LinearAlgebra.dotProduct(eye.normalized(), newEye.normalized()))
    val cameraTransform = Mat4.rotateAroundPoint(axis, angle, Vector(0.0, 0.0, 5.0, Vector.POINT))

//    val cameraTransform = Mat4().lookAt(eye, Vector(0.0, 0.0, 5.0, Vector.POINT), Vector.VEC_J)


    val viewport = Viewport(width, height, eye, cameraTransform)

    val lightPos = Vector(0.0, 4.0, 5.0, Vector.POINT)
    val scene = Scene(lightPos)
    val sphere1 = Sphere(Vector(0.0, 0.0, 5.0, Vector.POINT), 2.0, Color(255, 0, 0, 255))
    scene.addObject(sphere1)
    val sphere2 = Sphere(Vector(-0.5, 0.5, 1.9, Vector.POINT), .4, Color(0, 0, 255, 255))
    scene.addObject(sphere2)

    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

