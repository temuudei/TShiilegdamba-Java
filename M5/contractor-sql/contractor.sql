use contract;
​
create table requirement
(
	requirementId int not null auto_increment,
    `name` varchar(50) not null,
    primary key(requirementId)
);
​
create table contractor
(
	contractorId int not null auto_increment,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    primary key(contractorId)
);
​
create table company
(
	companyId int not null auto_increment,
    `name` varchar(50) not null,
    address varchar(50) not null,
    primary key(companyId)
);
​
create table jobPosting
(
	jobPostingId int not null auto_increment,
    companyId int not null,
    contractorId int not null,
    title varchar(50) not null,
    rate int not null,
    primary key(jobPostingId),
    foreign key(companyId) references company(companyId),
    foreign key(contractorId) references contractor(contractorId)
);
​
create table jobRequirement
(
	jobPostingId int not null,
    requirementId int not null,
    primary key(jobPostingId, requirementId),
    foreign key(jobPostingId) references jobPosting(jobPostingId),
    foreign key(requirementId) references requirement(requirementId)
);
​
insert into requirement (`name`)
    select distinct Skills
    from temp_contractors2;
select * from requirement;
​
insert into company (`name`, address)
	select distinct Company, Address
	from temp_contractors2;
select * from company;
​
insert into contractor (firstName, lastName)
	select distinct substring_index(Contractor, ' ', 1), substring_index(Contractor, ' ', -1)
    from temp_contractors2;
select * from contractor;
-- companyId int not null,
--    contractorId int not null,
  --  title varchar(50) not null,
   -- rate int not null,
insert into jobPosting (companyId, contractorId, title, rate)
	select distinct com.companyId, con.contractorId, t.`Job Title`, t.Rate
    from temp_contractors2 t
    inner join company com on t.Company = com.`name`
    inner join contractor con on t.Contractor = (concat(con.firstName, ' ', con.lastName));
select * from jobPosting;
​
insert into jobRequirement (jobPostingId, requirementId)
	select distinct j.jobPostingId, r.requirementId
    from temp_contractors2 c
    inner join requirement r on c.Skills = r.`name`
    inner join company com on c.Company = com.`name`
    inner join jobPosting j on j.companyId = com.companyId;
select * from jobRequirement;
​
drop table temp_contractors2;