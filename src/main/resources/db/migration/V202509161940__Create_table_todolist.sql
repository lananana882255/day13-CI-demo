create table todolist.todolist
(
    id   bigint auto_increment
        primary key,
    text varchar(255) not null,
    done BOOLEAN   not null
);

