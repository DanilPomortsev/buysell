create table product_like (
    product_id bigint not null,
    user_id bigint not null
);

alter table if exists product_like add constraint FKmm34jxvkoxtx173uwsioc7wsa foreign key (product_id) references products;
alter table if exists product_like add constraint FK5gj2wpe2fsrpm2jfnlbq88dlk foreign key (user_id) references users;