package objects

import geometry.Ray
import geometry.Vector
import util.LinearAlgebra
import java.awt.Color

class Disk( // disk is easier to model than a plane
    val normal: Vector,
    val center: Vector,
    val radius: Double = 10.0
): SceneObject {

    init {
        if (!normal.isVector() || !center.isPoint()) {
            throw Exception("Bad plane initialization; check normal and center point")
        }
    }

    override fun intersect(ray: Ray): Double? {
        val numerator = (center - ray.origin).dot(normal)
        val denominator = ray.direction.dot(normal)
        if (denominator == 0.0) {
            // ray and plane are parallel, no intersection
            return null
        }
        val t = numerator / denominator
        val intersectionPoint = ray.pointAlongRay(t) // TODO: this calculation is done twice; look into optimizing
        if (LinearAlgebra.distance(intersectionPoint, center) > radius) return null
        return t
    }

    override fun lambertianReflectance(pos: Vector, lightPos: Vector): Color {
        return Color(255, 255, 255, 255)
    }
}