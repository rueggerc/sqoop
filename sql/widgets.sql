use hadoopdb;
create table widgets(id int not null primary key auto_increment,
                     widget_name varchar(64) not null,
                     price decimal(10,2),
                     design_date date,
                     version int,
                     design_comment varchar(100));

insert into widgets
values
(null, 'sprocket', 0.25, '2018-03-29', 1, 'Connects two gizmos');

insert into widgets
values
(null, 'gizmo', 4.00, '2018-01-16', 4, 'some widget');

insert into widgets
values
(null, 'gadget', 99.99, '2018-02-05', 13, 'Flagshipt product');
