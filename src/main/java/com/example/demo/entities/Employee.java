package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}) )
public class Employee {
	@Id
	@Column(name = "id")
	@GenericGenerator(name = "uuid", type = UUIDGenerator.class)
	@GeneratedValue(generator = "uuid")
	private String id;

	@Nonnull
	@Column(name = "name")
	private String name;

	@Nonnull
	@Column(name = "email")
	private String email;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "manager_id", referencedColumnName = "id")
	private Employee manager;

	@Nonnull
	@Column(name = "position")
	private String position;
}
