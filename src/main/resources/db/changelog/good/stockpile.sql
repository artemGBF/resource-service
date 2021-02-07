create table stockpile(
    id serial primary key,
    number text not null
);

create sequence my_stockpile_seq increment by 1 start with 1;

insert into stockpile(number) values ('south');
insert into stockpile(number) values ('west');
insert into stockpile(number) values ('north');