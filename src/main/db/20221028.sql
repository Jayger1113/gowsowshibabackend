create table user
(
    id	int unsigned auto_increment primary key,
    email	varchar(50) charset utf8    not null,
    password varchar(256) charset utf8    not null,
    update_millis	bigint	not null,
    create_millis	bigint  not null,
    constraint email	unique (email)
);