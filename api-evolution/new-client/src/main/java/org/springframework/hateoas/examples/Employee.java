
package org.springframework.hateoas.examples;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
class Employee {

	private Long id;
	private String firstName;
	private String lastName;
	private String role;
}
