package geometry

import util.LinearAlgebra
import java.awt.Color
import java.lang.Float.max
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

        val b = 2 * LinearAlgebra.dotProduct(ray.direction, centeredRayStart)
        val c = LinearAlgebra.dotProduct(centeredRayStart, centeredRayStart) - radius * radius

        val discriminant = b * b - 4   * c
        return when {
            // no intersection
            discriminant == 0.0 -> ray.pointAlongRay(-b / 2)
            discriminant > 0.0 -> {
                val sq = sqrt(discriminant)
                val t0 = (-b + sq) / 2
                val t1 = (-b - sq) / 2
                val intersection = min(t0, t1)
                ray.pointAlongRay(intersection)
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
        return Color(r / 255, g / 255, b / 255, alpha / 255)
    }
}