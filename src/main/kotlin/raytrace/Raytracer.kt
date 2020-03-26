package raytrace

import graphics.Scene
import graphics.Viewport

class Raytracer(
    val viewport: Viewport,
    val scene: Scene
) {

    fun render(width: Int, height: Int) {
        var nInts = 0
        for (r in 0 until height) {
            for (c in 0 until width) {
                // determine ray from eye through pixel
                val ray = viewport.getRay(r, c)

                // find closest intersection of ray with an object
                val intersection = scene.findIntersection(ray)

                //////testing
                if (intersection != null) nInts++
//                println("$r, $c, $intersection")

                //////testing end

                // cast shadow ray to the light source
                // recursively cast reflected and refracted ray
                // calculate pixel color
                // paint pixel
            }
        }
        println("Number intersections: $nInts")
        println("Total pixels: ${height * width}")
    }
}