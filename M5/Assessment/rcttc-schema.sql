drop database if exists rcttcdb;

create database rcttcdb;

use rcttcdb;

-- Creating Performance table

create table Performance
(
	performance_id int primary key auto_increment,
    `name` varchar(100) not null
);

-- Creating Customer table

create table Customer
(
	customer_id int primary key auto_increment,
	first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null,
    phone varchar(15) null,
    address varchar(125) null
);

-- Creating Theater table

create table Theater
(
	theater_id int primary key auto_increment,
    `name` varchar(25) not null,
    address varchar(125) not null,
    phone varchar(15) not null,
    email varchar(100) not null
);

-- Creating Shows table

create table Shows
(
	shows_id int primary key auto_increment,
    ticket_price decimal(4,2) not null,
    `date` date not null,
    theater_id int not null,
    performance_id int not null,
    foreign key (theater_id) references Theater(theater_id),
    foreign key (performance_id) references Performance(performance_id)
);

-- Creating Ticket table

create table Ticket
(
	ticket_id int primary key auto_increment,
    seat varchar(3) not null,
    shows_id int not null,
    customer_id int not null,
    foreign key (shows_id) references Shows(shows_id),
    foreign key (customer_id) references Customer(customer_id)
);