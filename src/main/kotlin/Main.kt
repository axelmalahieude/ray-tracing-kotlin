import geometry.Mat4
import geometry.Sphere
import geometry.Vector
import graphics.Scene
import graphics.Viewport
import raytrace.Raytracer
import java.awt.Color


fun main() {

    // Set up camera and image plane
    val width = 480
    val height = 480
    val cameraTransform = Mat4().translate(-3, 1, 0)
    val viewport = Viewport(width, height, cameraTransform)

    val lightPos = Vector(-2.0, 2.0, 0.0, Vector.POINT)
    val scene = Scene(lightPos)
    val sphere1 = Sphere(Vector(0.0, 0.0, 5.0, Vector.POINT), 2.0, Color(255, 0, 0, 255))
    scene.addObject(sphere1)
    val sphere2 = Sphere(Vector(-0.5, 0.5, 1.9, Vector.POINT), .4, Color(0, 0, 255, 255))
    scene.addObject(sphere2)

    val matrix = Mat4().scale(5).translate(1, 2, 3)
    println(matrix)

    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

