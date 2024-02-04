import model.Category;
import model.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Scanner;

public class CreateOption {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager(); //static

        int id = getInt("Введите id категории");
        Category category = manager.find(Category.class, id);

        if (category == null) {
            System.out.println("Категория с указанным id не найдена");
            return;
        }

        String name = getString("Введите имя характеристики");

        if (optionCheck(manager, name, category)) {
            System.out.println("Характеристика с таким именем существует");
            return;
        }

        Option option = Option.builder()
                .name(name)
                .category(category)
                .build();

        try {
            manager.getTransaction().begin();
            manager.persist(option);
            manager.getTransaction().commit();
            System.out.println("Характеристика создана");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Ошибка при создании характеристики");
        }
    }

    static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    static int getInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }

    static boolean optionCheck(EntityManager manager, String name, Category category) {
        TypedQuery<Long> query = manager.createQuery("SELECT count(o) FROM Option o WHERE o.name = :name AND o.category = :category", Long.class);
        query.setParameter("name", name);
        query.setParameter("category", category);
        return query.getSingleResult() > 0;
    }
}