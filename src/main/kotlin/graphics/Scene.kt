package graphics

import geometry.Coordinate
import geometry.Intersection
import geometry.Ray
import geometry.SceneObject
import util.LinearAlgebra

/**
 * Holds all of the objects in the scene to be rendered
 * Can handle intersection calculation
 */
class Scene(
    val lightPos: Coordinate // TODO: Add support for multiple lights
) {
    private val objects : MutableList<SceneObject> = mutableListOf()

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
        objects.forEach {
            val point = it.intersect(ray)
            if (point != null) {
                val dist = LinearAlgebra.distance(ray.origin, point)
                if (dist < currDist) {
                    intersection = Intersection(point, it)
                    currDist = dist
                }
            }
        }
        return intersection
    }
}