package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DullSudokuSolverTest {
    @Test
    void solve() {
        SudokuBoard s = new SudokuBoard();
        DullSudokuSolver solver = new DullSudokuSolver();
        assertFalse(solver.solve(s));
    }
}