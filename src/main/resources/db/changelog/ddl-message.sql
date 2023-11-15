create table message_template (
	id varchar(255),
	name varchar(255) not null,
	template text not null,
	primary key(id)
);
ALTER TABLE message_template ADD CONSTRAINT unq_message_template_name UNIQUE (name);
CREATE INDEX idx_message_template_name ON message_template USING btree (name);
