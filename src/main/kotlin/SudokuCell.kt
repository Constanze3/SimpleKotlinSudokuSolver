package org.example

/**
 * A cell of a Sudoku puzzle.
 *
 * @property position the position of the cell
 * @property value the value of the cell (can be numbers 1-9)
 * @property isGiven whether this cell is a given (unmodifiable) cell
 */
class SudokuCell(position: Position2D, value: Int?, val isGiven: Boolean) : Cell<Int>(position, value) {
    constructor(x: Int, y: Int, value: Int?, isGiven: Boolean) : this(Position2D(x, y), value, isGiven)

    init {
        this.value?.let {
            if (it < 1 || 9 < it) {
                throw IllegalArgumentException("sudoku cells may only have values 1-9, but ${this.value} was provided")
            }
        }
    }

    companion object {
        /**
         * Constructs a new given SudokuCell with the provided position and value.
         *
         * @param position the position of the cell
         * @param value the value of the cell
         */
        fun given(position: Position2D, value: Int): SudokuCell {
            return SudokuCell(position, value, true)
        }

        /**
         * Constructs a new given SudokuCell with the provided position and value.
         *
         * @param x the x coordinate of the cell
         * @param y the y coordinate of the cell
         * @param value the value of the cell
         */
        fun given(x: Int, y: Int, value: Int): SudokuCell {
            return given(Position2D(x, y), value)
        }

        /**
         * Constructs a new empty, normal cell with the provided position.
         * @param position the position of the cell
         */
        fun emptyNormal(position: Position2D): SudokuCell {
            return SudokuCell(position, null, false)
        }

        /**
         * Constructs a new empty, normal cell with the provided position.
         * @param x the x coordinate of the cell
         * @param y the y coordinate of the cell
         */
        fun emptyNormal(x: Int, y: Int): SudokuCell {
            return emptyNormal(Position2D(x, y))
        }
    }
}