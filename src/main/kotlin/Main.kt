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
    val viewport = Viewport(width, height)

    val lightPos = Vector(-500.0, 500.0, 0.0)
    val scene = Scene(lightPos)
    val sphere1 = Sphere(Vector(0.0, 0.0, 300.0), 250.0, Color(255, 0, 0, 255))
    scene.addObject(sphere1)
    val sphere2 = Sphere(Vector(-200.0, 200.0, 100.0), 50.0, Color(0, 0, 255, 255))
    scene.addObject(sphere2)

    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

