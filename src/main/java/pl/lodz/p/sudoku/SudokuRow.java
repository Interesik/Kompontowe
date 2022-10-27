package pl.lodz.p.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuRow implements SudokuVerifier {
    private SudokuField[] row;

    public SudokuRow(SudokuField[] row) {
        this.row = row;
    }

    @Override
    public boolean verify() {
        Set<Integer> check = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            check.add(row[j].getValue());
        }
        return check.size() == 9;
    }
}
