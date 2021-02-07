create table good(
    id serial primary key,
    name text not null,
    price float not null,
    category_id bigint references category(id) not null
);

create sequence my_good_seq increment by 1 start with 1;

insert into good(name, price, category_id) values('ростбиф', 390, 9);
insert into good(name, price, category_id) values( 'стейк риплойн', 590, 9);
insert into good(name, price, category_id) values( 'гуляш', 190, 10);
insert into good(name, price, category_id) values( 'Замороженный палтус', 439, 3);
insert into good(name, price, category_id) values( 'Замороженная скумбрия', 470, 3);
insert into good(name, price, category_id) values( 'Пельмени', 210, 5);