create sequence image_sequence start with 1 increment by 1;
create sequence product_sequence start with 1 increment by 1;
create sequence product_admin_info_sequence start with 1 increment by 1;
create sequence seller_data_sequence start with 1 increment by 1;
create sequence user_sequence start with 1 increment by 1;
create table images (
    is_preview_image boolean,
    id bigint not null,
    product_id bigint,
    size bigint,
    content_type varchar(255),
    name varchar(255),
    original_file_name varchar(255),
    bytes oid,
    primary key (id)
);

create table product_admin_info (
    moderate boolean,
    date_deactivate timestamp(6),
    id bigint not null,
    product_id bigint unique,
    deactivate_reason varchar(255),
    primary key (id)
);

create table product_like (
    product_id bigint not null,
    user_id bigint not null
);

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

create table seller_data (
    id bigint not null,
    user_id bigint unique,
    address varchar(255),
    contact varchar(255),
    phone varchar(255),
    primary key (id)
);

create table user_role (
    user_id bigint not null,
    roles varchar(255) check (roles in ('ROLE_USER','ROLE_ADMIN','ROLE_SELLER','ROLE_MAINADMIN'))
);

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

alter table if exists images add constraint FKghwsjbjo7mg3iufxruvq6iu3q foreign key (product_id) references products;
alter table if exists product_admin_info add constraint FKnl9p1hb78op6g7tuvp155jj8y foreign key (product_id) references products;
alter table if exists product_like add constraint FKmm34jxvkoxtx173uwsioc7wsa foreign key (product_id) references products;
alter table if exists product_like add constraint FK5gj2wpe2fsrpm2jfnlbq88dlk foreign key (user_id) references users;
alter table if exists products add constraint FKdb050tk37qryv15hd932626th foreign key (user_id) references users;
alter table if exists seller_data add constraint FKatefk1ak7hgalhphlkfi54re foreign key (user_id) references users;
alter table if exists user_role add constraint FKj345gk1bovqvfame88rcx7yyx foreign key (user_id) references users;
alter table if exists users add constraint FK17herqt2to4hyl5q5r5ogbxk9 foreign key (image_id) references images;