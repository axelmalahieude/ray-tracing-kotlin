import geometry.Mat4
import geometry.Sphere
import geometry.Vector
import graphics.Camera
import graphics.Scene
import graphics.Viewport
import raytrace.Raytracer
import util.LinearAlgebra
import java.awt.Color
import kotlin.math.acos


fun main() {
    val width = 480
    val height = 480

    // Set up camera and viewport matrix
    val lookat = Vector(0.0, 0.0, 5.0, Vector.POINT)
    val eye = Vector(-1.0, -1.0, 0.0, Vector.POINT)
    val camera = Camera(eye, lookat)
    val viewport = Viewport(width, height, camera)

    // Set up the scene; 1 light and 2 spheres
    val lightPos = Vector(-2.0, 2.0, 0.0, Vector.POINT)
    val scene = Scene(lightPos)
    val sphere1 = Sphere(lookat, 2.0, Color(255, 0, 0, 255))
    scene.addObject(sphere1)
    val sphere2 = Sphere(Vector(-0.5, 0.5, 1.9, Vector.POINT), .4, Color(0, 0, 255, 255))
    scene.addObject(sphere2)

    // render the scene and output the result to a png image
    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

