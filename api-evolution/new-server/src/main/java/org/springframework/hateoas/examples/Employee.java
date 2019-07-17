
package org.springframework.hateoas.examples;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.StringUtils;


@Data
@NoArgsConstructor
@Entity
class Employee {

	@Id @GeneratedValue private Long id;
	private String firstName;
	private String lastName;
	private String role;

	Employee(String firstName, String lastName, String role) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public Optional<Long> getId() {
		return Optional.ofNullable(this.id);
	}

	
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	
	public void setName(String wholeName) {

		String[] parts = wholeName.split(" ");
		this.firstName = parts[0];
		if (parts.length > 1) {
			this.lastName = StringUtils.arrayToDelimitedString(Arrays.copyOfRange(parts, 1, parts.length), " ");
		} else {
			this.lastName = "";
		}
	}
}
