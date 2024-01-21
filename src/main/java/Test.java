import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        //1. Достать товар по id
        System.out.println(manager.find(Product.class, 1));

        //2. Достать все товары
        List<Product> products = manager.createQuery("select p from Product p", Product.class).getResultList();
        System.out.println(products);

        //3. Добавить товар
        try {
            manager.getTransaction().begin();
            Product product = new Product();
            product.setName("Fanta");
            product.setPrice(400);
            manager.persist(product);
            manager.getTransaction().commit();
            System.out.println("Товар был добавлен");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        //4. Обновить информацию о товаре
        try {
            manager.getTransaction().begin();
            Product product = manager.find(Product.class, 3);
            product.setPrice(product.getPrice() + 50);
            manager.merge(product);
            manager.getTransaction().commit();
            System.out.println("Информация о товаре обновлена");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }

        //5. Удалить товар
        try {
            manager.getTransaction().begin();
            Product product = manager.find(Product.class, 3);
            manager.remove(product);
            manager.getTransaction().commit();
            System.out.println("Товар удален");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
    }
}