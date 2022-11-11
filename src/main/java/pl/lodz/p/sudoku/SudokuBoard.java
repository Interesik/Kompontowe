package pl.lodz.p.sudoku;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;




public class SudokuBoard {
    private List<SudokuField> sudokuFields = Arrays.asList(new SudokuField[81]);
    private List<SudokuVerifier> lisners = new ArrayList<>();

    private SudokuSolver solver;

    public SudokuBoard(SudokuSolver resolver) {
        for (int i = 0; i < 81; i++) {
            sudokuFields.set(i, new SudokuField());
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

    public int getIndex(int row, int col) {
        return sudokuFields.get(row * 9 + col).getValue();
    }

    public void setIndex(int row, int col, int number) {
        sudokuFields.get(row * 9 + col).setValue(number);
        // on every value change checkBoard
        checkBoard();
    }

    public SudokuRow getRow(int row) {
        return new SudokuRow(sudokuFields.subList(row * 9, row * 9 + 9));
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
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int j = 0; j < 3; j++) {
            box.addAll(sudokuFields.subList(startCol + (9 * startRow) + (9 * j),
                    startCol + 3 + (9 * startRow) + (9 * j)));
        }
        return new SudokuBox(box);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(sudokuFields, that.sudokuFields)
                .append(solver, that.solver)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sudokuFields)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, SHORT_PREFIX_STYLE)
                .append("sudokuFields", sudokuFields)
                .toString();
    }
}
//    public static void main(String[] args) {
//        BacktrackingSudokuSolver back = new BacktrackingSudokuSolver();
//        SudokuBoard s = new SudokuBoard(back);
//        System.out.println(s.toString());
//    }