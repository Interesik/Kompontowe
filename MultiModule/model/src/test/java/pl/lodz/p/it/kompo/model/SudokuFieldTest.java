package pl.lodz.p.it.kompo.model;

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

    @Test
    void Clonable() throws CloneNotSupportedException {
        SudokuField sf = new SudokuField();
        SudokuField sf2 = sf.clone();
        assertNotSame(sf,sf2);
        assertEquals(sf,sf2);
        sf.setValue(1);
        assertNotSame(sf,sf2);
        assertNotEquals(sf,sf2);
    }

    @Test
    void compare() throws CloneNotSupportedException {
        SudokuField sf = new SudokuField();
        SudokuField sf2 = sf.clone();
        SudokuField sf3 = sf.clone();
        assertEquals(0,sf2.compareTo(sf));
        assertThrowsExactly(NullPointerException.class,()->sf.compareTo(null));
        sf.setValue(3);
        assertTrue(sf.compareTo(sf2) == -sf2.compareTo(sf));
        sf2.setValue(1);
        assertTrue(sf.compareTo(sf2) > 0 && sf2.compareTo(sf3) > 0 && sf.compareTo(sf3) > 0);
        sf.setValue(0);
        sf2.setValue(0);
        sf3.setValue(0);
        assertTrue(sf.compareTo(sf2) == 0 && sf2.compareTo(sf3) == 0 && sf.compareTo(sf3) == 0);



    }
}
