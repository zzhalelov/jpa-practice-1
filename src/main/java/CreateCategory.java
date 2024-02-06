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

        if (name.isBlank()) {
            System.out.println("Название не может быть пустым значенем!");
        } else {
            Category category = Category.builder()
                    .name(name)
                    .build();
            try {
                manager.getTransaction().begin();
                manager.persist(category);

                CreateOption createOption =
                        String optionName = getString("Введите название характеристики");
                Option opt = Option.builder()
                        .name(optionName)
                        .category(category)
                        .build();
                manager.persist(opt);
            }
            manager.getTransaction().commit();
            System.out.println("Категория создана");
        } catch(Exception e){
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