create table category(
    id serial primary key,
    name text not null,
    parent_id bigint references category(id)
);

create sequence my_category_seq increment by 1 start with 11;

insert into category(name, parent_id) values( 'Заморозка', null);
insert into category(name, parent_id) values( 'Кулинария', null);
insert into category(name, parent_id) values ( 'Замороженная рыба', 1);
insert into category(name, parent_id) values ( 'Замороженное мясо', 1);
insert into category(name, parent_id) values ('Полуфабрикаты', 1);
insert into category(name, parent_id) values ('Мясная кухня', 2);
insert into category(name, parent_id) values ('Рыбная кухня', 2);
insert into category(name, parent_id) values ('Птичья кухня', 2);
insert into category(name, parent_id) values ('Жареное мясо', 6);
insert into category(name, parent_id) values ('Тушеное мясо', 6);