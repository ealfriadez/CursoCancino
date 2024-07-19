package pe.edu.unfv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class TemplatesController {

	@GetMapping("")
	public String homer() {
		return "templates/home";
	}
}
