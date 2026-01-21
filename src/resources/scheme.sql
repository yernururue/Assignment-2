create table authors
(
    id          serial
        primary key,
    name        varchar(255) not null,
    nationality varchar(255) not null,
    birthyear   integer
);

alter table authors
    owner to postgres;

create table books
(
    id             serial
        primary key,
    title          varchar(255) not null,
    isbn           varchar(50),
    author_id      integer
        references authors,
    publish_year   integer,
    book_type      varchar(20)  not null,
    download_url   varchar(500),
    file_size      double precision,
    shelf_location varchar(100),
    weight         double precision,
    available      boolean default true
);

alter table books
    owner to postgres;
