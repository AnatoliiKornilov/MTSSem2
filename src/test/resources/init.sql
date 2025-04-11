create table users (
  id bigint primary key,
  name varchar(255) not null,
  surname varchar(255) not null
);

create table books (
  id bigint primary key,
  title varchar(255) not null,
  author varchar(255) not null
);

create table courses (
  id bigint primary key,
  name varchar(255) not null
);

create table universities (
  id bigint primary key,
  name varchar(255) not null,
  location varchar(255)
);
