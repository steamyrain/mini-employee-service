CREATE TABLE employee_on_leave (
	id varchar(255),
	employee_id varchar(255) not null,
	start_date timestamp not null,
	end_date timestamp not null,
	request_date timestamp not null,
	primary key(id)
);
ALTER TABLE employee_on_leave ADD CONSTRAINT fk_employee_on_leave_employee_id FOREIGN KEY(employee_id)
REFERENCES employee(id);
