package service;

import dao.ProductDao;
import lombok.Getter;
import model.Product;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Getter
public class ProductService {
    private final ProductDao productDao;
    private final Scanner scanner;

    public ProductService(Scanner scanner) {
        this.productDao = new ProductDao();
        this.scanner = scanner;
    }

    //1. Создать товар
    public void create() {
        System.out.println("Введите наименование продукта:");
        String name = scanner.nextLine();

        System.out.println("Введите цену товара:");
        int price = Integer.parseInt(scanner.nextLine());

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        productDao.create(product);
    }

    //2. Вывести все товары
    public void printAll() {
        productDao.findAll();
    }

    //3. Найти товар по id
    public void findById() {
        System.out.println("Введите id товара:");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productDao.findByid(id);
        if (product == null) {
            System.out.println("Товар с данным Id не найден");
            return;
        }
        System.out.println(product);
    }

    //4. Найти товар по названию
    public void findByName() {
        System.out.println("Введите имя товара:");
        String name = scanner.nextLine();
        Product product = productDao.findByName(name);
        if (product == null) {
            System.out.println("Товар с данным именем не найден");
            return;
        }
        System.out.println(product);
    }

    //5. Удалить товар по id
    public void deleteProductById() {
        System.out.println("Введите ID товара:");
        int id = Integer.parseInt(scanner.nextLine());
        int product = productDao.deleteById(id);
    }

    //6. Обновить стоимость товара по id
    public void updateProductById() {
        System.out.println("Введите ID товара:");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productDao.updateById(productDao.findByid(id));
    }

    //7. Обновить стоимость товара по названий
    public void updatePriceByName() {
        System.out.println("Введите наименование товара:");
        String name = scanner.nextLine();
        productDao.updateByName(productDao.findByName(name));
    }

    //8. Подсчитать общую стоимость всех товаров
    public void totalSum() {
        Long sum = productDao.totalSum();
    }

    //9. Вывести общее количество товаров
    public void countAll() {
        Long count = productDao.count();
    }

    //10. Найти самый дорогой товар
    public void findMax() {
        productDao.findMax();
    }

    //11. Показать топ N товаров с самой высокой стоимостью
    public void printTop() {
        System.out.println("Введите число для вывода Топ N товаров:");
        int num = Integer.parseInt(scanner.nextLine());
        productDao.printTop(num);
    }

    //12. Предоставить скидку на товар по его id (в процентах)
    public void discountById() {
        System.out.println("Введите Id товара");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Сколько процентов скидки на товар вы хотите предоставить:");
        int discount = Integer.parseInt(scanner.nextLine());
        productDao.discountById(productDao.findByid(id), discount);
    }

    //13. Найти список товаров определенной категорий (по id)
    public void findByCategoryId() {
        System.out.println("Введите ID товара:");
        int categoryId = Integer.parseInt(scanner.nextLine());
        List<Product> products = productDao.findByCategoryId(categoryId);
        System.out.println(products);
    }

    //14. Найти список товаров определенной категорий (по названию)
    public void findByCategoryName() {
        System.out.println("Введите название товара:");
        String name = scanner.nextLine();
        List<Product> products = productDao.findByCategoryName(name);
        System.out.println(products);
    }

    //15. Найти количество товаров в каждой категорий
    public void countByCategory() {
        Map<String, Long> countByCategory = productDao.countByCategory();
        for (Map.Entry<String, Long> entry : countByCategory.entrySet()) {
            System.out.println("По категории " + entry.getKey() + " количество товаров " + entry.getValue());
        }
    }
}