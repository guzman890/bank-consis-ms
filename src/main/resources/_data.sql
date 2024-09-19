
INSERT INTO
    persons (id, name, gender, age, identification, address, phone)
VALUES
    (1, 'Jose Lema', 'MALE', 30, '1234567890', 'Otavalo sn y principal', '98254785'),
    (2, 'Marianela Montalvo', 'FEMALE', 25, '0987654321', 'Amazonas y NNUU', '97548965'),
    (3, 'Juan Osorio', 'MALE', 40, '1122334455', '13 junio y Equinoccial', '98874587');

INSERT INTO
    customers (id, password, status)
VALUES
    (1, '1234', 'ACTIVE'),
    (2, '5678', 'ACTIVE'),
    (3, '1245', 'ACTIVE');


INSERT INTO
    accounts (id, account_number, account_type, initial_balance, status, customer_id)
VALUES
    (1, '478758', 'SAVINGS', 2000.00, 'ACTIVE', 1),
    (2, '225487', 'CHECKING', 100.00, 'ACTIVE', 2),
    (3, '495878', 'SAVINGS', 0.00, 'ACTIVE', 3),
    (4, '496825', 'SAVINGS', 540.00, 'ACTIVE', 2);


INSERT INTO
    transactions (id, transaction_date, transaction_type, initial_balance, amount, balance, account_id, customer_id)
VALUES
    (1, '2023-01-01', 'DEBIT', 2000, 575.00, 1425.00, 1, 1),
    (2, '2023-01-01', 'CREDIT', 100, 600.00, 700.00, 2, 2),
    (3, '2023-01-01', 'CREDIT', 0, 150.00, 150.00, 3, 3),
    (4, '2023-01-01', 'DEBIT', 540, 540.00, 0.00, 4, 2);