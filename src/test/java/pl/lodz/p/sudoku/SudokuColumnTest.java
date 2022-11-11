package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuColumnTest {
/**
     * Test SudokuColumn verify
     * Sprawdzenie czy verify poprawnie sprawdza uzupełnioną kolumnę.
     */
    @Test
    void verify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getColumn(1).verify());
        s.setIndex(1,1,s.getIndex(2,1));
        assertFalse(s.getColumn(1).verify());

    }

    @Test
    void testEquals() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getColumn(0).equals(s.getColumn(0)));
        assertFalse(s.getColumn(0).equals(s.getColumn(3)));
        assertFalse(s.getColumn(0).equals(null));
        SudokuColumn column = s.getColumn(0);
        assertTrue(column.equals(column));
    }

    @Test
    void testHashCode() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertEquals(s.getColumn(0).hashCode(),s.getColumn(0).hashCode());
        assertNotEquals(s.getColumn(0).hashCode(),s.getColumn(3).hashCode());
    }

    @Test
    void testToString() {
        SudokuField f = new SudokuField();
        SudokuField f2 = new SudokuField();
        f.setValue(1);
        f2.setValue(2);
        List<SudokuField> columnList = new ArrayList<>();
        columnList.add(f);
        columnList.add(f2);
        SudokuColumn column = new SudokuColumn(columnList);
        assertEquals(column.getClass().getSimpleName()+"[column=[SudokuField[value=1], SudokuField[value=2]]]",column.toString());
    }
}
