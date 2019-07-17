
package org.springframework.hateoas.examples;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class EmployeeController {

	private final EmployeeRepository repository;
	private final EmployeeRepresentationModelAssembler assembler;

	EmployeeController(EmployeeRepository repository, EmployeeRepresentationModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}

	@GetMapping("/")
	public RepresentationModel root() {

		RepresentationModel rootResource = new RepresentationModel();

		rootResource.add( //
				linkTo(methodOn(EmployeeController.class).root()).withSelfRel(), //
				linkTo(methodOn(EmployeeController.class).findAll()).withRel("employees"));

		return rootResource;
	}

	@GetMapping("/employees")
	public CollectionModel<EntityModel<Employee>> findAll() {
		return assembler.toCollectionModel(repository.findAll());
	}

	@PostMapping("/employees")
	public ResponseEntity<EntityModel<Employee>> newEmployee(@RequestBody Employee employee) {

		Employee savedEmployee = repository.save(employee);

		return ResponseEntity //
				.created(savedEmployee.getId() //
						.map(id -> linkTo(methodOn(EmployeeController.class).findOne(id)).toUri()) //
						.orElseThrow(() -> new RuntimeException("Failed to create for some reason"))) //
				.body(assembler.toModel(savedEmployee));
	}

	@GetMapping("/employees/{id}")
	public EntityModel<Employee> findOne(@PathVariable Long id) {
		return repository.findById(id) //
				.map(assembler::toModel) //
				.orElseThrow(() -> new RuntimeException("No employee '" + id + "' found"));
	}

}
