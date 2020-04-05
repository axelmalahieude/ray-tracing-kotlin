package geometry

import java.awt.Color

interface SceneObject {
    fun intersect(ray: Ray): Coordinate?
    fun lambertianReflectance(pos: Coordinate, lightPos: Coordinate): Color
}