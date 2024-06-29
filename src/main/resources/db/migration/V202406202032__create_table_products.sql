create table products (
    product_id serial not null,
    name varchar(100) not null,
    stock int not null,
    constraint pk_products primary key (product_id)
);
