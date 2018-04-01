use hadoopdb;
create table pets(id int not null primary key auto_increment,
                  name varchar(64) not null,
                  description varchar(100),
                  notes varchar(4096),
                  weight int,
                  last_update timestamp);

insert into pets
values
(null, 'Captain', 'The DOGE1', 'Golden Retriever Notes', 75, CURRENT_TIMESTAMP);