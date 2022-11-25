package pl.lodz.p.it.kompo.model;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;




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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuBox sudokuBox = (SudokuBox) o;
        return new EqualsBuilder()
                .append(box, sudokuBox.box)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(box)
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,SHORT_PREFIX_STYLE)
                .append("box", box)
                .build();
    }

    @Override
    protected SudokuBox clone() throws CloneNotSupportedException {
        SudokuBox clone = new SudokuBox(this.box);
        List<SudokuField> cloneList = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < clone.box.size(); i++) {
            cloneList.set(i, clone.box.get(i).clone());
        }
        clone.box = cloneList;
        return clone;
    }
}
