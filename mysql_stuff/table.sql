create table rest.user (`sno` mediumint auto_increment,
`name` varchar(100) not null,
`email` varchar(100) not null, 
`password` varchar(100) not null,
primary key(sno)
);

drop table rest.user;



create table rest.orders(`sno` mediumint auto_increment,
`user` varchar(100) not null,
`dish` varchar(100) not null,
`quantity` varchar(100) not null,
`pno` int(15) not null,
primary key(sno)
);

select * from rest.user;
select * from rest.orders;
