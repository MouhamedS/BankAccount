-- ACCOUNT --
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (1, 500, 100, 1);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (2, 1500, 200, 2);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (3, 2500, 100, 3);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (4, 20500, 1000, 4);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (5, 100, 0, 5);

-- TRANSACTIONS --
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, 100, sysdate -3, 1);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, -200, sysdate -3, 1);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, 150, sysdate -2, 1);

INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, -500, sysdate -3, 2);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, -200, sysdate -3, 2);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, 150, sysdate -2, 2);

INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, -500, sysdate -1, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, -200, sysdate -2, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, 150, sysdate -3, 3);

INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, -1500, sysdate -1, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, -2000, sysdate -2, 3);
INSERT INTO transaction(transaction_id ,amount, date ,client_id ) VALUES(1, 150, sysdate -3, 3);

-- CLIENT--
INSERT INTO client(client_id, firstname) values (1, 'Jean-Paul', 'Dupont');
INSERT INTO client(client_id, firstname) values (2, 'Madame', 'Irma');
INSERT INTO client(client_id, firstname) values (3, 'Khaled', 'Ayar');
INSERT INTO client(client_id, firstname) values (4, 'Sebastien', 'Mathieu');
INSERT INTO client(client_id, firstname) values (5, 'Toto', 'Titi');

