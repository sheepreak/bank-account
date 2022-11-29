drop table if exists account;
create table account
(
    id      int auto_increment primary key,
    balance double not null
);

drop table if exists operation;
create table operation
(
    id               int auto_increment primary key,
    account_id       int,
    operation        varchar(32),
    datetime         timestamp,
    previous_balance double not null,
    new_balance      double not null,
    amount           double not null
);