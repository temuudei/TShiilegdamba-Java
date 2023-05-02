drop database if exists contactdb_test;
create database contactdb_test;
use contactdb_test;

create table Contact
(
	contact_id int primary key auto_increment,
	first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(100) not null,
    phone varchar(15) not null
);
delimiter //
create procedure set_known_good_state()
begin
    truncate table Contact;

    insert into Contact
        (first_name, last_name, email, phone)
    values
        ('Temuudei','Shiilegdamba','temuudeish@gmail.com','(123)4567'),
        ('Garret','Paradis','gparandis@gmail.com','(123)4567');
end //
delimiter ;