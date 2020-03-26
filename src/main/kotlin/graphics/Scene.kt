package graphics

import geometry.Coordinate
import geometry.Ray
import geometry.SceneObject

class Scene(
    val viewport: Viewport
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
    fun findIntersection(ray: Ray): Coordinate? {
        objects.forEach {
            val intersection = it.intersect(ray)
            // TODO: Fix this code to support if ray intersects multiple objects; return closest intersection
            if (intersection != null) {
                return intersection
            }
        }
        return null
    }
}