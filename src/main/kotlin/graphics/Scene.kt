package graphics

import geometry.SceneObject

class Scene() {
    /**
     * Holds all of the objects in the scene to be rendered
     * Can handle intersection calculation
     */

    private val objects : MutableList<SceneObject> = mutableListOf()

    fun addObject(obj : SceneObject) {
        objects.add(obj)
    }

    fun isInitialized() : Boolean {
        return objects.isNotEmpty()
    }
}