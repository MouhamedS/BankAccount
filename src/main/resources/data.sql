-- CLIENT--
INSERT INTO client(client_id, firstname, lastname) values (1, 'Jean-Paul', 'Dupont');
INSERT INTO client(client_id, firstname, lastname) values (2, 'Madame', 'Irma');
INSERT INTO client(client_id, firstname, lastname) values (3, 'Khaled', 'Ayar');
INSERT INTO client(client_id, firstname, lastname) values (4, 'Sebastien', 'Mathieu');
INSERT INTO client(client_id, firstname, lastname) values (5, 'Toto', 'Titi');

-- ACCOUNT --
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (1, 500, 100, 1);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (2, 1500, 200, 2);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (3, 2500, 100, 3);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (4, 20500, 1000, 4);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (5, 100, 0, 5);

-- TRANSACTIONS --
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, 100, CURRENT_TIMESTAMP -3, 1);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(2, -200, CURRENT_TIMESTAMP -3, 1);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(3, 150, CURRENT_TIMESTAMP, 1);

INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(4, -500, CURRENT_TIMESTAMP -3, 2);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(5, -200, CURRENT_TIMESTAMP -3, 2);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(6, 150, CURRENT_TIMESTAMP -2, 2);

INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(7, -500, CURRENT_TIMESTAMP -1, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(8, -200, CURRENT_TIMESTAMP -2, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(9, 150, CURRENT_TIMESTAMP -3, 3);

INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(10, -1500, CURRENT_TIMESTAMP -1, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(11, -2000, CURRENT_TIMESTAMP -2, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(12, 150, CURRENT_TIMESTAMP -3, 3);
