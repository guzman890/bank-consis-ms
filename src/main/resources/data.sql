
INSERT INTO
    persons (id, name, gender, age, identification, address, phone)
VALUES
    (11, 'Jose Lema', 'MALE', 30, '1234567890', 'Otavalo sn y principal', '98254785'),
    (12, 'Marianela Montalvo', 'FEMALE', 25, '0987654321', 'Amazonas y NNUU', '97548965'),
    (13, 'Juan Osorio', 'MALE', 40, '1122334455', '13 junio y Equinoccial', '98874587');

INSERT INTO
    customers (id, password, status)
VALUES
    (11, '1234', 'ACTIVE'),
    (12, '5678', 'ACTIVE'),
    (13, '1245', 'ACTIVE');


INSERT INTO
    accounts (id, account_number, account_type, initial_balance, status, customer_id)
VALUES
    (11, '478758', 'SAVINGS', 2000.00, 'ACTIVE', 11),
    (12, '225487', 'CHECKING', 100.00, 'ACTIVE', 12),
    (13, '495878', 'SAVINGS', 0.00, 'ACTIVE', 13),
    (14, '496825', 'SAVINGS', 540.00, 'ACTIVE', 12);


INSERT INTO
    transactions (id, transaction_date, transaction_type, initial_balance, amount, balance, account_id, customer_id)
VALUES
    (11, '2023-01-01', 'DEBIT', 2000, 575.00, 1425.00, 11, 11),
    (12, '2023-01-01', 'CREDIT', 100, 600.00, 700.00, 12, 12),
    (13, '2023-01-01', 'CREDIT', 0, 150.00, 150.00, 13, 13),
    (14, '2023-01-01', 'DEBIT', 540, 540.00, 0.00, 14, 12);