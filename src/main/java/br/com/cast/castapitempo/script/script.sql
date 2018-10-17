create table catapitempo.tempo(
id serial primary key,
tempmax varchar(5) not null,
tempmin varchar(5) not null,
descricao varchar(100) not null,
umidade varchar(5) not null,
cidade varchar(50) not null,
dttxt date not null
);