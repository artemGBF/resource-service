create table carts_goods(
    id serial primary key,
    id_cart int references carts(id),
    id_good int references good(id),
    count int not null
);

create sequence my_carts_goods_seq increment by 1 start with 1;