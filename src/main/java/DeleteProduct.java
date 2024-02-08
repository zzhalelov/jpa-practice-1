import model.Product;
import model.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class DeleteProduct {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        //запросить id продукта
        int productId = getInt("Введите ID товара, который надо удалить:");

        //удалить все значения характерстик, которые были привязаны к удаляемому продукту
        deleteValuesByProductId(manager, productId);

        //удаление продукта
        deleteProduct(manager, productId);

        factory.close();

    }

    static void deleteValuesByProductId(EntityManager manager, int productId) {
        Product product = manager.find(Product.class, productId);

        if (product != null) {
            List<Value> values = product.getValues();

            try {
                manager.getTransaction().begin();
                for (Value value : values) {
                    manager.remove(value);
                }
                manager.getTransaction().commit();
                System.out.println("Все значения характерстик товара удалены");
            } catch (Exception e) {
                manager.getTransaction().rollback();
                System.out.println("Ошибка");
            }
        } else {
            System.out.println("Товар с указанным ID не найден");
        }
    }

    static void deleteProduct(EntityManager manager, int productId) {
        Product product = manager.find(Product.class, productId);

        if (product != null) {
            try {
                manager.getTransaction().begin();
                manager.remove(product);
                manager.getTransaction().commit();
                System.out.println("Товар удален");
            } catch (Exception e) {
                manager.getTransaction().rollback();
                System.out.println("Ошибка");
            }
        } else {
            System.out.println("Товар с указанным ID не найден");
        }
    }

    static int getInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }
}