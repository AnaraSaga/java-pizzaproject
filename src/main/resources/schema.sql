DROP TABLE IF EXISTS pizza;

CREATE TABLE pizza (
  id varchar (36) not null primary key,
  name varchar (255) not null ,
  price float,
  description text not null,
  image varchar (255) not null
);

DROP TABLE IF EXISTS cafe;

CREATE TABLE cafe (
    id varchar (36) not null primary key,
    name varchar (255) not null ,
    address varchar (255) not null ,
    phoneNumber varchar (255) not null
);