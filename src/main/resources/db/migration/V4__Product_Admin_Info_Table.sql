create sequence product_admin_info_sequence start with 1 increment by 1;

create table product_admin_info (
    moderate boolean,
    date_deactivate timestamp(6),
    id bigint not null,
    product_id bigint unique,
    deactivate_reason varchar(255),
    primary key (id)
);

alter table if exists product_admin_info add constraint FKnl9p1hb78op6g7tuvp155jj8y foreign key (product_id) references products;