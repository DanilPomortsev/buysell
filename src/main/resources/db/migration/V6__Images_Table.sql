create sequence image_sequence start with 1 increment by 1;

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

alter table if exists images add constraint FKghwsjbjo7mg3iufxruvq6iu3q foreign key (product_id) references products;
alter table if exists users add constraint FK17herqt2to4hyl5q5r5ogbxk9 foreign key (image_id) references images;