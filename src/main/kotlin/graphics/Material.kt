package graphics

import java.awt.Color

data class Material (
    val specular: Double,
    val diffuse: Double,
    val ambient: Double,
    val color: Color
)