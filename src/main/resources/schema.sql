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

SELECT options.name, values.name
FROM options,
     categories,
     values
WHERE category_id = categories.id
  AND option_id = options.id
  AND categories.id = 1;

--select v.* from values v where v.option = ? v.product = ?
