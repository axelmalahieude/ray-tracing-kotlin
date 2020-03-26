import geometry.Coordinate
import geometry.Sphere
import graphics.Scene
import graphics.Viewport
import raytrace.Raytracer
import java.awt.Color


fun main() {

    // Set up camera and image plane
    val width = 4
    val height = 4
    val viewport = Viewport(width, height)

    val scene = Scene()
    val sphere = Sphere(Coordinate(0.0, 100.0, 200.0), 100.0, Color(255, 0, 0, 255))
    scene.addObject(sphere)

    val raytracer = Raytracer(viewport, scene)
    raytracer.render(width, height)

    // testing


}

