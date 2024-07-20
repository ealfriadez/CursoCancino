package pe.edu.unfv.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public String home() {		
		return "home/home";
	}	
	
	@GetMapping("/nosotros")
	@ResponseBody
	public String nosotros() {
		return "Hola desde nosotros";
	}
	
	@GetMapping("/parametros/{id}/{slug}")
	@ResponseBody
	public String parametros(@PathVariable("id") Long id, @PathVariable("slug") String slug) {		
		return "El parametro id es:".concat(id.toString()).concat(" | ").concat(slug);
	}
	
	@GetMapping("/query-string")
	@ResponseBody
	public String query_string(@RequestParam("id") Long id, @RequestParam("slug") String slug) {		
		return "El parametro id es: ".concat(id.toString()).concat(" | ").concat(slug);
	}
	
	@Value("${elio.valores.nombre}")
	private String elioValoresNombre;
	
	@GetMapping("/valores")
	@ResponseBody
	public String valores() {
		return "elio.valores.nombre= ".concat(this.elioValoresNombre);
	}
	
}
