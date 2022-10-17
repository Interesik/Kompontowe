package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void SudokuBoard(){
    }
    @Test
    void fillBoard() {
        SudokuBoard c = new SudokuBoard();
        SudokuBoard c2 = new SudokuBoard();
        assertTrue(c2.fillBoard());
        assertTrue(c.fillBoard());
        boolean isSame = true;
        for(int row = 0;row < 9;row++) {
            for (int col = 0;col < 9;col++) {
                if(c.getIndex(row,col) != c2.getIndex(row,col)) {
                    isSame = false;
                }
            }
        }
        assertFalse(isSame);

        for(int row = 0; row < 9; row++) {
            Set<Integer> rows = new HashSet<Integer>();
            for(int col = 0; col < 9; col++) {
                rows.add(c.getIndex(row,col));
            }
            assertEquals(rows.size(),9);
        }
        for(int col = 0; col < 9; col++) {
            Set<Integer> cols = new HashSet<Integer>();
            for(int row = 0; row < 9; row++) {
                cols.add(c.getIndex(row,col));
            }
            assertEquals(cols.size(),9);
        }
        //TODO:check if 3x3 matrix is corect
    }
}