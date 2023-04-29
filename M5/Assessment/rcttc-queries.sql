use rcttcdb;

-- Find all performances in the last quarter of 2021 (Oct. 1, 2021 - Dec. 31 2021).

select 
	p.`name`
from Performance p 
inner join Shows s on p.performance_id = s.performance_id 
where `date` between '2021-10-01' and '2021-12-31';

-- List customers without duplication.

select distinct
	*
from Customer;

-- Find all customers without a .com email address.

select 
	*
from Customer
where email not like '%.com';

-- Find the three cheapest shows.

select 
	*
from Shows
order by ticket_price limit 3;

-- List customers and the show they're attending with no duplication.

select
	concat(c.first_name, ' ', c.last_name) as full_name,
    p.`name`
from Customer c 
inner join Ticket t on c.customer_id = t.customer_id
inner join Shows s on t.shows_id = s.shows_id
inner join Performance p on s.performance_id = p.performance_id
group by `name`, full_name;

-- List customer, show, theater, and seat number in one query.

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

-- Find customers without an address.

select
	*
from Customer where address = '' or address is null;

-- Recreate the spreadsheet data with a single query.

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

-- Count total tickets purchased per customer.

select 
	concat(c.first_name, ' ', c.last_name) as full_name,
    count(t.seat) as total_tickets
from Customer c 
inner join Ticket t on c.customer_id = t.customer_id
group by full_name;

-- Calculate the total revenue per show based on tickets sold.

select 
	p.`name` as `show`,
    s.ticket_price as price,
    count(t.seat) as seats,
    s.ticket_price * count(t.seat) as total_revenue
from Shows s 
inner join Performance p on s.performance_id = p.performance_id
inner join Ticket t on s.shows_id = t.shows_id
group by `show`, price;

-- Calculate the total revenue per theater based on tickets sold.

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

-- Who is the biggest supporter of RCTTC? Who spent the most in 2021?: Jammie Swindles
    
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