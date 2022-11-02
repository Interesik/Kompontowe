package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    void getValue() {
        SudokuField f = new SudokuField();
        f.setValue(1);
        assertEquals(1,f.getValue());
    }

    @Test
    void setValue() {
        SudokuField f = new SudokuField();
        f.setValue(1);
        assertEquals(1,f.getValue());
        f.setValue(2);
        assertEquals(2,f.getValue());
    }
}