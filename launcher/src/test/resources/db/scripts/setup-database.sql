create database if not exists customer character set utf8mb4 collate utf8mb4_0900_ai_ci;
create user if not exists 'sys_customer'@'%' identified with mysql_native_password by 'sys_customer';
grant all privileges on customer.* to 'sys_customer'@'%';

create database if not exists product character set utf8mb4 collate utf8mb4_0900_ai_ci;
create user if not exists 'sys_product'@'%' identified with mysql_native_password by 'sys_product';
grant all privileges on product.* to 'sys_product'@'%';

flush privileges;
