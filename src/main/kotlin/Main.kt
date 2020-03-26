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

    val scene = Scene()
    val sphere = Sphere(Coordinate(0.0, 0.0, 300.0), 250.0, Color(255, 0, 0, 255))
    scene.addObject(sphere)

    val raytracer = Raytracer(viewport, scene)
    raytracer.render(width, height)

    val image = Image(width, height)
    image.randomlyAssignPixels()
    image.outputToFile()

}

