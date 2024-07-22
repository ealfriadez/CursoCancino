package pe.edu.unfv.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")
public class TemplatesController {

	@GetMapping("/")
	public String homer(Model model) {
		String nombre = "Eleazar Alfredo Alfriadez Yriarte";
		String pais = "Peru";
		model.addAttribute("nombreVariable", nombre);
		model.addAttribute("paisVariable", pais);
		return "templates/home";
	}
	
	@GetMapping("/atributos")
	public String atributos(Model model) {
		Integer num1 = 15;
		Integer num2 = 13;
		Integer cifra = 12345;
		Date fecha = new Date();
		List<String> paises = new ArrayList<String>();
		paises.add("Peru");
		paises.add("Ecuador");
		paises.add("Colombia");
		paises.add("Brasil");
		paises.add("Bolivia");
		
		model.addAttribute("num1Variable", num1);
		model.addAttribute("num2Variable", num2);
		model.addAttribute("cifraVariable", cifra);
		model.addAttribute("fechaVariable", fecha);
		model.addAttribute("paisesVariable", paises);
		        
		return "templates/atributos";
	}
	
	@GetMapping("/estaticos")
	public String estaticos(Model model) {
		return "templates/estaticos";
	}
	
	@GetMapping("/estaticos2")
	public String estaticos2(Model model) {
		return "templates/estaticos2";
	}
	
	@GetMapping("/ajax")
	public String ajax(Model model) {
		return "templates/ajax";
	}
	
	@GetMapping("/peticion")
	public String peticion(Model model) {
		return "templates/peticion";
	}
}
