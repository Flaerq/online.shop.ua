-- Drop tables if they exist
DROP TABLE IF EXISTS orders_products;
DROP TABLE IF EXISTS cart_items;
DROP TABLE IF EXISTS carts;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS categories;


CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            description TEXT
);

CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          description TEXT,
                          price DECIMAL(10, 2) NOT NULL,
                          category_id INT,
                          FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       user_type VARCHAR(255) NOT NULL,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL,
                       phone_number VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       address TEXT

);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id INT,
                        order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50),
                        address TEXT,
                        total_price DECIMAL(10, 2),
                        comment TEXT,
                        payment_method VARCHAR(50),
                        delivery_method VARCHAR(50),
                        FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE carts (
                       id SERIAL PRIMARY KEY,
                       user_id INT,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE cart_items (
                            id SERIAL PRIMARY KEY,
                            cart_id INT,
                            product_id INT,
                            quantity INT,
                            FOREIGN KEY (cart_id) REFERENCES carts(id),
                            FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE orders_products (
                                 id SERIAL PRIMARY KEY,
                                 order_id INT,
                                 product_id INT,
                                 quantity INT,
                                 FOREIGN KEY (order_id) REFERENCES orders(id),
                                 FOREIGN KEY (product_id) REFERENCES products(id)
);