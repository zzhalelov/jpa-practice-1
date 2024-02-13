import model.Category;

import javax.persistence.*;
import java.util.Scanner;

public class CreateCategory {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        String name = getString("Введите название категории:");

        if (name.isBlank()) {
            System.out.println("Название не может быть пустым значенем!");
        } else if (categoryExists(manager, name)) {
            System.out.println("Категория с таким именем существует");
        } else {
            Category category = Category.builder()
                    .name(name)
                    .build();
            try {
                manager.getTransaction().begin();
                manager.persist(category);
                manager.getTransaction().commit();
                System.out.println("Категория создана");
            } catch (Exception e) {
                manager.getTransaction().rollback();
                System.out.println("Ошибка при создании категории");
            }
        }
        factory.close();
    }

    static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    //Метод, проверяющий наличие категории
    static boolean categoryExists(EntityManager manager, String name) {
        TypedQuery<Long> query = manager.createQuery("SELECT count(c) FROM Category  c WHERE c.name = :categoryName", Long.class);
        query.setParameter("categoryName", name);
        return query.getSingleResult() > 0;
    }
}