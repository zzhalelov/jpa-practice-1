CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR
);
CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    price       INT,
    category_id INT REFERENCES categories (id)
);
CREATE TABLE options
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    category_id INT REFERENCES categories (id)
);
CREATE TABLE values
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR,
    product_id INT REFERENCES products (id),
    option_id  INT REFERENCES options (id)
);

SELECT *
FROM categories,
     options
WHERE options.category_id = categories.id
  AND options.category_id = 1;
