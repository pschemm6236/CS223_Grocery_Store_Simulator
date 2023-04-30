CREATE DATABASE IF NOT EXISTS csc223Giraffes;
USE csc223Giraffes;

DROP TABLE IF EXISTS sim_results; 
CREATE TABLE sim_results (
    id int(11) NOT NULL AUTO_INCREMENT,
    customer_id int(11) NOT NULL,
    arrival_time int(11) NOT NULL,
    service_time int(11) NOT NULL,
    departure_time int(11) NOT NULL,
    wait_time int(11) NOT NULL,
    queue varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4; 


DROP TABLE IF EXISTS full_queues_idle_time;
CREATE TABLE full_queues_idle_time(
id int(11) NOT NULL AUTO_INCREMENT, 
    queue varchar(255) NOT NULL,
    idle_time int(11) NOT NULL,
    
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4; 



DROP TABLE IF EXISTS satisfied_customer;
CREATE TABLE satisfied_customer(
id int(11) NOT NULL AUTO_INCREMENT, 
    customer_id int(11) NOT NULL,
    arrival_time int(11) NOT NULL,
    service_time int(11) NOT NULL,
    departure_time int(11) NOT NULL,
    wait_time int(11) NOT NULL,
    queue varchar(255) NOT NULL,
    satisfied varchar(255) NOT NULL DEFAULT 'TRUE',
   PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4; 

DROP TABLE IF EXISTS dissatisfied_customer;
CREATE TABLE dissatisfied_customer(
id int(11) NOT NULL AUTO_INCREMENT, 
    customer_id int(11) NOT NULL,
    arrival_time int(11) NOT NULL,
    service_time int(11) NOT NULL,
    departure_time int(11) NOT NULL,
    wait_time int(11) NOT NULL,
    queue varchar(255) NOT NULL,
    satisfied varchar(255) NOT NULL DEFAULT 'FALSE',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4; 



DROP TABLE IF EXISTS self_queues_idle_time;
CREATE TABLE self_queues_idle_time(
id int(11) NOT NULL AUTO_INCREMENT, 
    queue varchar(255) NOT NULL,
    idle_time int(11) NOT NULL,
    
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4; 

