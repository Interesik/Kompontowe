package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DullSudokuSolverTest {
    @Test
    void solve() {
        DullSudokuSolver dullSolver = new DullSudokuSolver();
        SudokuBoard s = new SudokuBoard(dullSolver);
        assertFalse(s.solveGame());
    }
}