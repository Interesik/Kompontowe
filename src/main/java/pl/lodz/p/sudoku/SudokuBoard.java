package pl.lodz.p.sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class SudokuBoard {
    private List<SudokuField> sudokuFields = new ArrayList<>();
    private List<SudokuVerifier> lisners = new ArrayList<>();

    private SudokuSolver solver;

    public SudokuBoard(SudokuSolver resolver) {
        for (int i = 0; i < 81; i++) {
                sudokuFields.add(new SudokuField());
        }
        for (int i = 0; i < 9; i++) {
            lisners.add(getRow(i));
            lisners.add(getColumn(i));
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lisners.add(getBox(i, j));
            }
        }
        this.solver = resolver;
        // random generate starting board
        for (int r = 0; r < 9; r++) {
            sudokuFields.get(r).setValue(r);
        }
        Collections.shuffle(sudokuFields);
        this.solveGame();
    }

    public boolean solveGame() {
        return solver.solve(this);
    }

    private boolean checkBoard() {
        for (SudokuVerifier lis : lisners) {
            if (!lis.verify()) {
                return false;
            }
        }
        return true;
    }

    public int getIndex(int row,int col) {
        return sudokuFields.get(row * 9 + col).getValue();
    }

    public void setIndex(int row,int col, int number) {
        sudokuFields.get(row * 9 + col).setValue(number);
    }

    public SudokuRow getRow(int row) {
        return new SudokuRow(sudokuFields.subList((row * 9),(row * 9) + 9));
    }

    public SudokuColumn getColumn(int col) {
        List<SudokuField> column = new ArrayList<>(9);
        for (int row = 0; row < 9; row++) {
            column.add(sudokuFields.get(col + (row * 9)));
        }
        return new SudokuColumn(column);
    }

    public SudokuBox getBox(int row, int col) {
        List<SudokuField> box = new ArrayList<>(9);
        int index = 0;
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int j = 0; j < 3; j++) {
            box.addAll(sudokuFields.subList(startCol + (9 * startRow) + (9 * j),
                    startCol + 3 + (9 * startRow) + (9 * j)));
        }
        return new SudokuBox(box);
    }
}
/*
    public static void main(String[] args) {
        SudokuSolver back = new BacktrackingSudokuSolver();
        SudokuBoard s = new SudokuBoard(back);
        for (int i = 0; i < s.board.length; i++) {
            for (int j = 0; j < s.board[0].length; j++) {
                System.out.print(s.getIndex(i, j) + " ");
            }
            System.out.println();
        }
    }
 */