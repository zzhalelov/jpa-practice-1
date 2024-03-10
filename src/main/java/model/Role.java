package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public enum Role {
    USER("user", "Пользователь"),
    MODERATOR("moder", "Модератор"),
    ADMINISTRATOR("admin", "Администратор");

    private final String serviceName;
    private final String displayName;

}