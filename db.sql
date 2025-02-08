Create database  ticket_brokerage;

 CREATE TABLE user (
  user_id bigint NOT NULL AUTO_INCREMENT,
  create_dt datetime(6) NOT NULL,
  status enum('ACTIVE','INACTIVE') NOT NULL,
  updated_dt datetime(6) DEFAULT NULL,
  user_email varchar(256) NOT NULL,
  user_mobile varchar(16) NOT NULL,
  user_name varchar(56) NOT NULL,
  user_password varchar(56) NOT NULL,
  wallet_amount decimal(38,2) NOT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE train (
  train_id bigint NOT NULL AUTO_INCREMENT,
  create_dt datetime(6) NOT NULL,
  status enum('ACTIVE','INACTIVE') NOT NULL,
  train_code bigint NOT NULL,
  train_name varchar(256) NOT NULL,
  updated_dt datetime(6) DEFAULT NULL,
  PRIMARY KEY (train_id)
);

CREATE TABLE location (
  location_id bigint NOT NULL AUTO_INCREMENT,
  create_dt datetime(6) NOT NULL,
  location_code varchar(5) NOT NULL,
  location_name varchar(256) NOT NULL,
  status enum('ACTIVE','INACTIVE') NOT NULL,
  updated_dt datetime(6) DEFAULT NULL,
  PRIMARY KEY (location_id)
);

CREATE TABLE train_location (
  id bigint NOT NULL AUTO_INCREMENT,
  amount decimal(10,2) NOT NULL,
  location_id bigint DEFAULT NULL,
  train_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKl86xkr78gv2geh3kdt90q7lt1 (location_id),
  KEY FKhpp5l8fnpmga6sw0kv3cirp8i (train_id),
  CONSTRAINT FKhpp5l8fnpmga6sw0kv3cirp8i FOREIGN KEY (train_id) REFERENCES train (train_id),
  CONSTRAINT FKl86xkr78gv2geh3kdt90q7lt1 FOREIGN KEY (location_id) REFERENCES location (location_id)
);

 CREATE TABLE ticket (
  ticket_id bigint NOT NULL AUTO_INCREMENT,
  create_dt datetime(6) NOT NULL,
  no_of_passengers int NOT NULL,
  pnr_no varchar(10) NOT NULL,
  ticket_cost decimal(10,2) NOT NULL,
  status enum('BOOKED','DELETED','OPEN') NOT NULL,
  updated_dt datetime(6) DEFAULT NULL,
  end_location_id bigint DEFAULT NULL,
  start_location_id bigint DEFAULT NULL,
  train_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (ticket_id),
  KEY FKiujs33w322i7wng48acq7mwvl (end_location_id),
  KEY FKi9d3k83qq956vax47xs6ssimc (start_location_id),
  KEY FK86c20t7sdlfvrwskmig4xqd4u (train_id),
  KEY FKdvt57mcco3ogsosi97odw563o (user_id),
  CONSTRAINT FK86c20t7sdlfvrwskmig4xqd4u FOREIGN KEY (train_id) REFERENCES train (train_id),
  CONSTRAINT FKdvt57mcco3ogsosi97odw563o FOREIGN KEY (user_id) REFERENCES user (user_id),
  CONSTRAINT FKi9d3k83qq956vax47xs6ssimc FOREIGN KEY (start_location_id) REFERENCES location (location_id),
  CONSTRAINT FKiujs33w322i7wng48acq7mwvl FOREIGN KEY (end_location_id) REFERENCES location (location_id)
);

 CREATE TABLE ticket_buyer (
  ticket_buyer_id bigint NOT NULL AUTO_INCREMENT,
  create_dt datetime(6) NOT NULL,
  ticket_id bigint DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (ticket_buyer_id),
  KEY FK9pi2ykiqwblb21ulcb8dva50y (ticket_id),
  KEY FKeejqrdgviy31fextheos46e6n (user_id),
  CONSTRAINT FK9pi2ykiqwblb21ulcb8dva50y FOREIGN KEY (ticket_id) REFERENCES ticket (ticket_id),
  CONSTRAINT FKeejqrdgviy31fextheos46e6n FOREIGN KEY (user_id) REFERENCES user (user_id)
);

-- Insert Scripts

insert into train values(1, SYSDATE(),'ACTIVE',12639,'Nagercoil Express',null);
insert into train values(2, SYSDATE(),'ACTIVE',12664,'Chennai Central Express',null);

insert into location values(1,SYSDATE(),'MS','Chennai Egmore','ACTIVE',null);
insert into location values(2,SYSDATE(),'NCJ','Nagercoil Junction','ACTIVE',null);
insert into location values(3,SYSDATE(),'MAS','Chennai Central','ACTIVE',null);
insert into location values(4,SYSDATE(),'MDU','Madurai Junction','ACTIVE',null);


insert into train_location values(1,100,1,1);
insert into train_location values(2,150,2,1);
insert into train_location values(3,200,3,2);
insert into train_location values(4,170,4,2);