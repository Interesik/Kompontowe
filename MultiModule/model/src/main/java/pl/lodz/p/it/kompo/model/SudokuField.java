package pl.lodz.p.it.kompo.model;

import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity(name = "field")
public class SudokuField implements Serializable,Cloneable,Comparable {
    private int value = 0;
    @Transient
    private final PropertyChangeSupport propertySupport;
    @Id
    private Long id;

    public SudokuField() {
        this.propertySupport = new PropertyChangeSupport(this);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        int oldValue = this.value;
        this.value = value;
        propertySupport.firePropertyChange("Value", oldValue, value);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder()
                .append(value, that.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(value)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,SHORT_PREFIX_STYLE)
                .append("value", value)
                .build();
    }

    @Override
    protected SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }

    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        SudokuField comparetorObj = (SudokuField) o;
        if (this.getValue() > comparetorObj.getValue()) {
            return 1;
        } else if (this.getValue() == comparetorObj.getValue()) {
            return 0;
        } else {
            return -1;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
}
