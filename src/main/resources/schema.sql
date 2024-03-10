--создание таблицы Категории
CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR
);

--создание таблицы Продукты
CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    price       INT,
    category_id INT REFERENCES categories (id)
);

--создание таблицы Характеристики
CREATE TABLE options
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    category_id INT REFERENCES categories (id)
);

--создание таблицы Значения
CREATE TABLE values
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR,
    product_id INT REFERENCES products (id),
    option_id  INT REFERENCES options (id)
);

--Создание таблицы пользователей
CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,
    login         VARCHAR NOT NULL UNIQUE,
    password      VARCHAR NOT NULL,
    name          VARCHAR,
    lastname      VARCHAR,
    role          int,
    registered_at timestamp
);

--Создание таблицы заказов
CREATE TABLE orders
(
    id               BIGSERIAL PRIMARY KEY,
    user_id          BIGINT REFERENCES users (id) NOT NULL,
    status           INT,
    delivery_address VARCHAR                      NOT NULL,
    date_of_order    TIMESTAMP
);

--создание таблицы отзывов
CREATE TABLE reviews
(
    id                 BIGSERIAL PRIMARY KEY,
    user_id            BIGINT REFERENCES users (id)    NOT NULL,
    product_id         BIGINT REFERENCES products (id) NOT NULL,
    publication_status INT                             NOT NULL,
    review_text        TEXT,
    review_data        TIMESTAMP
);

--
CREATE TABLE orders_products
(
    id         BIGSERIAL PRIMARY KEY,
    order_id   BIGINT REFERENCES orders (id),
    product_id BIGINT REFERENCES products (id)
)