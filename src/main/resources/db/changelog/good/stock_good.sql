create table stock_good
(
    id_stock int references stockpile (id),
    id_good  int references good (id),
    count    bigint not null,

    primary key (id_stock, id_good)
);

insert into stock_good(id_good, id_stock, count)
values (1, 1, 10);
insert into stock_good(id_good, id_stock, count)
values (1, 2, 10);
insert into stock_good(id_good, id_stock, count)
values (1, 3, 10);
insert into stock_good(id_good, id_stock, count)
values (2, 1, 10);
insert into stock_good(id_good, id_stock, count)
values (2, 2, 0);
insert into stock_good(id_good, id_stock, count)
values (2, 3, 0);
insert into stock_good(id_good, id_stock, count)
values (3, 1, 10);
insert into stock_good(id_good, id_stock, count)
values (3, 2, 10);
insert into stock_good(id_good, id_stock, count)
values (3, 3, 30);
insert into stock_good(id_good, id_stock, count)
values (4, 1, 10);
insert into stock_good(id_good, id_stock, count)
values (4, 2, 5);
insert into stock_good(id_good, id_stock, count)
values (4, 3, 0);
insert into stock_good(id_good, id_stock, count)
values (5, 1, 1);
insert into stock_good(id_good, id_stock, count)
values (5, 2, 1);
insert into stock_good(id_good, id_stock, count)
values (5, 3, 1);
insert into stock_good(id_good, id_stock, count)
values (6, 1, 3);
insert into stock_good(id_good, id_stock, count)
values (6, 2, 3);
insert into stock_good(id_good, id_stock, count)
values (6, 3, 3);