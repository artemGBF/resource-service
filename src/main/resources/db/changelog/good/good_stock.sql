create table good_stock
(
    id_good  int references good (id),
    id_stock int references stockpile (id),
    count    bigint not null,

    primary key (id_good, id_stock)
);

insert into good_stock(id_good, id_stock, count)
values (1, 1, 10);
insert into good_stock(id_good, id_stock, count)
values (1, 2, 10);
insert into good_stock(id_good, id_stock, count)
values (1, 3, 10);
insert into good_stock(id_good, id_stock, count)
values (2, 1, 10);
insert into good_stock(id_good, id_stock, count)
values (2, 2, 0);
insert into good_stock(id_good, id_stock, count)
values (2, 3, 0);
insert into good_stock(id_good, id_stock, count)
values (3, 1, 10);
insert into good_stock(id_good, id_stock, count)
values (3, 2, 10);
insert into good_stock(id_good, id_stock, count)
values (3, 3, 30);
insert into good_stock(id_good, id_stock, count)
values (4, 1, 10);
insert into good_stock(id_good, id_stock, count)
values (4, 2, 5);
insert into good_stock(id_good, id_stock, count)
values (4, 3, 0);
insert into good_stock(id_good, id_stock, count)
values (5, 1, 1);
insert into good_stock(id_good, id_stock, count)
values (5, 2, 1);
insert into good_stock(id_good, id_stock, count)
values (5, 3, 1);
insert into good_stock(id_good, id_stock, count)
values (6, 1, 3);
insert into good_stock(id_good, id_stock, count)
values (6, 2, 3);
insert into good_stock(id_good, id_stock, count)
values (6, 3, 3);