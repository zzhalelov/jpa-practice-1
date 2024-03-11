package model;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String lastname;

    @Enumerated(value = EnumType.ORDINAL)
    private Role role;

    @Column(name = "registered_at")
    private LocalDateTime registeredDate;
}