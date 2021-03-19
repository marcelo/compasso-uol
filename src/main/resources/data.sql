DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  price DECIMAL(20, 2) NOT NULL
);

INSERT INTO products (name, description, price) VALUES
  ('test', 'test', 59.99);
