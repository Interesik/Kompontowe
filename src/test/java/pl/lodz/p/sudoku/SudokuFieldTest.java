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
}
