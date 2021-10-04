CREATE DATABASE IF NOT EXISTS test_db 
CHARACTER SET utf8 
COLLATE utf8_general_ci;

USE test_db;

CREATE TABLE test_table (
	id INT AUTO_INCREMENT PRIMARY KEY,
    surname VARCHAR(50),
    date_ad DATE
);

CREATE TABLE test_table2 (
	id INT AUTO_INCREMENT PRIMARY KEY,
    table2 INT,
    FOREIGN KEY (table2)
        REFERENCES test_table (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);

CREATE TABLE test_table3 (
	id INT AUTO_INCREMENT PRIMARY KEY,
    table3 INT,
    FOREIGN KEY (table3)
        REFERENCES test_table (id)
        ON UPDATE RESTRICT ON DELETE CASCADE
);