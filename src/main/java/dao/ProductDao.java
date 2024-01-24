package dao;

import model.Product;

import javax.persistence.*;
import java.util.List;

public class ProductDao {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    static EntityManager manager = factory.createEntityManager();

    //1. Создать товар
    public void create(Product product) {
        try {
            manager.getTransaction().begin();
            manager.persist(product);
            manager.getTransaction().commit();
            System.out.println("Запись о товаре была создана");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
    }

    //2. Вывести все товары
    public List<Product> findAll() {
        List<Product> products = manager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
        System.out.println(products);
        return products;
    }

    //3. Найти товар по Id
    public Product findByid(int productId) {
        return manager.find(Product.class, productId);
    }

    //4. Найти товар по названию
    public Product findByName(String name) {
        TypedQuery<Product> query = manager.createQuery("select p from Product p where name = ?1", Product.class);
        query.setParameter(1, "name");
        Product product = query.getSingleResult();
        return product;
    }

    //5. Удалить товар по id
    public int deleteById(int productId) {
        EntityTransaction transaction = manager.getTransaction();
        try {
            transaction.begin();
            Product product = manager.find(Product.class, productId);
            if (product != null) {
                manager.remove(product);
                System.out.println("Товар с указанным ID удален: " + productId);
            } else {
                System.out.println("Товар с указанным ID не найден: " + productId);
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            manager.getTransaction().rollback();
        }
        return productId;
    }

    //6. Обновить стоимость товара по id
    public Product updateById(Product product) {
        try {
            manager.getTransaction().begin();
            manager.find(Product.class, product.getId());
            product.setPrice(product.getPrice() + 10);
            manager.merge(product);
            manager.getTransaction().commit();
            System.out.println("Сведения о товаре обновлены");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return product;
    }

    //7. Обновить стоимость товара по названию
    public Product updateByName(Product product) {
        try {
            manager.getTransaction().begin();
            manager.find(Product.class, product.getName());
            product.setPrice(product.getPrice() + 30);
            manager.merge(product);
            manager.getTransaction().commit();
            System.out.println("Сведения о товаре обновлены");
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return product;
    }
}