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
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (4, 20000, 1000, 4);
INSERT INTO account(account_id, balance, overdraft_threshold,client_id) values (5, 100, 0, 5);

-- TRANSACTIONS --
INSERT INTO transaction(amount, date ,client_id, account_id , BALANCE_POST_TRANSACTION) VALUES( 100, CURRENT_TIMESTAMP -3, 1, 1, 600);
INSERT INTO transaction(amount, date ,client_id , account_id, BALANCE_POST_TRANSACTION) VALUES( -200, CURRENT_TIMESTAMP -3, 1, 1, 400);
INSERT INTO transaction(amount, date ,client_id , account_id, BALANCE_POST_TRANSACTION) VALUES( 150, CURRENT_TIMESTAMP, 1, 1, 550);

INSERT INTO transaction(amount, date ,client_id, account_id , BALANCE_POST_TRANSACTION) VALUES( -500, CURRENT_TIMESTAMP -3, 2, 2, 1000);
INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( -200, CURRENT_TIMESTAMP -3, 2, 2,800);
INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( 150, CURRENT_TIMESTAMP -2, 2, 2, 950);

INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( -500, CURRENT_TIMESTAMP -1, 3, 3, 2000);
INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( -200, CURRENT_TIMESTAMP -2, 3, 3, 1800);
INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( 150, CURRENT_TIMESTAMP -3, 3, 3, 1950);

INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( -15000, CURRENT_TIMESTAMP -1, 4, 4, 5000);
INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( -2000, CURRENT_TIMESTAMP -2, 4, 3, 3000);
INSERT INTO transaction(amount, date ,client_id, account_id, BALANCE_POST_TRANSACTION ) VALUES( 150, CURRENT_TIMESTAMP -3, 4, 3, 3150);
