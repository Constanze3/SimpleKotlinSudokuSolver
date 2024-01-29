package org.example

/**
 * A SudokuSolver can be used to find solutions of a certain sudoku puzzle state.
 *
 * @constructor creates a new solver for the given sudoku
 */
class SudokuSolver(sudoku: Sudoku) {
    private val sudoku = Sudoku(sudoku)
    private val workingSudoku = Sudoku(sudoku)
    private val solutions = mutableListOf<Sudoku>()

    /**
     * Finds and returns all solutions of the sudoku puzzle state provided to the solver.
     *
     * @return a list of solutions in the order they were found (an empty list if there are no solutions)
     */
    fun getAllSolutions(): MutableList<Sudoku> {
        while (solutionGenerator.hasNext()) {
            solutions.add(solutionGenerator.next())
        }
        return solutions
    }

    /**
     * Returns a solution iterator for the solutions of the sudoku puzzle state provided to the solver.
     *
     * Use this if finding all solutions is not necessary.
     *
     * @return a solution iterator for the solutions of the sudoku puzzle state
     */
    fun getSolutionIterator(): Iterator<Sudoku> {
        return iterator {
            var i = 0
            while (true) {
                if (i < solutions.size) {
                    yield(solutions[i])
                    i++
                } else {
                    if (solutionGenerator.hasNext()) {
                        val solution = solutionGenerator.next()
                        solutions.add(solution)
                        i++
                        yield(solution)
                    } else {
                        break
                    }
                }
            }
        }
    }

    private val solutionGenerator = solve(this.sudoku.data.flatten().filter { cell -> cell.value == null })
    private fun solve(emptyCells: List<SudokuCell>): Iterator<Sudoku> = iterator {
        if (emptyCells.isEmpty()) {
            if (workingSudoku.isSolved()) {
                yield(Sudoku(workingSudoku))
            }
        } else {
            val currentCell = emptyCells[0]

            val x = currentCell.position.x
            val y = currentCell.position.y
            for (v in 1..9) {
                workingSudoku.data[x][y].value = v
                if (workingSudoku.isValidCell(Position2D(x, y))) {
                    yieldAll(solve(emptyCells.drop(1)))
                }
            }
            workingSudoku.data[x][y].value = null
        }
    }
}
