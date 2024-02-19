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

        int productId = getInt("Введите ID товара, который надо удалить:");

        Product product = manager.find(Product.class, productId);
        if (product == null) {
            return;
        }

        try {
            manager.getTransaction().begin();

            //удалить все значения характерстик, которые были привязаны к удаляемому продукту
            deleteValuesByProductId(manager, product);

            //удалить товар
            manager.remove(product);

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Ошибка");
        }
    }

    static void deleteValuesByProductId(EntityManager manager, Product product) {
        List<Value> values = product.getValues();
        for (Value value : values) {
            manager.remove(value);
        }
    }

    static int getInt(String message) {
        System.out.println(message);
        return Integer.parseInt(scanner.nextLine());
    }
}