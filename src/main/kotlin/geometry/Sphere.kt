package geometry

import util.LinearAlgebra
import java.awt.Color
import kotlin.math.min
import kotlin.math.sqrt

class Sphere(
    override val center : Coordinate,
    private val radius : Double,
    var color : Color = Color(255, 0, 0, 255)
) : SceneObject {

    /**
     * Determine if a ray intersects this sphere
     */
    override fun intersect(ray: Ray): Coordinate? {
        val centeredRayStart = ray.origin - center

        val a = 1
        val b = 2 * LinearAlgebra.dotProduct(ray.direction, centeredRayStart)
        val c = LinearAlgebra.dotProduct(centeredRayStart, centeredRayStart) - radius * radius

        val discriminant = b * b - 4 * a  * c
        return when {
            // no intersection
            discriminant == 0.0 -> ray.pointAlongRay(-b / (2 * a))
            discriminant > 0.0 -> {
                val sq = sqrt(discriminant)
                val t0 = (-b + sq) / (2 * a)
                val t1 = (-b - sq) / (2 * a)
                val intersection = min(t0, t1)
                ray.pointAlongRay(intersection)
            }
            else -> null // discriminant < 0.0
        }
    }

    // a way to find where line intersects point (will need direction, since there will be 2 points)

}