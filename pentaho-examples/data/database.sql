CREATE TABLE marital_status(
	´code´ CHAR(1) PRIMARY KEY,
	description VARCHAR(100)
);

CREATE TABLE address_type(
	´code´ CHAR(1) PRIMARY KEY,
	description VARCHAR(100)
);

CREATE TABLE person(
	id INT(11) PRIMARY KEY,
	first_name VARCHAR(100),
	last_name VARCHAR(100),
	age INT,
	marital_status_code CHAR(1),
	FOREIGN KEY (marital_status_code) REFERENCES marital_status (´code´)
);

CREATE TABLE telephone(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	telephone_number VARCHAR(100)
);

CREATE TABLE address(
	id INT(11) PRIMARY KEY AUTO_INCREMENT,
	line1 VARCHAR(100),
	line2 VARCHAR(100),
	line3 VARCHAR(100),
	address_type_code CHAR(1),
	FOREIGN KEY (address_type_code) REFERENCES address_type(´code´)
);

CREATE TABLE person_telephone(
	person_id INT(11),
	telephone_id INT(11),
	PRIMARY KEY (person_id, telephone_id)
);

CREATE TABLE person_address(
	person_id INT(11),
	address_id INT(11),
	PRIMARY KEY (person_id, address_id)
);


INSERT INTO address_type VALUES('M', 'Main Address');
INSERT INTO address_type VALUES('B', 'Billing Address');

INSERT INTO marital_status VALUES('S', 'Single');
INSERT INTO marital_status VALUES('M', 'Married');
INSERT INTO marital_status VALUES('D', 'Divorced');
INSERT INTO marital_status VALUES('W', 'Widower');
