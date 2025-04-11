CREATE DATABASE finanzas_personales;
USE finanzas_personales;

CREATE TABLE usuario (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    username VARCHAR(50) UNIQUE,
    password VARCHAR(100)
);

CREATE TABLE cuenta (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    account_type VARCHAR(50),
    balance DECIMAL(10, 2),
    liabilities DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES usuario(user_id)
);

CREATE TABLE fuente_de_ingreso (
    source_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    source_name VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES usuario(user_id)
);

CREATE TABLE ingreso (
    income_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    account_id INT,
    income_date DATE,
    income_source VARCHAR(100),
    amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES usuario(user_id),
    FOREIGN KEY (account_id) REFERENCES cuenta(account_id)
);

CREATE TABLE categoria_de_gasto (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
    category_name VARCHAR(100),
    FOREIGN KEY (user_id) REFERENCES usuario(user_id)
);

CREATE TABLE gasto (
	expense_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    account_id INT,
    expense_date DATE,
    expense_category VARCHAR(100),
    remark VARCHAR(100),
    amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES usuario(user_id),
    FOREIGN KEY (account_id) REFERENCES cuenta(account_id)
);

DELIMITER //
CREATE TRIGGER beforeAccountdelete
BEFORE DELETE ON cuenta
FOR EACH ROW
BEGIN
    IF OLD.account_id IS NOT NULL THEN
        DELETE FROM gasto WHERE account_id = OLD.account_id;
        DELETE FROM ingreso WHERE account_id = OLD.account_id;
        DELETE FROM transaccion WHERE account_id = OLD.account_id;
    END IF;
END;
//

DELIMITER ;

CREATE TABLE transaccion (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    type VARCHAR(10),
    amount DECIMAL(10, 2),
    statement VARCHAR(255),
    time TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES cuenta(account_id)
);

DELIMITER //
CREATE TRIGGER afterIncomeInsert
AFTER INSERT ON ingreso
FOR EACH ROW
BEGIN
    INSERT INTO transaccion(account_id, type, amount, statement, time) VALUES 
        (NEW.account_id, 'Ingreso', NEW.amount, CONCAT('Ingreso registrado: ', NEW.income_source, ' - Monto: ', NEW.amount), CURRENT_TIMESTAMP);
END;
//

CREATE TRIGGER afterExpenseInsert
AFTER INSERT ON gasto
FOR EACH ROW
BEGIN
    INSERT INTO transaccion(account_id, type, amount, statement, time) VALUES 
        (NEW.account_id, 'Gasto', NEW.amount, CONCAT('Gasto registrado: ', NEW.expense_category, ' - Monto: ', NEW.amount), CURRENT_TIMESTAMP);
END;
//

DELIMITER ;

CREATE TABLE presupuesto (
    budget_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    expense_category VARCHAR(100),
    amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES usuario(user_id)
);

CREATE TABLE monto_objetivo (
    target_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    amount DECIMAL(10, 2),
    FOREIGN KEY (user_id) REFERENCES usuario(user_id)
);