insert into users (active,image_id,date_of_created,email,name,password,id) values (true, null, null, 'danul78969@gmail.com', 'Данил', '$2a$08$0Uwm7HTN4f2Bmc0Zrp8rZe8fU97oeaXJd0zgy2rMUxzMkrWAWJnd6', 1);
insert into user_role (user_id,roles) values (1, 'ROLE_USER');
insert into user_role (user_id,roles) values (1, 'ROLE_ADMIN');
insert into user_role (user_id,roles) values (1, 'ROLE_MAINADMIN');