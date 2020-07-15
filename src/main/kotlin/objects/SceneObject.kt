package objects

import geometry.Ray
import geometry.Vector
import graphics.Material
import java.awt.Color

interface SceneObject {
    val material: Material
    fun intersect(ray: Ray): Double?
    fun colorAt(pos: Vector, lightPos: Vector): Color
}