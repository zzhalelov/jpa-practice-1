import model.Role;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class UserCreate {
    public static void main(String[] args) {
        EntityManager manager = Persistence.createEntityManagerFactory("default").createEntityManager();

        User user = new User();
        user.setLogin("userLogin");
        user.setPassword("userPassword");
        user.setName(user.getLogin());
        user.setRole(Role.ADMINISTRATOR);
        user.setRegisteredDate(LocalDateTime.now());

        try {
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}