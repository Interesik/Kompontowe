package pl.lodz.p.sudoku;

import java.util.HashSet;
import java.util.Set;

public class SudokuBox  implements SudokuVerifier {
    private SudokuField[] box;

    public SudokuBox(SudokuField[] box) {
        this.box = box;
    }

    @Override
    public boolean verify() {
        Set<Integer> check = new HashSet<>();
        for (int j = 0; j < 9; j++) {
            check.add(box[j].getValue());
        }
        return check.size() == 9;
    }
}
