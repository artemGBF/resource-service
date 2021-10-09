create table users
(
    id    serial primary key,
    name  text not null,
    email text not null
);

create table category
(
    id        serial primary key,
    name      text not null,
    parent_id bigint references category (id)
);

create table good
(
    id          serial primary key,
    name        text                            not null,
    price       float                           not null,
    category_id bigint references category (id) not null
);

create table orders
(
    id        serial primary key,
    user_id   int  not null references users (id),
    delivery  text not null,
    payment   text not null,
    status    text not null,
    is_active boolean default true
);

create table stockpile
(
    id     serial primary key,
    number text not null
);

create table xref_order_2_goods
(
    order_id int references orders (id),
    good_id  int references good (id),
    count    int not null,

    primary key (order_id, good_id)
);

create table xref_stock_2_goods
(
    stock_id int references stockpile (id),
    good_id  int references good (id),
    count    bigint not null,

    primary key (stock_id, good_id)
);