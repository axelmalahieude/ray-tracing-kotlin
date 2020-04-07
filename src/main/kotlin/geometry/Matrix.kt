package geometry

class Matrix(
    val nRows: Int,
    val nCols: Int
) {
    val rows = DoubleArray(nRows)
    val cols = DoubleArray(nCols)
}