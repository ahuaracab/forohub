CREATE TABLE topics (
    id bigint not null auto_increment,
    title varchar(255) not null,
    message varchar(255) not null,
    creation_date datetime not null,
    status tinyint not null,
    primary key (id)
);
