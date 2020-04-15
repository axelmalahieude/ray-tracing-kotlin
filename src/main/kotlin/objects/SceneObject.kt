package objects

import geometry.Ray
import geometry.Vector
import java.awt.Color

interface SceneObject {
    fun intersect(ray: Ray): Double?
    fun lambertianReflectance(pos: Vector, lightPos: Vector): Color
}