package pl.lodz.p.sudoku;

import org.junit.jupiter.api.Test;


import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    /**
     * Test solveGame
     * Sprawdzenie czy SudokuBoard generuję poprawną rozwiązaną planszę sudoku.
     * @see SudokuBoard
     * Kolejno sprawdzanie czy uzyskane rozwiązanie jest poprawne względem reguł gry sudoku.
     */
    @Test
    void SudokuBorad(){
        SudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard c = new SudokuBoard(back);
        assertTrue(c.solveGame());
        for(int row = 0; row < 9; row++) {
            Set<Integer> rows = new HashSet<>();
            for(int col = 0; col < 9; col++) {
                rows.add(c.getIndex(row,col));
            }
            assertEquals(rows.size(),9);
        }
        for(int col = 0; col < 9; col++) {
            Set<Integer> cols = new HashSet<>();
            for(int row = 0; row < 9; row++) {
                cols.add(c.getIndex(row,col));
            }
            assertEquals(cols.size(),9);
        }
        for(int row = 0; row < 9;row+=3) {
            for (int col = 0;col < 9;col+=3) {
                Set<Integer> matrix = new HashSet<>();
                int startRow = row - row % 3;
                int startCol = col - col % 3;
                for (int j = 0; j < 3; j++) {
                    for (int g = 0; g < 3; g++) {
                        matrix.add(c.getIndex(j + startRow,g + startCol));
                    }
                }
                assertEquals(matrix.size(),9);
            }
        }
    }

    /**
     * Test solveGame
     * Sprawdzenie czy solveGame dla dwóch różnych SudokuBoard generuję to samo rozwiąznie.
     * Kolejno sprawdzanie czy uzyskane rozwiązanie jest poprawne względem reguł gry sudoku.
     */
    @Test
    void solveGame() {
        SudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard c = new SudokuBoard(back);
        SudokuBoard c2 = new SudokuBoard(back);
        assertTrue(c2.solveGame());
        assertTrue(c.solveGame());
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
            Set<Integer> rows = new HashSet<>();
            for(int col = 0; col < 9; col++) {
                rows.add(c.getIndex(row,col));
            }
            assertEquals(rows.size(),9);
        }
        for(int col = 0; col < 9; col++) {
            Set<Integer> cols = new HashSet<>();
            for(int row = 0; row < 9; row++) {
                cols.add(c.getIndex(row,col));
            }
            assertEquals(cols.size(),9);
        }
        for(int row = 0; row < 9;row+=3) {
            for (int col = 0;col < 9;col+=3) {
                Set<Integer> matrix = new HashSet<>();
                int startRow = row - row % 3;
                int startCol = col - col % 3;
                for (int j = 0; j < 3; j++) {
                    for (int g = 0; g < 3; g++) {
                        matrix.add(c.getIndex(j + startRow,g + startCol));
                    }
                }
                assertEquals(matrix.size(),9);
            }
        }
    }

    @Test
    void getterSetter() {
        SudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard test = new SudokuBoard(back);
        test.setIndex(0,0,1);
        assertEquals(test.getIndex(0,0),1);
    }

}