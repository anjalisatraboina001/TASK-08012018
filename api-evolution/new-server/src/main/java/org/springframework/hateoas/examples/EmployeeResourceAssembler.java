
package org.springframework.hateoas.examples;

import org.springframework.hateoas.SimpleIdentifiableRepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
class EmployeeResourceAssembler extends SimpleIdentifiableRepresentationModelAssembler<Employee> {

	EmployeeResourceAssembler() {
		super(EmployeeController.class);
	}
}
