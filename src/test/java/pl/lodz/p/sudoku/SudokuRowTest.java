package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getRow(0).equals(s.getRow(0)));
        assertFalse(s.getRow(0).equals(s.getRow(3)));
    }

    @Test
    void testHashCode() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertEquals(s.getRow(0).hashCode(),s.getRow(0).hashCode());
        assertNotEquals(s.getRow(0).hashCode(),s.getRow(3).hashCode());
    }

    @Test
    void testToString() {
        SudokuField f = new SudokuField();
        SudokuField f2 = new SudokuField();
        f.setValue(1);
        f2.setValue(2);
        List<SudokuField> rowList = new ArrayList<>();
        rowList.add(f);
        rowList.add(f2);
        SudokuRow row = new SudokuRow(rowList);
        assertEquals(row.getClass().getSimpleName()+"[row=[SudokuField[value=1], SudokuField[value=2]]]",row.toString());
    }
}
