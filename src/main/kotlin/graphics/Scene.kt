package graphics

import geometry.Intersection
import geometry.Ray
import geometry.SceneObject
import geometry.Vector
import util.LinearAlgebra

/**
 * Holds all of the objects in the scene to be rendered
 * Can handle intersection calculation
 */
class Scene(
    val lightPos: Vector // TODO: Add support for multiple lights
) {
    private val objects : MutableList<SceneObject> = mutableListOf()
    private val acne : Double = 0.05 // buffer around objects where we prevent collisions

    fun addObject(obj : SceneObject) {
        // should make sure object is completely inside viewport
        objects.add(obj)
    }

    /**
     * Determine if a ray intersects any object in this scene
     */
    fun findIntersection(ray: Ray): Intersection? {
        var intersection: Intersection? = null
        var currDist = Double.MAX_VALUE
        for (it in objects) {

            // find out if the ray intersects any object
            val intersectionTime = it.intersect(ray)
            if (intersectionTime == null || intersectionTime <= acne) continue

            // find the point at which the ray intersects the object
            val point = ray.pointAlongRay(intersectionTime)

            // determine if this is the first object the ray intersects
            val dist = LinearAlgebra.distance(ray.origin, point)
            if (dist < currDist) {
                intersection = Intersection(point, it)
                currDist = dist
            }
        }
        return intersection
    }
}