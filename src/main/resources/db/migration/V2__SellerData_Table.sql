create sequence seller_data_sequence start with 1 increment by 1;

create table seller_data (
    id bigint not null,
    user_id bigint unique,
    address varchar(255),
    contact varchar(255),
    phone varchar(255),
    primary key (id)
);

alter table if exists seller_data add constraint FKatefk1ak7hgalhphlkfi54re foreign key (user_id) references users;