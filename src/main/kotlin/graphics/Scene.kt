package graphics

import geometry.Coordinate
import geometry.Intersection
import geometry.Ray
import geometry.SceneObject

class Scene(
    val lightPos: Coordinate // TODO: Add support for multiple lights
) {
    /**
     * Holds all of the objects in the scene to be rendered
     * Can handle intersection calculation
     */

    private val objects : MutableList<SceneObject> = mutableListOf()

    fun addObject(obj : SceneObject) {
        // should make sure object is completely inside viewport
        objects.add(obj)
    }

    /**
     * Determine if a ray intersects any object in this scene
     */
    fun findIntersection(ray: Ray): Intersection? {
        objects.forEach {
            val point = it.intersect(ray)
            // TODO: Fix this code to support if ray intersects multiple objects; return closest intersection
            if (point != null) {
                return Intersection(point, it)
            }
        }
        return null
    }
}