package pl.lodz.p.sudoku;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuBox  implements SudokuVerifier {
    private List<SudokuField> box;

    public SudokuBox(List<SudokuField> box) {
        this.box = box;
    }

    @Override
    public boolean verify() {
        Set<Integer> check = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            if (box.get(j).getValue() == 0) {
                continue;
            }
            if (check.contains(box.get(j).getValue())) {
                return false;
            }
            check.add(box.get(j).getValue());
        }
        return true;
    }
}
