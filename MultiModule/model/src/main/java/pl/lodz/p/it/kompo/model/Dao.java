package pl.lodz.p.it.kompo.model;

public interface Dao<T> extends AutoCloseable {
    T read() throws ReadFromFileException;

    void write(T obj) throws SaveToFileException;
}
