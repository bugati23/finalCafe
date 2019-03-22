DROP DATABASE IF EXISTS cafe;
CREATE DATABASE IF NOT EXISTS cafe DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE cafe;
CREATE TABLE IF NOT EXISTS user_role(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
role VARCHAR(45) NOT NULL,
UNIQUE KEY(role))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
insert into user_role(role) values('admin');
insert into user_role(role) values('user');

CREATE TABLE IF NOT EXISTS user(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
login VARCHAR(40) NOT NULL UNIQUE,
password CHAR(60) NOT NULL,
first_name VARCHAR(50) NOT NULL,
last_name VARCHAR(50) NOT NULL,
email VARCHAR(45) NOT NULL UNIQUE,
account DECIMAL(10,2) NULL DEFAULT 30,
points_loyalty INT UNSIGNED NULL DEFAULT 0,
blocked TINYINT(1) NOT NULL DEFAULT 1,
registration_date DATETIME NOT NULL,
role_id INT NOT NULL,
CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES user_role(id) ON DELETE CASCADE)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS feedback(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
user_id INT NOT NULL,
review TEXT(500) NOT NULL,
CONSTRAINT fk_feedback_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS product_category(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
category varchar(45) NOT NULL,
unique key (category))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
insert into product_category(category) values ('first course');
insert into product_category(category) values ('main course');
insert into product_category(category) values ('drink');


CREATE TABLE IF NOT EXISTS product(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
title VARCHAR(50) NOT NULL,
description VARCHAR(200) NOT NULL,
picture BLOB NOT NULL,
price DECIMAL(10,2) NOT NULL,
availability TINYINT(1) NOT NULL DEFAULT 1,
category_id INT NOT NULL,
CONSTRAINT fk_probuct_category FOREIGN KEY (category_id) REFERENCES product_category(id) ON DELETE CASCADE)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS payment_type(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
pay_type varchar(25) NOT NULL,
unique key (pay_type))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
insert into payment_type(pay_type) values ('cash');
insert into payment_type(pay_type) values ('online account');

CREATE TABLE IF NOT EXISTS order_rating(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
rating ENUM('1','2','3','4','5','6'))
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
insert into order_rating(rating) values('1');
insert into order_rating(rating) values('2');
insert into order_rating(rating) values('3');
insert into order_rating(rating) values('4');
insert into order_rating(rating) values('5');
insert into order_rating(rating) values('0');

CREATE TABLE IF NOT EXISTS order_status(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
status varchar(40) NOT NULL)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;
insert into order_status(status) values('expects');
insert into order_status(status) values('closed');
insert into order_status(status) values('expired');

CREATE TABLE IF NOT EXISTS cafe_order(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
user_id INT NOT NULL,
payment_type_id INT NOT NULL,
rating_id INT,
status_id INT NOT NULL,
total_amount DECIMAL(10,2) NOT NULL,
pre_oder TINYINT(1) NOT NULL,
time_of_order DATETIME NOT NULL,
time_of_receipt DATETIME NOT NULL,
CONSTRAINT fk_order_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
CONSTRAINT fk_order_pay_type FOREIGN KEY (payment_type_id) REFERENCES payment_type(id) ON DELETE CASCADE,
CONSTRAINT fk_order_rating FOREIGN KEY (rating_id) REFERENCES order_rating(id) ON DELETE CASCADE,
CONSTRAINT fk_order_status FOREIGN KEY (status_id) REFERENCES order_status(id) ON DELETE CASCADE)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE TABLE IF NOT EXISTS order_product(
id INT PRIMARY KEY AUTO_INCREMENT UNIQUE,
order_id INT NOT NULL,
product_id INT NOT NULL,
amount INT NOT NULL,
CONSTRAINT fk_order_product_to_order FOREIGN KEY (order_id) REFERENCES cafe_order(id) ON DELETE CASCADE,
CONSTRAINT fk_order_product_to_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE)
ENGINE = InnoDB DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'nikita1234'

