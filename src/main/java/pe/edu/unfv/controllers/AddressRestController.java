package pe.edu.unfv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.unfv.models.UsuarioRestModel;
import pe.edu.unfv.services.AddressRestService;

@Controller
@RequestMapping("/data-rest")
public class AddressRestController {

	@Autowired
	private AddressRestService addressRestService;
	
	@GetMapping("")
	public String home(Model model) {
		
		List<UsuarioRestModel> datos = this.addressRestService.listar();
		model.addAttribute("datos", datos);
		
		return "data_rest/home";
	}
}
