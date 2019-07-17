
package org.springframework.hateoas.examples;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
class Employee {

	@Id @GeneratedValue private Long id;
	private String name;
	private String role;

	Employee(String name, String role) {

		this.name = name;
		this.role = role;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}
}
