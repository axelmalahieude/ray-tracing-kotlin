package graphics

import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.min

class Image(
    private val width : Int,
    private val height: Int
) {
    /**
     * Stores information for each pixel
     */

    val xMax = width
    val yMax = height

    private val pixels : Array<Array<Color>> = Array(yMax) { Array(xMax) { Color(0, 0, 0, 255) } }

    fun randomlyAssignPixels() {
        for (i in 0 until yMax) {
            for (j in 0 until xMax) {
                pixels[i][j] = Color(min(i, 255), min(j, 255), 0, 255)
            }
        }
    }

    fun outputToFile(fileName : String) {
        val bufferedImage = BufferedImage(width, height, TYPE_INT_ARGB)
        for (i in 0 until yMax) {
            for (j in 1 until xMax) {
                val pixel = pixels[i][j]
                bufferedImage.setRGB(j, i, pixel.rgb)
            }
        }

        val outputFile = File(fileName)
        outputFile.createNewFile()
        ImageIO.write(bufferedImage, "png", outputFile)
    }
}