package pl.lodz.p.it.kompo.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PostgressqlSudokuBoardDao implements Dao<SudokuBoard> {

    private final String boardName;

    public PostgressqlSudokuBoardDao(String boardName) {
        this.boardName = boardName;
    }

    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("Persistence");

    @Override
    public SudokuBoard read() throws ReadFromFileException {
        SudokuBoard readSudokuBoard;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            readSudokuBoard = em.find(SudokuBoard.class, boardName);
        } catch (Exception e) {
            throw new ReadFromFileException("ExceptionRead",e);
        }
        return readSudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws SaveToFileException {
        EntityTransaction entityTransaction = null;
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            obj.setPrKey(boardName);
            em.persist(obj);
            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            throw new SaveToFileException("ExceptionWrite",e);
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
    }
}
