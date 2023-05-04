drop database if exists pets_test;
create database pets_test;
use pets_test;

create table pet (
    pet_id int primary key auto_increment,
    `name` varchar(50) not null,
    `type` varchar(50) null
);
delimiter //
create procedure set_known_good_state()
begin
	truncate table pet;
    insert into pet 
		(`name`, `type`)
	values
		('Secret', 'Snake'),
        ('Arslan', 'Cat');
end //
delimiter ;
