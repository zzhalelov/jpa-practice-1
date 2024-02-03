import model.Category;

import javax.persistence.*;
import java.util.Scanner;

public class CreateCategory {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        String name = getString("Введите название:");

        if (name == null || name.isEmpty()) {
            System.out.println("Название не может быть пустым значенем!");
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
}