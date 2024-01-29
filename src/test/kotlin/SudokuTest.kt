import org.example.Position2D
import org.example.Sudoku
import org.junit.jupiter.api.Test

class SudokuTest {
    private val testPuzzle1 = arrayOf(
        intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 0),
        intArrayOf(9, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(8, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(7, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(6, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(5, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(4, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(3, 0, 0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    )

    @Test
    fun validInsertion() {
        val sudoku = Sudoku(testPuzzle1)
        sudoku.data[8][0].value = 9
        assert(sudoku.isValidCell(Position2D(8, 0)))
    }

    @Test
    fun invalidInsertionBecauseOfRow() {
        val sudoku = Sudoku(testPuzzle1)
        sudoku.data[8][0].value = 8
        assert(!sudoku.isValidCell(Position2D(8, 0)))
    }

    @Test
    fun invalidInsertionBecauseOfColumn() {
        val sudoku = Sudoku(testPuzzle1)
        sudoku.data[0][8].value = 1
        assert(!sudoku.isValidCell(Position2D(0, 8)))
    }

    @Test
    fun invalidInsertionBecauseOfBlock() {
        val sudoku = Sudoku(testPuzzle1)
        sudoku.data[1][1].value = 9
        assert(!sudoku.isValidCell(Position2D(1, 1)))
    }

    private val testPuzzle2Solved = arrayOf(
        intArrayOf(4, 3, 5, 2, 6, 9, 7, 8, 1),
        intArrayOf(6, 8, 2, 5, 7, 1, 4, 9, 3),
        intArrayOf(1, 9, 7, 8, 3, 4, 5, 6, 2),
        intArrayOf(8, 2, 6, 1, 9, 5, 3, 4, 7),
        intArrayOf(3, 7, 4, 6, 8, 2, 9, 1, 5),
        intArrayOf(9, 5, 1, 7, 4, 3, 6, 2, 8),
        intArrayOf(5, 1, 9, 3, 2, 6, 8, 7, 4),
        intArrayOf(2, 4, 8, 9, 5, 7, 1, 3, 6),
        intArrayOf(7, 6, 3, 4, 1, 8, 2, 5, 9)
    )

    @Test
    fun testSolved() {
        val sudoku = Sudoku(testPuzzle2Solved)
        assert(sudoku.isSolved())
    }
}