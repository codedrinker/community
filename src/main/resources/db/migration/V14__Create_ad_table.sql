create table ad
(
    id int auto_increment primary key not null,
    title varchar(256) DEFAULT NULL,
    url varchar(512) DEFAULT NULL,
    image varchar(256) DEFAULT NULL,
    gmt_start bigint,
    gmt_end bigint,
    gmt_create bigint,
    gmt_modified bigint,
    status int,
    pos varchar(10) NOT NULL
);