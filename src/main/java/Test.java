import service.ProductService;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductService service = new ProductService(scanner);

        while (true) {
            menu();
            int command = Integer.parseInt(scanner.nextLine());
            switch (command) {
                case 0 -> {
                    return;
                }
                case 1 -> service.create();
                case 2 -> service.printAll();
                case 3 -> service.findById();
                case 4 -> service.findByName();
                case 5 -> service.deleteProductById();
                case 6 -> service.updateProductById();
                case 7 -> service.updatePriceByName();
                case 8 -> service.totalSum();
                case 9 -> service.countAll();
                case 10 -> service.findMax();
                case 11 -> service.printTop();
                case 12 -> service.discountById();
                case 13 -> service.findByCategoryId();
                case 14 -> service.findByCategoryName();
                case 15 -> service.countByCategory();
            }
        }
    }

    private static void menu() {
        System.out.println("-----------------------------------------------");
        System.out.println("1. Создать товар");
        System.out.println("2. Вывести все товары");
        System.out.println("3. Найти товар по id");
        System.out.println("4. Найти товар по названию");
        System.out.println("5. Удалить товар по id");
        System.out.println("6. Обновить стоимость товара по id");
        System.out.println("7. Обновить стоимость товара по названий");
        System.out.println("8. Подсчитать общую стоимость всех товаров");
        System.out.println("9. Вывести общее количество товаров");
        System.out.println("10. Найти самый дорогой товар");
        System.out.println("11. Показать топ N товаров с самой высокой стоимостью");
        System.out.println("12. Предоставить скидку на товар по его id (в процентах)");
        System.out.println("13. Найти список товаров определенной категорий (по id)");
        System.out.println("14. Найти список товаров определенной категорий (по названию)");
        System.out.println("15. Найти количество товаров в каждой категорий");
        System.out.println("*** Для выхода введите цифру 0");
        System.out.println("-----------------------------------------------");
    }
}