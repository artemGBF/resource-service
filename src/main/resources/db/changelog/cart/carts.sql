create table carts(
    id serial primary key,
    uuid text not null,
    id_user int references users(id),
    is_active bool not null
);

create sequence my_carts_seq increment by 1 start with 1;