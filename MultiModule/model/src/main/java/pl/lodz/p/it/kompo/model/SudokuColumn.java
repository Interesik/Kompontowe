package pl.lodz.p.it.kompo.model;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;



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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuColumn that = (SudokuColumn) o;

        return new EqualsBuilder()
                .append(column, that.column)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(column)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,SHORT_PREFIX_STYLE)
                .append("column", column)
                .toString();
    }
}
