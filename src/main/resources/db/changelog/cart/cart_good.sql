create table cart_good(
    id_cart int references carts(id),
    id_good int references good(id),
    count int not null,

    primary key (id_cart, id_good)
);