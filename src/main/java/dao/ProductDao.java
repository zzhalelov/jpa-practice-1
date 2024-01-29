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
        Query query = manager.createQuery("SELECT p FROM Product p WHERE p.name = :name");
        query.setParameter("name", name);

        List<Product> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
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

            Query query = manager.createQuery("SELECT p FROM Product p WHERE p.name = :name");
            query.setParameter("name", product.getName());

            List<Product> products = query.getResultList();

            if (!products.isEmpty()) {
                Product currentProduct = products.get(0);
                currentProduct.setPrice(product.getPrice() + 100);
                manager.merge(currentProduct);
                manager.getTransaction().commit();
                System.out.println("Сведения о товаре обновлены");
                return currentProduct;
            } else {
                System.out.println("Товар с наименованием " + product.getName() + " не найден.");
                manager.getTransaction().rollback();
            }
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println("Ошибка при обновлении сведений о товаре: " + e.getMessage());
        }
        return null;
    }

    //8. Подсчитать общую стоимость всех товаров
    public long totalSum() {
        TypedQuery<Long> query = manager.createQuery("SELECT sum(p.price) FROM Product p", Long.class);
        Long sum = query.getSingleResult();

        if (sum != null) {
            System.out.println("Общая стоимость всех товаров: " + sum);
            return sum;
        } else {
            System.out.println("Ошибка");
            return 0L;
        }
    }

    //9. Вывести общее количество товаров
    public long count() {
        TypedQuery<Long> query = manager.createQuery("SELECT count(*) FROM Product p", Long.class);
        Long count = query.getSingleResult();

        if (count != null) {
            System.out.println("Общее количество всех товаров: " + count);
            return count;
        } else {
            System.out.println("ERROR");
            return 0;
        }
    }

    //10. Найти самый дорогой товар
    public List<Product> findMax() {
        List<Product> products = manager.createQuery("SELECT p FROM Product p WHERE p.price = (SELECT max(p.price) FROM Product p)", Product.class).getResultList();
        System.out.println(products);
        return products;
    }

    //11. Показать топ N товаров с самой высокой стоимостью
    public List<Product> printTop(int num) {
        TypedQuery<Product> query = manager.createQuery("SELECT p FROM Product p ORDER BY price DESC ", Product.class);
        query.setMaxResults(num);

        List<Product> products = query.getResultList();

        System.out.println(products);
        return products;
    }

    //12. Предоставить скидку на товар по его id (в процентах)
    public Product discountById(Product product, int discount) {
        try {
            manager.getTransaction().begin();
            Product currentPrice = manager.find(Product.class, product.getId());
            double priceWithDiscount = currentPrice.getPrice() * (1 - ((double) discount / 100));
            currentPrice.setPrice((int) priceWithDiscount);
            manager.merge(currentPrice);
            manager.getTransaction().commit();
            System.out.println("Товару предоставлена скидка, % " + discount);
        } catch (Exception e) {
            manager.getTransaction().rollback();
            System.out.println(e.getMessage());
        }
        return product;
    }

    //13. Найти список товаров определенной категорий (по названий/id)
    public List<Product> findByCategoryId(int categoryId) {
        Query query = manager.createQuery("SELECT p FROM Product p, Category c WHERE c.id = :id");
        query.setParameter("id", categoryId);

        List<Product> products = query.getResultList();
        return products;
    }


}