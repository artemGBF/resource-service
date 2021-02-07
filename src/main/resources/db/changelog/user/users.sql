create table users(
    id serial primary key,
    login text not null,
    password text not null,
    type text not null
);

create sequence my_users_seq increment by 1 start with 1;

insert into users(login, password, type) values ('admin', 'admin', 'ADMIN');
insert into users(login, password, type) values ('client', 'client', 'USER');