package pl.lodz.p.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuColumn implements SudokuVerifier {
    private SudokuField[] column;

    public SudokuColumn(SudokuField[] column) {
        this.column = column;
    }

    @Override
    public boolean verify() {
        Set<Integer> check = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            if (column[j].getValue() == 0) {
                continue;
            }
            if (check.contains(column[j].getValue())) {
                return false;
            }
            check.add(column[j].getValue());
        }
        return true;
    }
}
