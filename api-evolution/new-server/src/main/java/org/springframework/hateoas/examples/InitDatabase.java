
package org.springframework.hateoas.examples;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
class InitDatabase {

	private final EmployeeRepository repository;

	InitDatabase(EmployeeRepository repository) {
		this.repository = repository;
	}

	@Bean
	CommandLineRunner loadEmployees() {

		return args -> {
			repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
			repository.save(new Employee("Bilbo", "Baggins", "burglar"));
		};
	}

}
