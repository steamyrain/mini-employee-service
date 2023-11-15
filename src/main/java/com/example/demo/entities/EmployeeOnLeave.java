package com.example.demo.entities;

import java.time.Instant;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import io.micrometer.common.lang.NonNull;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_on_leave")
public class EmployeeOnLeave {
	@Id
	@GenericGenerator(name = "uuid", type = UUIDGenerator.class)
	@GeneratedValue(generator = "uuid")
	private String id;

	@Nonnull
	@Column(name = "start_date")
	private Instant startDate;

	@NonNull
	@Column(name = "end_date")
	private Instant endDate;

	@OneToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "id")
	private Employee employee;

	@Nonnull
	@Column(name = "request_date")
	private Instant requestDate;
}
