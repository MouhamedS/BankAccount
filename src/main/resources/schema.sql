
create table account (
       account_id bigint PRIMARY KEY AUTO_INCREMENT,
        balance number(19,2),
        overdraft_threshold number(19,2),
        client_id bigint
    );

    create table client (
       client_id bigint PRIMARY KEY AUTO_INCREMENT,
        firstname varchar(255),
        lastname varchar(255)
    );

    create table transaction (
       transaction_id bigint PRIMARY KEY AUTO_INCREMENT,
        amount numeric(19,2),
        date timestamp,
        client_id bigint
    );

    alter table account
       add constraint foreign_key_client_account
       foreign key (client_id)
       references client;

    alter table transaction
       add constraint foreign_key_client_transaction
       foreign key (client_id)
       references client;