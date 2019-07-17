
package org.springframework.hateoas.examples;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.server.core.TypeReferences.CollectionModelType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;


@Controller
public class HomeController {

	private static final String REMOTE_SERVICE_ROOT_URI = "http://localhost:9000";

	private final RestTemplate rest;

	public HomeController(RestTemplate restTemplate) {
		this.rest = restTemplate;
	}

	
	@GetMapping
	public String index(Model model) throws URISyntaxException {

		Traverson client = new Traverson(new URI(REMOTE_SERVICE_ROOT_URI), MediaTypes.HAL_JSON);
		CollectionModel<EntityModel<Employee>> employees = client //
				.follow("employees") //
				.toObject(new CollectionModelType<EntityModel<Employee>>() {});

		model.addAttribute("employee", new Employee());
		model.addAttribute("employees", employees);

		return "index";
	}

	
	@PostMapping("/employees")
	public String newEmployee(@ModelAttribute Employee employee) throws URISyntaxException {

		Traverson client = new Traverson(new URI(REMOTE_SERVICE_ROOT_URI), MediaTypes.HAL_JSON);
		Link employeesLink = client //
				.follow("employees") //
				.asLink();

		this.rest.postForEntity(employeesLink.expand().getHref(), employee, Employee.class);

		return "redirect:/";
	}
}
