insert into category(name, parent_id)
values ('Заморозка', null);
insert into category(name, parent_id)
values ('Кулинария', null);
insert into category(name, parent_id)
values ('Замороженная рыба', 1);
insert into category(name, parent_id)
values ('Замороженное мясо', 1);
insert into category(name, parent_id)
values ('Полуфабрикаты', 1);
insert into category(name, parent_id)
values ('Мясная кухня', 2);
insert into category(name, parent_id)
values ('Рыбная кухня', 2);
insert into category(name, parent_id)
values ('Птичья кухня', 2);
insert into category(name, parent_id)
values ('Жареное мясо', 6);
insert into category(name, parent_id)
values ('Тушеное мясо', 6);

insert into good(name, price, category_id)
values ('ростбиф', 390, 9);
insert into good(name, price, category_id)
values ('стейк риплойн', 590, 9);
insert into good(name, price, category_id)
values ('гуляш', 190, 10);
insert into good(name, price, category_id)
values ('Замороженный палтус', 439, 3);
insert into good(name, price, category_id)
values ('Замороженная скумбрия', 470, 3);
insert into good(name, price, category_id)
values ('Пельмени', 210, 5);

insert into stockpile(number)
values ('south');
insert into stockpile(number)
values ('west');
insert into stockpile(number)
values ('north');

insert into xref_stock_2_goods(good_id, stock_id, count)
values (1, 1, 10);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (1, 2, 10);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (1, 3, 10);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (2, 1, 10);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (2, 2, 0);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (2, 3, 0);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (3, 1, 10);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (3, 2, 10);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (3, 3, 30);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (4, 1, 10);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (4, 2, 5);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (4, 3, 0);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (5, 1, 1);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (5, 2, 1);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (5, 3, 1);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (6, 1, 3);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (6, 2, 3);
insert into xref_stock_2_goods(good_id, stock_id, count)
values (6, 3, 3);