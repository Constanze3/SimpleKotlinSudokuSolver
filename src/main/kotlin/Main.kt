package org.example

val examplePuzzle1 = arrayOf(
    intArrayOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
    intArrayOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
    intArrayOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
    intArrayOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
    intArrayOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
    intArrayOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
    intArrayOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
    intArrayOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
    intArrayOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
)

fun main() {
    val sudoku = Sudoku(examplePuzzle1)
    println(sudoku)
    solve(sudoku)
    println(sudoku)
}
