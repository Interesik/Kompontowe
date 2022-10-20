package pl.lodz.p.sudoku;

/**
 * @ Interfejs SudokuSolver.
 */
public interface SudokuSolver {
    /**
     * Funkcja solve rozwiązaując gotową planszeSudoku.
     * @param sudokuBoard - PlanszaSudoku
     * @return Zwraca True w przypadku rozwiązania sudoku,False w przypadku braku rozwiązania.
     */
    boolean solve(SudokuBoard sudokuBoard);
}
