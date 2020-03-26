package geometry

import java.awt.Color

interface SceneObject {
    val center : Coordinate

    fun intersect(ray: Ray): Coordinate?
    fun contains(ray: Ray): Boolean
    fun lambertianReflectance(pos: Coordinate, lightPos: Coordinate): Color
}