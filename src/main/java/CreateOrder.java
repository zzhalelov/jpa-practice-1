import model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        try {
            System.out.println("Введите id пользователя:");
            Long userId = Long.parseLong(scanner.nextLine());
            User user = manager.find(User.class, userId);

            System.out.println("Введите адрес:");
            String deliveryAddress = scanner.nextLine();

            List<OrderItem> orderItemList = new ArrayList<>();
            Order order = new Order();
            order.setUser(user);
            order.setStatus(Status.CREATED);
            order.setDeliveryAddress(deliveryAddress);
            order.setDateOfOrder(LocalDateTime.now());

            OrderItem item = new OrderItem();
            System.out.println("Введите id продукта:");
            Long productId = Long.parseLong(scanner.nextLine());
            Product product = manager.find(Product.class, productId);

            System.out.println("Введите кол-во:");
            int quantity = Integer.parseInt(scanner.nextLine());

            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(quantity);
            orderItemList.add(item);

            try {
                manager.getTransaction().begin();
                manager.persist(order);
                for (OrderItem orderItem : orderItemList) {
                    manager.persist(orderItem);
                }

                manager.getTransaction().commit();
                System.out.println("Заказ оформлен");
            } catch (Exception e) {
                System.out.println("Ошибка");
                manager.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}