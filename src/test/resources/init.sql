create table users (
  id serial primary key,
  name text not null,
  surname text not null
);

create table books (
  id serial primary key,
  title text not null,
  author text not null
);

create table courses (
  id serial primary key,
  name text not null
)

create table universities (
  id serial primary key,
  name text not null,
  location text
)