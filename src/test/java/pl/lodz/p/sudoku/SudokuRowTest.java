package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuRowTest {
/**
     * Test SudokuRow verify
     * Sprawdzenie czy verify poprawnie sprawdza uzupełniony rząd.
     */
    @Test
    void verify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getRow(1).verify());
        s.setIndex(1,2,s.getIndex(1,1));
        assertFalse(s.getRow(1).verify());
        // Jeżeli nie wypełnione
        s.setIndex(1,2,0);
        s.setIndex(1,3,0);
        s.setIndex(1,4,0);
        s.setIndex(1,5,0);
        assertTrue(s.getRow(1).verify());
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}
