import model.Product;
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
        System.out.println("*** Для выхода введите цифру 0");
        System.out.println("-----------------------------------------------");
    }
}