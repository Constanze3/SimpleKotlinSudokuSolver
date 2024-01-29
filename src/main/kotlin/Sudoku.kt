package org.example

/**
 * Represents a Sudoku puzzle
 *
 * @property data the data of the puzzle
 */
class Sudoku(private val structure: Array<IntArray>) {
    val data: Array<Array<SudokuCell>>

    constructor(sudoku: Sudoku) : this(sudoku.structure) {
        for (i in 0..<9) {
            for (j in 0..<9) {
                data[i][j].value = sudoku.data[i][j].value
            }
        }
    }

    init {
        val temp = Array(9) {
            arrayOfNulls<SudokuCell>(9)
        }

        for (j in 0..<9) {
            for (i in 0..<9) {
                val value = try {
                    structure[j][i]
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

    /**
     * Checks whether a cell located at a certain position in a Sudoku is valid.
     *
     * Meaning if the cell is not empty:
     * - it is unique in its column
     * - it is unique in its row
     * - it is unique in its block
     *
     * @param position the position of the cell to check
     * @return true if the cell at the provided position is valid else false
     */
    fun isValidCell(position: Position2D): Boolean {
        val value = data[position.x][position.y].value

        // validate row
        for (i in 0..<9) {
            if (i == position.x) continue
            if (data[i][position.y].value == value) {
                return false
            }
        }

        // validate column
        for (j in 0..<9) {
            if (j == position.y) continue
            if (data[position.x][j].value == value) {
                return false;
            }
        }

        // validate block
        val blockX = position.x / 3 * 3
        val blockY = position.y / 3 * 3
        for (i in 0..<3) {
            for (j in 0..<3) {
                if (blockX + i == position.x && blockY + j == position.y) continue
                if (data[blockX + i][blockY + j].value == value) {
                    return false;
                }
            }
        }

        return true
    }

    /**
     * Checks whether the Sudoku is solved.
     *
     * @return true if the sudoku is solved else false
     */
    fun isSolved(): Boolean {
        for (i in 0..<9) {
            for (j in 0..<9) {
                val value = data[i][j].value
                if (value == null || !isValidCell(Position2D(i, j))) {
                    return false
                }
            }
        }

        return true
    }

    override fun toString(): String {
        return buildString {
            val appendBar: () -> Unit = { appendLine("-".repeat(13)) }
            for (j in 0..<9) {
                if (j % 3 == 0) appendBar()
                for (i in 0..<9) {
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