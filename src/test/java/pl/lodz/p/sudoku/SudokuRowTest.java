package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {

    @Test
    void verify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getRow(1).verify());
        s.setIndex(1,2,s.getIndex(1,1));
        assertFalse(s.getRow(1).verify());
    }
}