package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message_template", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class MessageTemplate {

	@Id
	@GenericGenerator(name = "uuid", type = UUIDGenerator.class)
	@GeneratedValue(generator = "uuid")
	private String id;

	@Nonnull
	@Column(name = "name")
	private String name;

	@Nonnull
	@Column(name = "template")
	private String template;
}
