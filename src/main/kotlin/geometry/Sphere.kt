package geometry

import util.LinearAlgebra
import java.awt.Color
import kotlin.math.min
import kotlin.math.sqrt

class Sphere(
    private val center : Coordinate,
    private val radius : Double,
    var color : Color
) : SceneObject {

    /**
     * Determine if a ray intersects this sphere
     */
    override fun intersect(ray: Ray): Coordinate? {
        val centeredRayStart = ray.origin - center

        val b = 2 * LinearAlgebra.dotProduct(ray.direction, centeredRayStart)
        val c = LinearAlgebra.dotProduct(centeredRayStart, centeredRayStart) - radius * radius

        val discriminant = b * b - 4 * c
        return when {
            // ensure intersections are in front of the ray by checking for positive intersection times
            discriminant == 0.0 -> {
                val t = -b / 2
                if (t > 0) ray.pointAlongRay(t)
                else null
            }
            discriminant > 0.0 -> {
                val sq = sqrt(discriminant)
                val t0 = (-b + sq) / 2
                val t1 = (-b - sq) / 2
                if (t0 > 0 && t1 > 0) {
                    ray.pointAlongRay(min(t0, t1))
                } else null
            }
            else -> null // discriminant < 0.0
        }
    }

    override fun lambertianReflectance(pos: Coordinate, lightPos: Coordinate): Color {
        val lightVector = LinearAlgebra.normalize(LinearAlgebra.calcVector(pos, lightPos))
        val normal = LinearAlgebra.normalize(LinearAlgebra.calcVector(center, pos))
        var dot = LinearAlgebra.dotProduct(lightVector, normal).toFloat()
        if (dot < 0) dot = 0F
        if (dot > 1) dot = 1F
        val incomingIntensity = 1 // in case we need to change this later

        // Lambertian Reflectance: I = L*N*c*i (dot product of light and normal, multiplied by intensity and color)
        val r = dot * color.red * incomingIntensity
        val g = dot * color.green * incomingIntensity
        val b = dot * color.blue * incomingIntensity
        val alpha = color.alpha.toFloat() // don't scale alpha
        return Color(r / 255, g / 255 , b / 255, alpha / 255)
    }

//    fun contains(ray: Ray): Boolean {
//        return LinearAlgebra.distance(ray.origin, center) <= radius + 1 // adjust for acne/rounding errors
//    }
}