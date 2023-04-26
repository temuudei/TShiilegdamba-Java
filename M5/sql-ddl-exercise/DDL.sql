use sys;
drop database if exists recording_label;
create database recording_label;
use recording_label;

create table Person(
	personID int auto_increment,
    firstName varchar(50),
    lastName varchar(50),
    stageName varchar(50) not null,
    primary key (personID)
);

create table Role(
	roleID int not null,
    `name` varchar(50) not null,
    primary key(roleID)
);


create table Album(
	albumId int auto_increment,
    `name` varchar(50) not null,
    releaseDate dateTime not null,
    `version` varchar(10) not null default '1.0', 
    primary key (albumId)
);

create table Track(
	trackId int auto_increment,
	`name` varchar(50) not null,
    releaseDate dateTime not null,
    albumId int not null,
    primary key (trackId),
    foreign key (albumId) references Album(albumId)
);


create table Track_to_album(
	trackID int not null,
    albumID int not null,
    primary key(trackID, albumID),
    foreign key(trackID) references Track(trackID),
    foreign key(albumID) references Album(albumID)
);

create table Credit(
	trackID int not null,
    roleID int not null,
    personID int not null,
    primary key(trackID, roleID, personID),
    foreign key (trackID) references Track(trackID),
    foreign key (roleID) references Role(roleID),
    foreign key (personID) references Person(personID)
);

insert into Role (roleID, `name`) values
	(1, 'Artist'),
    (2, 'Feature'),
    (3, 'Writer'),
    (4, 'Producer'),
    (6, 'Arranger'),
    (7, 'Performer');

insert into Person(firstName, lastName, stageName) values
	('Beyonce', 'Knowles', 'Beyone'),
    (null, null, 'Jay-z'),
    ('Joshua', 'Coleman', 'JC'),
    ('Sia', 'Furler', 'SF'),
    ('Brian', 'Soko', 'BS'),
    ('Jerome', 'Harmon', 'JH'),
    ('Shawn', 'Carter', 'SC'),
    ('Andre', 'Eric Proctor', 'Andre Eric Proctor'),
    ('Rasool', 'Diaz', 'Rasool Diaz'),
    ('Noel', 'Fisher', 'Noel Fisher'),
    ('Pharrell', 'Williams', 'Pharrel Williams'),
    ('Frank', 'Ocean', 'Frank Ocea'),
    ('Jack', 'White', 'Jack White'),
    ('Diana', 'Gordon', 'Diana Gordon'),
    (null, null, 'Rhoden'),
    ('Jonathan', 'Coffer', 'Jonathan Coffer'),
    ('Carla', 'Williams', 'Carla Williams'),
    ('Arrow', 'Benjamin', 'Arrow Benjamin'),
    ('Kendrick', 'Duckworth', 'Kendrick Duckworth'),
    ('Christopher', 'Breaux', 'Christopher Breaux'),
    ('Kanye', 'West', 'Kanye West'),
    ('Malaya', 'Ho', 'Malaya Ho');
    
    
    insert into Album(`name`, releaseDate, version) values
		('Beyone', '2013-12-13', '1.0'),
        ('Lemonade', '2016-08-23', '1.0'),
        ('Blonce', '2016-08-20', '1.0');
        
	
insert into Track(`name`, releaseDate, albumID) values
	('Pretty Hurts', '2013-12-13', 1),
    ('Drunk in Love', '2013-12-23', 1);