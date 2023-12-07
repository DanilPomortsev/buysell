create sequence product_sequence start with 1 increment by 1;

create table products (
    active boolean,
    date_of_creating date,
    date_of_last_changing date,
    price integer,
    id bigint not null,
    preview_image_id bigint,
    user_id bigint,
    city varchar(255),
    description text,
    title varchar(255),
    primary key (id)
);

alter table if exists products add constraint FKdb050tk37qryv15hd932626th foreign key (user_id) references users;