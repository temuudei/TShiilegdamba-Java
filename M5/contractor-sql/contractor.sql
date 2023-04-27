create table requirement
(
	requirementId int not null auto_increment,
    `name` varchar(50) not null,
    primary key(requirementId)
);

create table contractor
(
	contractorId int not null auto_increment,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    primary key(personId)
);

create table company
(
	companyId int not null auto_increment,
    `name` varchar(50) not null,
    address varchar(50) not null,
    primary key(companyId)
);

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

create table jobRequirements
(
	jobPostingId int not null,
    requirementId int not null,
    primary key(jobPostingId, requirementId),
    foreign key(jobPostingId) references jobPosting(jobPostingId),
    foreign key(requirementId) references requirement(requirementId)
);

insert into contractor (firstName, lastName)
	select distinct substring_index(Contractor, ' ', 1), substring_index(Contractor, ' ', -1)
    from temp_contractors2;
    
insert into jobPosting (companyId, contractorId, title, rate)
	select distinct com.companyId, con.contractorId, t.`Job Title`, t.Rate
    from temp_contractors2 t
    inner join company com on t.Company = com.`name`
    inner join contractor con on t.Contractor = (concat(con.firstName, ' ', con.lastName))