package geometry

import java.awt.Color

interface SceneObject {
    fun intersect(ray: Ray): Vector?
    fun lambertianReflectance(pos: Vector, lightPos: Vector): Color
}