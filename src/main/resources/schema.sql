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