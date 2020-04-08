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
    val cameraTransform = Mat4().translate(0, 0, 0)
    val eye = Vector(0.0, 0.0, -1.0, Vector.POINT)
//    val cameraTransform = Mat4().lookAt(eye, Vector(0.0, 0.0, 5.0, Vector.POINT), Vector.VEC_J)
    val viewport = Viewport(width, height, eye, cameraTransform)

    val lightPos = Vector(-2.0, 2.0, 0.0, Vector.POINT)
    val scene = Scene(lightPos)
    val sphere1 = Sphere(Vector(0.0, 0.0, 5.0, Vector.POINT), 2.0, Color(255, 0, 0, 255))
    scene.addObject(sphere1)
    val sphere2 = Sphere(Vector(-0.5, 0.5, 1.9, Vector.POINT), .4, Color(0, 0, 255, 255))
    scene.addObject(sphere2)

//    val a = Mat4(Vector(5.0, 2.0, 8.0, 1.0), Vector(7.0, 3.0, 10.0, 1.0), Vector(9.0, 3.0, 2.0, 1.0), Vector(10.0, 8.0, 3.0, 1.0))
//    val b = Mat4(Vector(3.0, 12.0, 9.0, 1.0), Vector(10.0, 1.0, 10.0, 1.0), Vector(12.0, 4.0, 12.0, 1.0), Vector(18.0, 9.0, 2.0, 1.0))
//    println(a * b)

    val raytracer = Raytracer(viewport, scene)
    val output = raytracer.render(width, height)
    output.outputToFile()
}

