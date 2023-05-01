drop database if exists contactdb;

create database contactdb;

use contactdb;

create table Contact
(
	contact_id int primary key auto_increment,
	first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null,
    phone varchar(15) not null
);

insert into Contact (first_name, last_name, email, phone) 
	values
		("Garrett", "Paradis", "myemail@gmail.com", "(123)4566678"),
        ("Temuudei", "Shiilegdamba", "tshiilegdamba@gmail.com", "(123)456777");
