package pe.edu.unfv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.unfv.services.CategoriaService;

@Controller
@RequestMapping("/jpa-repository")
public class JpaController {

	@Autowired
	private CategoriaService categoriaService;
	@GetMapping("")
	public String home(Model model) {
		return "jpa_repository/home";
	}
	
	@GetMapping("/categorias")
	public String categorias(Model model) {
		
		model.addAttribute("datos", this.categoriaService.listar());
		return "jpa_repository/categorias";
	}
}
