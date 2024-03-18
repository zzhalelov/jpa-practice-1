import model.Product;
import model.Review;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Scanner;

public class CreateReview {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        try {
            System.out.println("Введите id пользователя:");
            Long userId = Long.parseLong(scanner.nextLine());
            User user = manager.find(User.class, userId);

            System.out.println("Введите id товара:");
            Long productId = Long.parseLong(scanner.nextLine());
            Product product = manager.find(Product.class, productId);

            System.out.println("Введите оценку от 1 до 5:");
            int rating = Integer.parseInt(scanner.nextLine());

            System.out.println("Введите текст отзыва:");
            String text = scanner.nextLine();

            try {
                manager.getTransaction().begin();
                Review review = new Review();
                review.setUser(user);
                review.setProduct(product);
                review.setRating(rating);
                review.setText(text);
                review.setPublicationDate(LocalDateTime.now());

                manager.persist(review);
                manager.getTransaction().commit();
                System.out.println("Отзыв добавлен");
            } catch (Exception e) {
                System.out.println("Ошибка! Отзыв не добавлен " + e.getMessage());
                manager.getTransaction().rollback();
            }
        } catch (Exception e) {
            System.out.println("Ошибка " + e.getMessage());
        }
    }
}