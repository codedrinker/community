create table nav
(
    id int auto_increment primary key not null,
    title varchar(100),
    url varchar(256),
    priority int default 0,
    gmt_create bigint,
    gmt_modified bigint,
    status int
);