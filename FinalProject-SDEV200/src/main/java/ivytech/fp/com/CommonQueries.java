package ivytech.fp.com;
import entites.*;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public interface CommonQueries<T> {
    List<T> findAll();
    T findById(int id);
    void save(T t);
    void delete(int id);

}
