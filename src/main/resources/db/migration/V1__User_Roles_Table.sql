create sequence user_sequence start with 1 increment by 1;

create table users (
    active boolean,
    date_of_created timestamp(6),
    id bigint not null,
    image_id bigint unique,
    password varchar(1000),
    email varchar(255) unique,
    name varchar(255),
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255) check (roles in ('ROLE_USER','ROLE_ADMIN','ROLE_SELLER','ROLE_MAINADMIN'))
);

alter table if exists user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users;