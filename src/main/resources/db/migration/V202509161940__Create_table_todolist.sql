create table todolist.todolist
(
    id   bigint auto_increment
        primary key,
    text varchar(255) not null,
    done tinyint(1)   not null
);

