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

    val lookat = Vector(0.0, 0.0, 5.0, Vector.POINT)
    val eye = Vector(0.0, 0.0, -1.0, Vector.POINT) // eye starts such that image plane is xy-plane
    val newEye = Vector(0.1, 0.1, 1.5, Vector.POINT)
    val oldCameraDir = lookat - eye
    val newCameraDir = lookat - newEye
    val axis = LinearAlgebra.crossProduct(oldCameraDir, newCameraDir).normalized()
    val angle = acos(LinearAlgebra.dotProduct(oldCameraDir.normalized(), newCameraDir.normalized())) // this is correct
    val cameraRotation = Mat4.rotation(axis, angle)
    val cameraTranslation = Mat4.translation(newEye - eye)
    val cameraTransform = cameraTranslation * cameraRotation
    println(newEye.normalized())
    println((cameraTransform * eye).normalized())


    val viewport = Viewport(width, height, eye, cameraTransform)

    val lightPos = Vector(-2.0, 2.0, 0.0, Vector.POINT)
    val scene = Scene(lightPos)
    val sphere1 = Sphere(Vector(0.0, 0.0, 5.0, Vector.POINT), 2.0, Color(255, 0, 0, 255))
    scene.addObject(sphere1)
    val sphere2 = Sphere(Vector(-0.5, 0.5, 1.9, Vector.POINT), .4, Color(0, 0, 255, 255))
    scene.addObject(sphere2)

    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

