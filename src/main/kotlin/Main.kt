import objects.Sphere
import objects.Disk
import geometry.Vector
import graphics.Camera
import graphics.Scene
import graphics.Viewport
import raytrace.Raytracer
import java.awt.Color


fun main() {
    val width = 480
    val height = 480

    // Set up camera and viewport matrix
    val lookat = Vector(0.0, 0.0, 5.0, Vector.POINT)
    val eye = Vector(0.0, 1.0, -1.0, Vector.POINT)
    val camera = Camera(eye, lookat)
    val viewport = Viewport(width, height, camera)

    // Set up the scene; 1 light and 2 spheres
    val lightPos = Vector(-2.0, 3.5, 0.0, Vector.POINT)
    val scene = Scene(lightPos)
    val sphere1 = Sphere(lookat, 2.0, Color(255, 0, 0, 255))
    scene.addObject(sphere1)
    val sphere2 = Sphere(Vector(-0.5, 0.5, 1.9, Vector.POINT), .4, Color(0, 0, 255, 255))
    scene.addObject(sphere2)
    val plane = Disk(Vector.VEC_J, Vector(0.0, -3.0, 5.0, Vector.POINT), 3.0)
    scene.addObject(plane)
    val plane2 = Disk(Vector.VEC_I * -1.0, Vector(3.0, -1.0, 5.0, Vector.POINT), 3.0)
    scene.addObject(plane2)

    // render the scene and output the result to a png image
    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

