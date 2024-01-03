create database if not exists customer character set utf8mb4 collate utf8mb4_0900_ai_ci;

create user if not exists 'sys_customer'@'%' identified with mysql_native_password by 'customer_6#233zJMNTjkQb#sgps#';

grant all privileges on customer.* to 'sys_customer'@'%';

flush privileges;
