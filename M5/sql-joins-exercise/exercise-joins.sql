use gravel_family;

-- Solve each task by writing a query below the task description.
-- Each task describes the expected result.
-- Unfortunately, tasks must be verified manually. :(

-- Example: 
-- Select first_name and last_name from customer,
-- user_name from login where rows from both tables are required.
-- Expected: 659 Rows
select
	c.first_name,
    c.last_name,
    l.user_name
from customer c
inner join login l on c.customer_id = l.customer_id;

-- Select first_name and last_name from customer,
-- user_name from login where rows from both tables are required.
-- Sort by user_name descending.
-- Expected: 659 Rows

select 
	c.first_name,
	c.last_name,
    l.user_name
from customer c
inner join login l on c.customer_id = l.customer_id;

-- Select first_name and last_name from customer,
-- user_name from login where rows from both tables are required.
-- Only customers whose last name starts with 'W'.
-- Sort by user_name descending.
-- Expected: 24 Rows

select
	c.first_name,
    c.last_name,
    l.user_name
from customer c 
inner join login l on c.customer_id = l.customer_id 
where c.last_name like 'W%' 
order by l.user_name desc;


-- Join item and category. Select the item name and category name.
-- Expected: 19 Rows

select 
	i.`name`,
    c.`name`
from item i 
inner join category c on i.category_id = c.category_id;


-- Join item and category. Select the item name and category name.
-- Create an alias for each column: item_name and category_name
-- Sort by the category_name, then item_name.
-- Expected: 19 Rows

select
	i.`name` as item_name,
    c.`name` as category_name
from item i
inner join category c on i.category_id = c.category_id
order by i.`name` asc, c.`name` asc;

-- Select name and price_per_unit from item,
-- name from unit. Make rows from both tables required.
-- Add column aliases to avoid confusion from two `name` columns.
-- Expected: 19 Rows

select 
	i.`name` as item_name,
	i.price_per_unit,
    u.`name` as unit_name
from item i 
inner join unit u on i.unit_id = u.unit_id;

-- Select all columns from item, category, and unit.
-- Make all rows required.
-- Expected: 19 Rows

select 
	i.*,
    c.*,
    u.*
from item i
inner join category c on i.category_id = c.category_id
inner join unit u on i.unit_id = u.unit_id;

-- Select first_name, last_name from customer,
-- select description from project,
-- where the customer's last_name starts with 'B' or 'D'.
-- If a customer doesn't have a project, still include them.
-- (Hint: left outer join)
-- Expected: 228 Rows

select 
	c.first_name,
    c.last_name,
    p.`description`
from customer c 
left outer join project p on c.customer_id = p.customer_id
where c.last_name like 'B%' or c.last_name like 'D%';


-- Find all customers who do not have a project.
-- Expected: 195 Rows

select 
	c.first_name,
    c.last_name,
    p.`description`
from customer c 
left outer join project p on c.customer_id = p.customer_id
where p.project_id is null;

-- Find all customers who do not have a login.
-- Expected: 341 Rows

select
	c.first_name,
    c.last_name
from customer c 
left outer join login l on c.customer_id = l.customer_id
where l.user_name is null;

-- Find all employees who are not assigned to a project.
-- Expected: 0 Rows

select 
	e.first_name,
    e.last_name
from employee e 
left outer join project_employee pe on e.employee_id = pe.employee_id
left outer join project p on pe.project_id = p.project_id
where p.project_id is null;

-- Select employee_id, first_name, and last_name from employee,
-- project_id and description from project
-- where the employee last_name equals `Gravel`.
-- Expected: 958 Rows

select 
	e.employee_id,
    e.first_name,
    e.last_name,
    p.project_id,
    p.`description`
from employee e
left outer join project_employee pe on e.employee_id = pe.employee_id
left outer join project p on pe.project_id = p.project_id
where e.last_name like 'Gravel%';

-- Which employees worked on a project for the customer
-- with last_name equal to 'Rao'?
-- Expected: Itch Gravel, Alang Durt, Ynez Durt, Ddene Soyle,
-- Mychal Soyle, Hugo Durt

select
	c.last_name as customer_last_name,
    e.first_name as employee_first_name,
    e.last_name as employee_last_name
from customer c
left outer join project p on c.customer_id = p.customer_id
left outer join project_employee pe on p.project_id = pe.project_id
left outer join employee e on pe.employee_id = e.employee_id
where c.last_name = 'Rao';



-- Find employees and projects for projects in 2017.
-- Select project_id from project and names from employee.
-- Expected: 410 Rows

select
	concat(e.first_name,' ', e.last_name) as full_name,
    p.project_id as project_id
from employee e
inner join project_employee pe on e.employee_id = pe.employee_id
inner join project p on pe.project_id = p.project_id
where year(p.start_date) = 2017;

-- Create an "invoice" with line item totals (calculated field)
-- for items billed to projects for the customer with last_name 'Stelfox'.
-- Include the customer's names, project_id, item name, and calculated cost.
-- Expected:
-- Lanie Stelfox 481 Machine Labor     9720.000000
-- Lanie Stelfox 481 Standard Labor    3675.000000
-- Lanie Stelfox 481 Construction Sand 336.000000
-- Lanie Stelfox 481 Class 5 Gravel    624.000000
-- Lanie Stelfox 481 Wall Stone        3452.100000

-- Determine which customers employee Fleur Soyle worked for in
-- the 'M3H' postal_code. All customers in the postal_code should be included
-- regardless of if they have a project or Fleur worked on it.
-- Though something should indicate if Fleur was on a M3H project.
-- Expected: 48 Rows, 3 projects that Fleur worked on.

-- Find customers without logins using a `right outer` join.
-- Expected: 341 Rows

-- List category with its parent category, but make the parent category
-- optional to include categories without a parent.
-- Expected: 8 Rows

-- Write an "everything" query:
-- customer_id and names from customer
-- description from project
-- quantity from project_item
-- name from item
-- name from category
-- name from unit
-- for customers in the 'L3K' postal_code.
-- Expected: 39 Rows