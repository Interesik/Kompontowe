package pl.lodz.p.it.kompo.model;

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

    @Test
    void testEquals() {
        SudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard test = new SudokuBoard(back);
        assertTrue(test.equals(test));
        SudokuBoard test2 = new SudokuBoard(back);
        assertFalse(test.equals(test2));
        assertFalse(test.equals(this));
        assertFalse(test.equals(null));
    }

    @Test
    void randomRemove() {
        SudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard test = new SudokuBoard(back);
        for(int col = 0; col < 9; col++) {
            for(int row = 0; row < 9; row++) {
                assertNotEquals(0,test.getIndex(row,col));
            }
        }
        test.removeRandom(2);
        int amount = 0;
        for(int col = 0; col < 9; col++) {
            for(int row = 0; row < 9; row++) {
                if(test.getIndex(row,col) == 0){
                    amount ++;
                }
            }
        }
        assertEquals(2,amount);
    }

    @Test
    void testHashCode() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        SudokuBoard test2 = new SudokuBoard(solver);
        assertEquals(s.hashCode(),s.hashCode());
        assertNotEquals(test2.hashCode(),s.hashCode());
    }

    @Test
    void testToString() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(solver);
        String str = s.getClass().getSimpleName()+"[sudokuFields=[";
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(i == 8 && j == 8){
                    str += "SudokuField[value="+s.getIndex(i,j)+"]";
                }
                else {
                    str += "SudokuField[value="+s.getIndex(i,j)+"], ";
                }

            }
        }
        str += "]]";
        assertEquals(str,s.toString());
    }

    @Test
    void Clonable() throws CloneNotSupportedException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(solver);
        SudokuBoard cloneSB = sb.clone();
        assertNotSame(sb,cloneSB);
        assertEquals(sb,cloneSB);
        sb.setIndex(0,0,0);
        assertNotEquals(sb,cloneSB);
    }
}