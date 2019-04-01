create table User(
uid int not null primary key auto_increment,
username varchar(20) not null,
itcode varchar(20) not null);

create table Wallet(
wid int not null primary key auto_increment,
uid int not null references user(uid),
amount int not null);

create table Trade(
tid int not null primary key auto_increment,
wid int not null references wallet(wid),
volume int not null,
tradetime not null);