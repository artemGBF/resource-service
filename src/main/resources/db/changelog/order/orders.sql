create table orders(
    id serial primary key,
    uuid text not null,
    id_user int not null references users(id),
    id_cart int not null references carts(id),
    delivery text not null,
    payType text not null,
    status text not null
);

create sequence my_orders_seq increment by 1 start with 1;