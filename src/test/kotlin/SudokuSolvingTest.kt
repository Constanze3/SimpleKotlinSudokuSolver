import org.example.Cell
import org.example.Sudoku
import org.example.isValidInsertion
import org.example.solve
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SudokuSolvingTest {
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
        assert(isValidInsertion(sudoku, Cell(8, 0, 1)))
    }

    @Test
    fun invalidInsertionBecauseOfRow() {
        val sudoku = Sudoku(testPuzzle1)
        assert(!isValidInsertion(sudoku, Cell(8, 0, 8)))
    }

    @Test
    fun invalidInsertionBecauseOfColumn() {
        val sudoku = Sudoku(testPuzzle1)
        assert(!isValidInsertion(sudoku, Cell(0, 8, 1)))
    }

    @Test
    fun invalidInsertionBecauseOfBlock() {
        val sudoku = Sudoku(testPuzzle1)
        assert(!isValidInsertion(sudoku, Cell(1, 1, 9)))
    }

    private val testPuzzle2 = arrayOf(
        intArrayOf(0, 0, 0, 2, 6, 0, 7, 0, 1),
        intArrayOf(6, 8, 0, 0, 7, 0, 0, 9, 0),
        intArrayOf(1, 9, 0, 0, 0, 4, 5, 0, 0),
        intArrayOf(8, 2, 0, 1, 0, 0, 0, 4, 0),
        intArrayOf(0, 0, 4, 6, 0, 2, 9, 0, 0),
        intArrayOf(0, 5, 0, 0, 0, 3, 0, 2, 8),
        intArrayOf(0, 0, 9, 3, 0, 0, 0, 7, 4),
        intArrayOf(0, 4, 0, 0, 5, 0, 0, 3, 6),
        intArrayOf(7, 0, 3, 0, 1, 8, 0, 0, 0)
    )

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

    private fun extractContent(sudoku: Sudoku): Array<IntArray> {
        return sudoku.data.map { col -> col.map { cell -> cell.value ?: 0 }.toIntArray() }.toTypedArray()
    }

    @Test
    fun testSolve() {
        val sudoku = Sudoku(testPuzzle2)
        solve(sudoku)
        val result = extractContent(sudoku)
        assertEquals(testPuzzle2Solved, result)
    }
}