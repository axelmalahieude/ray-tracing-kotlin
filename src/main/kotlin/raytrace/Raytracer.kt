package raytrace

import graphics.Scene
import graphics.Viewport

class Raytracer(
    val viewport: Viewport,
    val scene: Scene
) {

    fun render(width: Int, height: Int) {
        for (r in 0..height) {
            for (c in 0..width) {
                // determine ray from eye through pixel
                val ray = viewport.getRay(r, c)

                // find closest intersection of ray with an object
                val intersection = scene.findIntersection(ray)

                //////testing

                println("$r, $c, $intersection")

                //////testing end

                // cast shadow ray to the light source
                // recursively cast reflected and refracted ray
                // calculate pixel color
                // paint pixel
            }
        }
    }
}