package pl.lodz.p.it.kompo.model;

public interface Dao<T> extends AutoCloseable {
    T read();

    void write(T obj);
}
