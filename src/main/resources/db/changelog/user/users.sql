create table users(
    id serial primary key,
    login text not null,
    password text not null,
    role text not null
);

create sequence my_users_seq increment by 1 start with 1;

insert into users(login, password, role) values ('admin', 'admin', 'ADMIN');
insert into users(login, password, role) values ('client', 'client', 'USER');