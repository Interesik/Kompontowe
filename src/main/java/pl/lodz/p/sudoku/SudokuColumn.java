package pl.lodz.p.sudoku;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuColumn implements SudokuVerifier {
    private List<SudokuField> column;

    public SudokuColumn(List<SudokuField> column) {
        this.column = column;
    }

    @Override
    public boolean verify() {
        Set<Integer> check = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            if (column.get(j).getValue() == 0) {
                continue;
            }
            if (check.contains(column.get(j).getValue())) {
                return false;
            }
            check.add(column.get(j).getValue());
        }
        return true;
    }
}
