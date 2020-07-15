import objects.Sphere
import objects.Disk
import geometry.Vector
import graphics.Camera
import graphics.Material
import graphics.Scene
import graphics.Viewport
import raytrace.Raytracer
import java.awt.Color


fun main() {
    val width = 480
    val height = 480

    // (alpha component in bits 24-31)*, the red component in bits 16-23, the green component in bits 8-15, and the blue component in bits 0-7
    // *only if hasAlpha = true
    val red = 255 shl 16
    val green = 255 shl 8
    val blue = 255
    val white = red or blue or green

    // Set up camera and viewport matrix
    val lookat = Vector(0.0, 0.0, 5.0, Vector.POINT)
    val eye = Vector(0.0, 1.0, -1.0, Vector.POINT)
    val camera = Camera(eye, lookat)
    val viewport = Viewport(width, height, camera)

    // Set up the scene; 1 light and 2 spheres
    val lightPos = Vector(-2.0, 3.5, 0.0, Vector.POINT)
    val scene = Scene(lightPos)

    val sphere1 = Sphere(Material(1.0, 1.0, 1.0, Color(red)), lookat, 2.0)
    val sphere2 = Sphere(Material(1.0, 1.0, 1.0, Color(green)), Vector(-0.5, 0.5, 1.9, Vector.POINT), .4)
    val plane = Disk(Material(1.0, 1.0, 1.0, Color(white)), Vector.VEC_J, Vector(0.0, -3.0, 5.0, Vector.POINT), 3.0)
    val plane2 = Disk(Material(1.0, 1.0, 1.0, Color(white)), Vector.VEC_I * -1.0, Vector(3.0, -1.0, 5.0, Vector.POINT), 3.0)

    scene.addObject(sphere1)
    scene.addObject(sphere2)
    scene.addObject(plane)
    scene.addObject(plane2)

    // render the scene and output the result to a png image
    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

