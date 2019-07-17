
package org.springframework.hateoas.examples;

import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class EmployeeRepresentationModelAssembler extends SimpleIdentifiableRepresentationModelAssembler<Employee> {

	EmployeeRepresentationModelAssembler() {
		super(EmployeeController.class);
	}
}
