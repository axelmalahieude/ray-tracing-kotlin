package geometry

interface SceneObject {
    val center : Coordinate

    fun intersect(ray: Ray): Coordinate?
}