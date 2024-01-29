package org.example

class Sudoku(structure: Array<IntArray>) {
    val data: Array<Array<SudokuCell>>

    init {
        val temp = Array(9) {
            arrayOfNulls<SudokuCell>(9)
        }

        for (j in 0..<9) {
            for (i in 0..<9) {
                val value = try {
                    structure[i][j]
                } catch (_: IndexOutOfBoundsException) {
                    throw IllegalArgumentException("cell $i $j does not exits")
                }

                temp[i][j] = when (value) {
                    0 -> SudokuCell.emptyNormal(i, j)
                    in 1..9 -> SudokuCell.given(i, j, value)
                    else -> throw IllegalArgumentException("cell $i $j has invalid value: $value")
                }
            }
        }

        data = temp.map { row -> row.requireNoNulls() }.toTypedArray()
    }

    override fun toString(): String {
        return buildString {
            val appendBar: () -> Unit = { appendLine("-".repeat(13)) }
            for (j in 0..<9) {
                if (j % 3 == 0) appendBar()
                for (i in 0..<9) {
                    val pos = Position2D(i, j)
                    if (i % 3 == 0) append("|")
                    append(data[i][j].value ?: "x")
                    if (i == 8) append("|")
                }
                append("\n")
            }
            appendBar()
        }
    }
}