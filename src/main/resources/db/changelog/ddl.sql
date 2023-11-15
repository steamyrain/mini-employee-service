CREATE TABLE employee (
	id varchar(255) not null,
	name varchar(255) not null,
	position varchar(100) not null,
	email varchar(255) not null,
	manager_id varchar(255),
	primary key(id)
);
ALTER TABLE employee ADD CONSTRAINT unq_employee_email UNIQUE (email);
ALTER TABLE employee ADD CONSTRAINT fk_employee_manager_id FOREIGN KEY(manager_id) REFERENCES employee(id);
CREATE INDEX idx_employee_email ON employee USING btree (email);
