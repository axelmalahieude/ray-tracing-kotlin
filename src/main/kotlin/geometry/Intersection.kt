package geometry

import objects.SceneObject

data class Intersection(
    val point: Vector,
    val obj: SceneObject
)