use rcttcdb;

select 
	p.`name`
from Performance p 
inner join Shows s on p.performance_id = s.performance_id 
where `date` between '2021-10-01' and '2021-12-31';

select distinct
	*
from Customer;

select 
	*
from Customer
where email not like '%.com';

select 
	*
from Shows
order by ticket_price limit 3;

select
	concat(c.first_name, ' ', c.last_name) as full_name,
    p.`name`
from Customer c 
inner join Ticket t on c.customer_id = t.customer_id
inner join Shows s on t.shows_id = s.shows_id
inner join Performance p on s.performance_id = p.performance_id
group by `name`, full_name;

select 
	concat(c.first_name, ' ', c.last_name) as full_name,
    p.`name` as `show`,
    t.`name` as theater_name,
    ticket.seat
from Customer c
inner join Ticket ticket on c.customer_id = ticket.customer_id
inner join Shows s on ticket.shows_id = s.shows_id
inner join Performance p on s.performance_id = p.performance_id
inner join Theater t on s.theater_id = t.theater_id;

select
	*
from Customer where address = '' or address is null;

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

select 
	concat(c.first_name, ' ', c.last_name) as full_name,
    count(t.seat) as total_tickets
from Customer c 
inner join Ticket t on c.customer_id = t.customer_id
group by full_name;

select 
	p.`name` as `show`,
    s.ticket_price as price,
    count(t.seat) as seats,
    s.ticket_price * count(t.seat) as total_revenue
from Shows s 
inner join Performance p on s.performance_id = p.performance_id
inner join Ticket t on s.shows_id = t.shows_id
group by `show`, price;


select 
	`name` as theater_name, 
    sum(total_revenue) as total_revenue 
from (select
		theater.`name` as `name`,
		s.ticket_price as price,
		count(t.seat) as seats,
		s.ticket_price * count(t.seat) as total_revenue
from Shows s 
inner join Ticket t on s.shows_id = t.shows_id
inner join Theater theater on s.theater_id = theater.theater_id
group by `name`, price) total_revenue
group by theater_name;
    
select
	full_name as full_name,
    sum(total_price) as total_price
from (select
		concat(c.first_name, ' ', c.last_name) as full_name,
		s.ticket_price as price,
		count(t.seat) * s.ticket_price as total_price
		from Ticket t 
		inner join Customer c on t.customer_id = c.customer_id
		inner join Shows s on t.shows_id = s.shows_id
		group by full_name, price) total_price
group by full_name
order by total_price desc;
	
