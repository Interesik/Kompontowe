package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoxTest {
/**
     * Test SudokuBox verify
     * Sprawdzenie czy verify poprawnie sprawdza uzupełniony box.
     */
    @Test
    void verify() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getBox(1,1).verify());
        s.setIndex(1,1,s.getIndex(2,1));
        assertFalse(s.getBox(1,1).verify());
    }

    @Test
    void testEquals() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertTrue(s.getBox(0,0).equals(s.getBox(0,0)));
        assertFalse(s.getBox(0,0).equals(s.getBox(3,0)));
    }

    @Test
    void testHashCode() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        assertEquals(s.getBox(0,0).hashCode(),s.getBox(0,0).hashCode());
        assertNotEquals(s.getBox(0,0).hashCode(),s.getBox(3,0).hashCode());
    }

    @Test
    void testToString() {
        SudokuField f = new SudokuField();
        SudokuField f2 = new SudokuField();
        f.setValue(1);
        f2.setValue(2);
        List<SudokuField> boxList = new ArrayList<>();
        boxList.add(f);
        boxList.add(f2);
        SudokuBox box = new SudokuBox(boxList);
        assertEquals(box.getClass().getSimpleName()+"[box=[SudokuField[value=1], SudokuField[value=2]]]",box.toString());
    }
}
