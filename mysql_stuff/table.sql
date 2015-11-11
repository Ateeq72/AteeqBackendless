create table rest.user (`sno` mediumint auto_increment,
`name` varchar(255) not null,
`email` varchar(255) not null, 
`password` varchar(255) not null,
primary key(sno)
);

insert into rest.user(name,email,password) values('ateeq','ahmedateeq64@gmail.com','khader6@');
drop table rest.user;
drop table rest.orders;



create table rest.orders(`sno` mediumint auto_increment,
`user` varchar(255) not null,
`dish` varchar(255) not null,
`quantity` varchar(255) not null,
`pno` int(15) not null,
primary key(sno)
);

select * from rest.user;
select * from rest.orders;