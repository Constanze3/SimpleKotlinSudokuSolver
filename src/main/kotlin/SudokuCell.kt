package org.example

class SudokuCell(position: Position2D, value: Int?, val isGiven: Boolean) : Cell<Int>(position, value) {
    constructor(x: Int, y: Int, value: Int?, isGiven: Boolean) : this(Position2D(x, y), value, isGiven)

    companion object {
        fun given(position: Position2D, value: Int): SudokuCell {
            return SudokuCell(position, value, true)
        }

        fun given(x: Int, y: Int, value: Int): SudokuCell {
            return given(Position2D(x, y), value)
        }

        fun emptyNormal(position: Position2D): SudokuCell {
            return SudokuCell(position, null, false)
        }

        fun emptyNormal(x: Int, y: Int): SudokuCell {
            return emptyNormal(Position2D(x, y))
        }
    }
}