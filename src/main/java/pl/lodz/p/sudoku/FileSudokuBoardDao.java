package pl.lodz.p.sudoku;

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
            SudokuBoard sb = (SudokuBoard) oin.readObject();
            return sb;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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

    @Override
    public void close() throws Exception {

    }
}
