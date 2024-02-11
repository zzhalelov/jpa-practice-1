import model.Category;
import model.Option;

import javax.persistence.*;
import java.util.Scanner;

public class CreateCategory {
    static Scanner scanner = new Scanner(System.in);
    static Option option = new Option();

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        String name = getString("Введите название категории:");
        Category cat = manager.find(Category.class, name);

        if (name.isBlank()) {
            System.out.println("Название не может быть пустым значенем!");

        } else if (categoryExists(manager, name, cat)) {
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
    }

    static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    static boolean categoryExists(EntityManager manager, String name, Category category) {
        TypedQuery<Long> query = manager.createQuery("SELECT count(c) FROM Category  c WHERE c.name = :name", Long.class);
        query.setParameter("name", category);
        return query.getSingleResult() > 0;
    }
}