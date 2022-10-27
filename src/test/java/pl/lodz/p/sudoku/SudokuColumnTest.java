package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuColumnTest {

    @Test
    void verify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getColumn(1).verify());
        s.setIndex(1,1,s.getIndex(2,1));
        assertFalse(s.getColumn(1).verify());
    }
}