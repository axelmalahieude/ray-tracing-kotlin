package objects

import geometry.Ray
import geometry.Vector
import graphics.Material
import util.LinearAlgebra as LA
import java.awt.Color
import kotlin.math.abs
import kotlin.math.sqrt

class Sphere(
    override val material: Material,
    private val center : Vector,
    private val radius : Double
) : SceneObject {

    /**
     * Determine if a ray intersects this sphere
     */
    override fun intersect(ray: Ray): Double? {
        val centeredRayStart = ray.origin - center

        val b = 2 * LA.dotProduct(ray.direction, centeredRayStart)
        val c = LA.dotProduct(centeredRayStart, centeredRayStart) - radius * radius

        val discriminant = b * b - 4 * c
        return when {
            // ensure intersections are in front of the ray by checking for positive intersection times
            discriminant == 0.0 -> {
                -b / 2
            }
            discriminant > 0.0 -> {
                val sq = sqrt(discriminant)
                val t0 = (-b + sq) / 2
                val t1 = (-b - sq) / 2
                when {
                    // return closest intersection
                    abs(t0) > abs(t1) -> t1
                    abs(t1) > abs(t0) -> t0
                    else -> null
                }
            }
            else -> null // discriminant < 0.0
        }
    }

    override fun colorAt(pos: Vector, lightPos: Vector): Color {
        val lightVector = (lightPos - pos).normalized()
        val normal = (pos - center).normalized()
        var dot = LA.dotProduct(lightVector, normal).toFloat()
        if (dot < 0) dot = 0F
        if (dot > 1) dot = 1F
        val incomingIntensity = 1 // in case we need to change this later

        // Lambertian Reflectance: I = L*N*c*i (dot product of light and normal, multiplied by intensity and color)
        val r = dot * material.color.red * incomingIntensity
        val g = dot * material.color.green * incomingIntensity
        val b = dot * material.color.blue * incomingIntensity
        val alpha = material.color.alpha.toFloat() // don't scale alpha
        return Color(r / 255, g / 255 , b / 255, alpha / 255)
    }

//    fun contains(ray: Ray): Boolean {
//        return LinearAlgebra.distance(ray.origin, center) <= radius + 1 // adjust for acne/rounding errors
//    }
}