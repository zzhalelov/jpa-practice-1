import model.Product;
import model.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class UpdateProduct {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        int productId = getInt("Введите ID товара, который нужно обновить:");
        Product product = manager.find(Product.class, productId);

        if (product == null) {
            System.out.println("Товар не найден");
            return;
        }

        try {
            manager.getTransaction().begin();

            System.out.println(product.getName());
            String name = getString("Введите обновленное название товара \n Если обновлять название не нужно - нажмите Enter");
            if (!name.isBlank()) {
                product.setName(name);
            }

            try {
                int price = getInt("Введите обновленную цену товара \n Если обновлять цену не нужно - нажмите Enter");
                product.setPrice(price);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Число не целое");
                return;
            }
            updateValues(product, manager);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }

    static void updateValues(Product product, EntityManager manager) {
        List<Value> values = product.getValues();
        if (values.isEmpty()) {
            return;
        }

        System.out.println("Обновление значений характеристик");
        for (Value value : values) {
            String newValue = getString(value.getOption().getName() + " " + value.getOption().getName());
            if (newValue.isBlank()) {
                continue;
            }
            value.setName(newValue);
            manager.merge(value);
        }
    }

    static int getInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }

    static String getString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}