CREATE DATABASE ECommercePlatform;
USE ECommercePlatform;

-- Creates the Users table
CREATE TABLE Users (
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role ENUM('Customer', 'Admin') DEFAULT 'Customer'
);

-- Creates the Products table
CREATE TABLE Products (
	product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    stock_quantity INT NOT NULL DEFAULT 0 CHECK (stock_quantity >= 0)
);

-- Creates the Orders table
CREATE TABLE Orders (
	order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL CHECK (total_amount > 0),
    order_status ENUM('Pending', 'In Process', 'Shipped', 'Delivered', 'Cancelled') DEFAULT 'Pending',
    CONSTRAINT fk_order_user FOREIGN KEY (user_id)
    REFERENCES Users(user_id)
);

-- Creates the OrderDetails table 
CREATE TABLE OrderDetails (
	order_detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
	quantity INT NOT NULL CHECK (quantity > 0),
    unit_price DECIMAL(10, 2) NOT NULL CHECK (unit_price > 0),
    CONSTRAINT fk_orderdetail_order FOREIGN KEY (order_id)
    REFERENCES Orders(order_id),
    CONSTRAINT fk_orderdetail_product FOREIGN KEY (product_id)
    REFERENCES Products(product_id)
);

-- Creates the Payments table
CREATE TABLE Payments (
	payment_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method ENUM('Credit Card', 'Bank Transfer'),
    amount DECIMAL(10, 2) NOT NULL CHECK (amount > 0),
    CONSTRAINT fk_payment_order FOREIGN KEY (order_id)
    REFERENCES Orders(order_id)
);

-- Creates the Reviews table
CREATE TABLE Reviews (
	review_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    user_id INT NOT NULL,
    review_text TEXT,
    rating TINYINT NOT NULL CHECK (rating BETWEEN 0 AND 5),
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_review_product FOREIGN KEY (product_id)
    REFERENCES Products(product_id),
    CONSTRAINT fk_review_user FOREIGN KEY (user_id)
    REFERENCES Users(user_id)
);

-- Inserts sample user data
INSERT INTO Users (username, email, password, role) VALUES
('mike_barca', 'mikeB@gmail.com', 'messi123', 'Customer'),
('megaraDoug', 'megis@hotmail.com', 'twelve456', 'Admin'),
('admin_alex', 'alex@gmail.com', 'adminPass', 'Admin'),
('cookie2024', 'oreomilk@hotmail.com', 'qwertyuiop', 'Customer'),
('sarah-mulo', 'sarah@hotmail.com', 'muloUS34', 'Customer');

-- Inserts sample product data
INSERT INTO Products (product_name, category, price, stock_quantity) VALUES
('Wireless Headphones', 'Electronics', 59.99, 50),
('Spiderman T-Shirt', 'Clothing', 4.99, 100),
('Dining Room', 'Home', 115.50, 75),
('Programming Book: SQL Basics', 'Books', 30.00, 30),
('Smartphone Case', 'Accessories', 14.99, 200);

-- Inserts sample order data
INSERT INTO Orders (user_id, total_amount, order_status) VALUES
(1, 64.98, 'Delivered'),  
(2, 30.00, 'Shipped'),    
(3, 120.49, 'In Process'), 
(1, 115.50, 'Pending'),   
(4, 59.99, 'Delivered');  

-- Inserts sample order detail data
INSERT INTO OrderDetails (order_id, product_id, quantity, unit_price) VALUES
(1, 1, 1, 59.99),   
(1, 2, 1, 4.99),  
(2, 4, 1, 30.00), 
(3, 3, 1, 115.50),  
(3, 5, 1, 4.99),   
(4, 3, 1, 115.50),  
(5, 1, 1, 59.99);

-- Inserts sample payment data
INSERT INTO Payments (order_id, payment_method, amount) VALUES
(1, 'Credit Card', 64.98),
(2, 'Bank Transfer', 30.00),
(3, 'Credit Card', 120.49),
(5, 'Credit Card', 59.99);

-- Inserts sample review data
INSERT INTO Reviews (product_id, user_id, review_text, rating) VALUES
(1, 1, 'Great sound quality!', 5),
(2, 1, 'Very comfortable fabric', 4),
(4, 2, 'Excellent for beginners', 5),
(1, 5, 'Battery life could be better', 3),
(3, 3, 'Durable and very confortable', 5);

-- Query to retrieve all electronics products
SELECT product_name, price, stock_quantity FROM Products
WHERE category = 'Electronics';

-- Query to get details for a specific user (user_id = 2)
SELECT * FROM Users
WHERE user_id = 2;

-- Query to get order history for user 1
SELECT * FROM Orders
WHERE user_id = 1;

-- Query to get all items in order 3
SELECT * FROM OrderDetails
WHERE order_id = 3;

-- Query to calculate average rating for product 1
SELECT AVG(rating) FROM Reviews
WHERE product_id = 1;

-- Query to calculate total revenue for May 2025
SELECT SUM(amount) FROM Payments
WHERE DATE_FORMAT(payment_date, '%Y-%m') = '2025-05';

-- Inserts a new product (SmartTV 4K)
INSERT INTO Products (product_name, category, price, stock_quantity) VALUES
('SmartTV 4K', 'Electronics', 400.00, 20);

-- Creates a new order for user 5
INSERT INTO Orders (user_id, total_amount, order_status) VALUES
(5, 404.99, 'Shipped');

-- Adds items to the new order (order_id = 6)
INSERT INTO OrderDetails (order_id, product_id, quantity, unit_price) VALUES
(6, 6, 1, 400.00),
(6, 2, 1, 4.99);

-- Records payment for the new order
INSERT INTO Payments (order_id, payment_method, amount) VALUES
(6, 'Credit Card', 404.99);

-- Updates stock quantity for product 6 (SmartTV)
UPDATE Products SET stock_quantity = 19 
WHERE product_id = 6;

-- Deletes reviews by user 5
DELETE FROM Reviews WHERE user_id = 5;

-- Query to find top 3 products by order count
SELECT p.product_id, COUNT(p.product_id) FROM Products p
INNER JOIN OrderDetails od WHERE od.product_id = p.product_id
GROUP BY p.product_id LIMIT 3;

-- Query to find users with orders over $100
SELECT u.username, o.total_amount, o.order_date FROM Orders o
INNER JOIN Users u WHERE o.user_id = u.user_id 
AND o.total_amount >= 100.00;

-- Query to calculate average rating for each product
SELECT p.product_name, AVG(r.rating) FROM Reviews r
INNER JOIN Products p WHERE r.product_id = p.product_id
GROUP BY p.product_name;

