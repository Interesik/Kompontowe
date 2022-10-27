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
            check.add(column[j].getValue());
        }
        return check.size() == 9;
    }
}
