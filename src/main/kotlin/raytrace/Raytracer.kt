package raytrace

import geometry.Ray
import graphics.Image
import graphics.Scene
import graphics.Viewport
import java.awt.Color

class Raytracer(
    private val viewport: Viewport,
    private val scene: Scene
) {

    fun render(width: Int, height: Int): Image {
        val image = Image(width, height)
        for (r in 0 until height) {
            for (c in 0 until width) {
                // determine ray from eye through pixel
                val ray = viewport.getRay(r, c)

                // find closest intersection of ray with an object
                val (intPoint, intObj) = scene.findIntersection(ray) ?: continue

                // cast shadow ray to the light source
                val shadowRayDirection = scene.lightPos - intPoint
                val shadowRay = Ray(intPoint, shadowRayDirection)
                if (scene.findIntersection(shadowRay) != null) {
                    // no contribution from this light source; something is blocking it
                    image.pixels[r][c] = Color(0)
                } else {
                    // no intersection between point and light source; light illuminates this point
                    image.pixels[r][c] = intObj.colorAt(intPoint, scene.lightPos)
                }

                // recursively cast reflected and refracted ray
                // calculate pixel color
                // paint pixel
            }
        }
        return image
    }
}