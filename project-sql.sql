-- adv java 8pm sprk batch

create database sprk_emp_crud;

use sprk_emp_crud;

show tables;

create table employee
(
	emp_id int primary key auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null unique,
    gender varchar(15),
    address varchar(255) not null
);

show tables;
select * from employee;

show create table employee;