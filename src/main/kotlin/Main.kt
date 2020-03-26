import geometry.Coordinate
import geometry.Sphere
import graphics.Image
import graphics.Scene
import graphics.Viewport
import raytrace.Raytracer
import java.awt.Color


fun main() {

    // Set up camera and image plane
    val width = 480
    val height = 480
    val viewport = Viewport(width, height)

    val lightPos = Coordinate(-240.0, -240.0, 0.0) // top right, in front of sphere
    val scene = Scene(lightPos)
    val sphere = Sphere(Coordinate(0.0, 0.0, 300.0), 250.0, Color(255, 0, 0, 255))
    scene.addObject(sphere)

    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

