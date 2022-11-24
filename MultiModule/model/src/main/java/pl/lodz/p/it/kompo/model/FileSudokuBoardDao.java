package pl.lodz.p.it.kompo.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    private final String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() {
        try (
                FileInputStream fin = new FileInputStream(filename);
                ObjectInputStream oin = new ObjectInputStream(fin)
                ) {
            return (SudokuBoard) oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try (
                FileOutputStream fout = new FileOutputStream(filename);
                ObjectOutputStream oos = new ObjectOutputStream(fout)
                ) {
            oos.writeObject(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Cant implement because finaly cant be implementened read:
    // https://docs.oracle.com/javase/tutorial/essential/exceptions/finally.html
    @Override
    public void close() throws Exception {

    }
}
