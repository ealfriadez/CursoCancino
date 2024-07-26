package pe.edu.unfv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.unfv.services.EjemploServices;

@Controller
@RequestMapping("/ejemplo-service")
public class EjemploSerciesController {

	@Autowired
	private EjemploServices ejemploServices;
	
	@GetMapping("")
	public String home(Model model) {
		
		model.addAttribute("ejemplo", this.ejemploServices.saludo());
		return "ejemplo_service/home";
	}
}
