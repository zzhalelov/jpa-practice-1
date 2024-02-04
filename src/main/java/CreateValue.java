//import model.Option;
//import model.Product;
//import model.Value;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import java.util.Scanner;
//
//public class CreateValue {
//    static Scanner scanner = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
//        EntityManager manager = factory.createEntityManager();
//
//        int productId = getInt("Введите id товара");
//        Product product = manager.find(Product.class, productId);
//
//        int optionId = getInt("Введите id характеристики");
//        Option option = manager.find(Option.class, optionId);
//
//        String name = getString("Введите значение характеристики");
//
//        Value value = Value.builder()
//                .name(name)
//                .product(product)
//                .option(option)
//                .build();
//
//        try {
//            manager.getTransaction().begin();
//            manager.persist(value);
//            manager.getTransaction().commit();
//            System.out.println("Значение для характеристики задано");
//        } catch (Exception e) {
//            manager.getTransaction().rollback();
//            System.out.println("Ошибка");
//        }
//    }
//
//    static String getString(String message) {
//        System.out.println(message);
//        return scanner.nextLine();
//    }
//
//    static int getInt(String message) {
//        System.out.println(message);
//        return Integer.parseInt(scanner.nextLine());
//    }
//}