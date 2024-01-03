create database if not exists product character set utf8mb4 collate utf8mb4_0900_ai_ci;

create user if not exists 'sys_product'@'%' identified with mysql_native_password by 'product_6#233zJMNTjkQb#sgps#';

grant all privileges on product.* to 'sys_product'@'%';

flush privileges;
