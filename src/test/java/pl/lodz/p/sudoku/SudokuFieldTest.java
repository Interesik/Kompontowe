package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {
/**
     * Test SudokuField getValue
     * Sprawdzenie czy getValue odwołuje się do poprawnej wartości.
     */
    @Test
    void getValue() {
        SudokuField f = new SudokuField();
        f.setValue(1);
        assertEquals(1,f.getValue());
    }
/**
     * Test SudokuField setValue
     * Sprawdzenie czy setValue przekazuje poprawną wartość.
     */
    @Test
    void setValue() {
        SudokuField f = new SudokuField();
        f.setValue(1);
        assertEquals(1,f.getValue());
        f.setValue(2);
        assertEquals(2,f.getValue());
    }

    @Test
    void testEquals() {
        SudokuField tE = new SudokuField();
        assertTrue(tE.equals(tE));
        SudokuField tE2 = new SudokuField();
        assertTrue(tE.equals(tE2));
        tE2.setValue(4);
        tE.setValue(3);
        assertFalse(tE.equals(tE2));
        assertFalse(tE.equals(this));
        assertFalse(tE.equals(null));
    }

    @Test
    void testHashCode() {
        SudokuField tE = new SudokuField();
        assertEquals(tE.hashCode(),tE.hashCode());
        SudokuField tE2 = new SudokuField();
        assertEquals(tE.hashCode(),tE2.hashCode());
        tE2.setValue(4);
        assertNotEquals(tE.hashCode(),tE2.hashCode());
    }

    @Test
    void testToString() {
        SudokuField tE = new SudokuField();
        assertEquals(tE.toString(),tE.getClass().getSimpleName()+"[value=0]");
    }
}
