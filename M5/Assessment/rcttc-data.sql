use rcttcdb;

select distinct customer_first, customer_last, customer_email, customer_phone, customer_address
from temp_rcttc;

-- Inserting into Customer table

insert into Customer (first_name, last_name, email, phone, address)
	select distinct customer_first, customer_last, customer_email, customer_phone, customer_address
	from temp_rcttc;

select distinct `show` 
from temp_rcttc;

-- Inserting into Performance table

insert into Performance (`name`)
	select distinct `show` 
	from temp_rcttc;

select distinct theater, theater_address, theater_phone, theater_email
from temp_rcttc;

-- Inserting into Theater table

insert into Theater (`name`, address, phone, email)
	select distinct theater, theater_address, theater_phone, theater_email
	from temp_rcttc;

select distinct temp.ticket_price, temp.`date`, t.theater_id, p.performance_id
from temp_rcttc temp
inner join Theater t on temp.theater = t.`name`
inner join Performance p on temp.`show` = p.`name`;

-- Inserting into Shows table

insert into Shows (ticket_price, `date`, theater_id, performance_id)
	select distinct temp.ticket_price, temp.`date`, t.theater_id, p.performance_id
	from temp_rcttc temp
	inner join Theater t on temp.theater = t.`name`
	inner join Performance p on temp.`show` = p.`name`;

select distinct temp.seat, s.shows_id, c.customer_id
from temp_rcttc temp
inner join Shows s on temp.`date` = s.`date` and temp.ticket_price = s.ticket_price
inner join Customer c on temp.customer_first = c.first_name and temp.customer_last = c.last_name;

-- Inserting into Ticket table

insert into Ticket (seat, shows_id, customer_id)
	select distinct temp.seat, s.shows_id, c.customer_id
	from temp_rcttc temp
	inner join Shows s on temp.`date` = s.`date` and temp.ticket_price = s.ticket_price
	inner join Customer c on temp.customer_first = c.first_name and temp.customer_last = c.last_name;
    
-- Denormalizing the table; original table
    
select
	c.first_name,
    c.last_name,
    c.email,
    c.phone,
    c.address,
    ticket.seat,
    p.`name`,
    s.ticket_price,
    s.`date`,
    t.`name`,
    t.address,
    t.phone,
    t.email
from Customer c
inner join Ticket ticket on c.customer_id = ticket.customer_id
inner join Shows s on ticket.shows_id = s.shows_id
inner join Performance p on s.performance_id = p.performance_id
inner join Theater t on s.theater_id = t.theater_id;

-- Dropping the original table from the database

drop table temp_rcttc;

-- Enabling updates, deletes

set sql_safe_updates = 0;

-- Updated the price from $20 to $22.25

update Shows set
	ticket_price = 22.25
where `date` = '2021-03-01' and theater_id = 2 and performance_id = 4;

-- Updated the phone number to 1-801-EAT-CAKE

Update Customer set
	phone = "1-801-EAT-CAKE"
where first_name = 'Jammie' and last_name = 'Swindles';

-- Updating all seats so that everyone has their own unique seats for Little Fitz's 2021-03-01 performance of The Sky Lit Up

update Ticket set
	seat = 'A1'
where ticket_id = 92;

update Ticket set
	seat = 'A2'
where ticket_id = 93;

update Ticket set
	seat = 'A3'
where ticket_id = 94;

update Ticket set
	seat = 'A4'
where ticket_id = 101;

update Ticket set
	seat = 'B1'
where ticket_id = 95;

update Ticket set
	seat = 'B2'
where ticket_id = 96;

update Ticket set
	seat = 'B3'
where ticket_id = 97;

update Ticket set
	seat = 'B4'
where ticket_id = 98;

update Ticket set
	seat = 'C1'
where ticket_id = 99;

update Ticket set
	seat = 'C2'
where ticket_id = 100;

update Ticket set
	seat = 'C3'
where ticket_id = 102;

update Ticket set
	seat = 'C4'
where ticket_id = 103;

-- Deleting all customers who have single-tickets at the 10 Pin

delete from Ticket where customer_id = 7;

delete from Ticket where customer_id = 8;

delete from Ticket where customer_id = 10;

delete from Ticket where customer_id = 15;

delete from Ticket where customer_id = 18;

delete from Ticket where customer_id = 19;

delete from Ticket where customer_id = 22;

delete from Ticket where customer_id = 25;

delete from Ticket where customer_id = 26;

delete from Ticket where customer_id = 65;

delete from Customer where customer_id = 65;

-- Disables updating, deleting

set sql_safe_updates = 1;