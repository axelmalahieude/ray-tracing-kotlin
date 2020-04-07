package graphics

import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_ARGB
import java.io.File
import java.util.Date
import java.sql.Timestamp
import javax.imageio.ImageIO
import kotlin.math.min

/**
 * Stores information for each pixel
 */
class Image(
    private val width : Int,
    private val height: Int
) {
    private val imageDirectory = File("images")
    private val fileSeparator = System.getProperty("file.separator")

    val pixels : Array<Array<Color>> = Array(height) { Array(width) { Color(0, 0, 0, 255) } }

    fun randomlyAssignPixels() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                pixels[i][j] = Color(min(i, 255), min(j, 255), 0, 255)
            }
        }
    }

    fun outputToFile() {
        val bufferedImage = BufferedImage(width, height, TYPE_INT_ARGB)
        for (r in 0 until height) {
            for (c in 0 until width) {
                val pixel = pixels[r][c] // make height go from MAX -> 0 otherwise image is flipped
                bufferedImage.setRGB(c, r, pixel.rgb)
            }
        }

        if (!imageDirectory.exists()) {
            imageDirectory.mkdir()
        }
        val fileName = Timestamp(Date().time).toString().replace(':', '-').split('.')[0] + ".png"
        println(fileName)
        val outputFile = File("$imageDirectory$fileSeparator$fileName")
        outputFile.createNewFile()
        ImageIO.write(bufferedImage, "png", outputFile)
    }
}