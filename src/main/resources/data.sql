-- Add categories
INSERT INTO categories (name, description) VALUES
                                               ('Electronics', 'Electronics products'),
                                               ('Clothing', 'Mens and Womens clothing'),
                                               ('Books', 'Books on various topics');

-- Add products
INSERT INTO products (name, description, price, category_id) VALUES
                                                                 ('Smartphone', 'Modern smartphone with a large screen', 500.00, 1),
                                                                 ('Laptop', 'Powerful laptop for work and entertainment', 1200.00, 1),
                                                                 ('T-Shirt', 'Cotton t-shirt for comfortable wear', 20.00, 2),
                                                                 ('Jeans', 'Classic jeans with stylish design', 50.00, 2),
                                                                 ('Java Programming', 'Textbook on Java programming', 30.00, 3),
                                                                 ('Introduction to Machine Learning', 'Book on machine learning', 25.00, 3);

-- Add users
INSERT INTO users (user_type, first_name, last_name, email, phone_number, password, address) VALUES
                                                                                                 ('Regular', 'John', 'Doe', 'john@example.com', '+1234567890', 'john123', 'New York, Main St, 123'),
                                                                                                 ('Administrator', 'Jane', 'Smith', 'admin@example.com', '+0987654321', 'admin123', 'Los Angeles, Elm St, 456');

-- Add orders
INSERT INTO orders (user_id, order_date, status, address, total_price, comment, payment_method, delivery_method) VALUES
                                                                                                                     (1, CURRENT_TIMESTAMP, 'Pending', 'New York, Main St, 123', 50.00, 'Please deliver as soon as possible', 'Card', 'Courier'),
                                                                                                                     (2, CURRENT_TIMESTAMP, 'Completed', 'Los Angeles, Elm St, 456', 100.00, 'Please call before delivery', 'Cash', 'Mail');

-- Add carts
INSERT INTO carts (user_id) VALUES
                                (1),
                                (2);

-- Add items to carts
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
                                                           (1, 1, 1),
                                                           (1, 3, 2),
                                                           (2, 2, 1),
                                                           (2, 4, 1);

-- Add items to orders_products table (associating products with orders)
INSERT INTO orders_products (order_id, product_id, quantity) VALUES
                                                                 (1, 1, 1), -- Order ID 1 contains Product ID 1 with quantity 1
                                                                 (1, 3, 2), -- Order ID 1 contains Product ID 3 with quantity 2
                                                                 (2, 2, 1), -- Order ID 2 contains Product ID 2 with quantity 1
                                                                 (2, 4, 1); -- Order ID 2 contains Product ID 4 with quantity 1