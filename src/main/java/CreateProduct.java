import model.Category;
import model.Option;
import model.Product;
import model.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateProduct {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        printAllCategories();
        int categotyId = getInt("Выберите ID категории:");
        Category category = manager.find(Category.class, categotyId);

        String name = getString("Введите название товара");
        int price = getInt("Введите цену товара");

        Product product = Product.builder()
                .name(name)
                .price(price)
                .category(category)
                .build();

        List<Option> options = category.getOptions();

        List<Value> values = new ArrayList<>();

        for (Option option : options) {
            String valueName = getString("Введите значение");
            Value value = Value.builder()
                    .name(valueName)
                    .product(product)
                    .option(option)
                    .build();
            values.add(value);
        }
        product.setValues(values);

        try {
            manager.getTransaction().begin();
            manager.persist(product);
            manager.getTransaction().commit();
            System.out.println("Товар создан");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Ошибка " + e.getMessage());
        }
    }

    static void printAllCategories() {
        System.out.println("ВЫБЕРИТЕ КАТЕГОРИЮ:");
        System.out.println("1. Ноутбуки");
        System.out.println("2. Смартфоны");
        System.out.println("3. Наушники");
    }

    static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    static int getInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }
}