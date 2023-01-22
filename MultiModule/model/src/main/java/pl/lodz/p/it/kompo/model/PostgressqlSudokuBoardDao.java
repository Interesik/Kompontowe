package pl.lodz.p.it.kompo.model;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PostgressqlSudokuBoardDao implements Dao<SudokuBoard>{

    private final String boardName;

    public PostgressqlSudokuBoardDao(String boardName) {
        this.boardName = boardName;
    }
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("Persistence");
    private EntityManager em = entityManagerFactory.createEntityManager();

    @Override
    public SudokuBoard read() throws ReadFromFileException {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws SaveToFileException {
        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(obj);
            entityTransaction.commit();
        } catch (Exception e) {
            if(entityTransaction != null){
                entityTransaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            em.close();
        }
    }

    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
        em.close();
    }
}
