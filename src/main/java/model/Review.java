package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "publication_status")
    private boolean published;

    @Column(name = "review_rating")
    private int rating;

    @Column(name = "review_text")
    private String text;

    @Column(name = "review_data")
    private LocalDateTime publicationDate;
}