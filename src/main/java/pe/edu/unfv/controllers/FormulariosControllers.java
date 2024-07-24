package pe.edu.unfv.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pe.edu.unfv.models.InteresesModel;
import pe.edu.unfv.models.PaisModel;
import pe.edu.unfv.models.Usuario2Model;
import pe.edu.unfv.models.Usuario3Model;
import pe.edu.unfv.models.UsuarioCheckboxModel;
import pe.edu.unfv.models.UsuarioModel;
import pe.edu.unfv.models.UsuarioUploadModel;

@Controller
@RequestMapping("/formularios")
public class FormulariosControllers {

	@GetMapping
	public String home() {
		return "formularios/home";
	}

	// Formulario simple
	@GetMapping("/simple")
	public String simple() {
		return "formularios/simple";
	}

	@PostMapping("/simple")
	@ResponseBody
	public String postSimple(@RequestParam(name = "username") String username,
			@RequestParam(name = "correo") String correo, @RequestParam(name = "password") String password) {

		return "username= " + username + " <br/>correo= " + correo + " <br/>password= " + password;
	}

	// Formulario de objetos
	@GetMapping("/objeto")
	public String objeto() {
		return "formularios/objeto";
	}

	@PostMapping("/objeto")
	@ResponseBody
	public String objetoPost(UsuarioModel usuario) {

		return "<h1>Objetos</h1>username= " + usuario.getUsername() + "<br/>correo= " + usuario.getCorreo()
				+ "<br/>password= " + usuario.getPassword();
	}

	// Formulario de objetos 2
	@GetMapping("/objeto2")
	public String objeto2(Model model) {

		model.addAttribute("usuario", new UsuarioModel());
		return "formularios/objeto2";
	}

	@PostMapping("/objeto2")
	@ResponseBody
	public String objetoPost2(UsuarioModel usuario) {

		return "<h1>Objetos</h1>username= " + usuario.getUsername() + "<br/>correo= " + usuario.getCorreo()
				+ "<br/>password= " + usuario.getPassword();
	}

	// Formulario de validaciones
	@GetMapping("/validaciones")
	public String validaciones(Model model) {

		model.addAttribute("usuario", new Usuario2Model());
		return "formularios/validaciones";
	}

	@PostMapping("/validaciones")
	public String validaciones_post(@Valid Usuario2Model usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			return "formularios/validaciones";
		}

		model.addAttribute("usuario", usuario);
		return "formularios/validaciones_result";
	}

	// Formulario de select dinamicos
	@GetMapping("/select_dinamico")
	public String select_dinamico(Model model) {

		model.addAttribute("usuario", new Usuario3Model());
		return "formularios/select_dinamico";
	}

	@PostMapping("/select_dinamico")
	public String select_dinamico_post(@Valid Usuario3Model usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			return "formularios/select_dinamico";
		}

		model.addAttribute("usuario", usuario);
		return "formularios/select_dinamico_result";
	}

	// Formulario de select dinamicos
	@GetMapping("/checkbox")
	public String checkbox(Model model) {

		model.addAttribute("usuario", new UsuarioCheckboxModel());
		return "formularios/checkbox";
	}

	@PostMapping("/checkbox")
	public String checkbox_post(@Valid UsuarioCheckboxModel usuario, BindingResult result, Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("usuario", usuario);
			return "formularios/checkbox";
		}

		model.addAttribute("usuario", usuario);
		return "formularios/checkbox_result";
	}

	// Formulario ejemplo de mensajes flash
	@GetMapping("/flash")
	public String flash(Model model) {
		
		model.addAttribute("usuario", new UsuarioModel());
		return "formularios/flash";
	}
	@PostMapping("/flash")
	public String flash_post(UsuarioModel usuario, RedirectAttributes flash) {
		
		flash.addFlashAttribute("clase", "danger");
		flash.addFlashAttribute("mensaje", "Ejemplo de mensaje flash con ñandu");
		
		return "redirect:/formularios/flash-respuesta";		
	}
	@GetMapping("/flash-respuesta")
	public String flash_respuesta() {
		
		return "formularios/flash_respuesta";
	}
	
	// Formulario upload files
		@GetMapping("/upload")
		public String upload(Model model) {
			
			model.addAttribute("usuario", new UsuarioUploadModel());
			return "formularios/upload";
		}
		
		
		
		
		
		
		
		

	// Formulario de setGenericos
	@ModelAttribute
	public void setGenericos(Model model) {

		List<PaisModel> paisModels = new ArrayList<>();
		paisModels.add(new PaisModel(1, "United States"));
		paisModels.add(new PaisModel(2, "China"));
		paisModels.add(new PaisModel(3, "Bahrain"));
		paisModels.add(new PaisModel(4, "Greece"));
		paisModels.add(new PaisModel(5, "Antarctica"));
		paisModels.add(new PaisModel(6, "Cambodia"));
		paisModels.add(new PaisModel(7, "Ecuador"));
		paisModels.add(new PaisModel(8, "Mexico"));
		paisModels.add(new PaisModel(9, "Egypt"));
		paisModels.add(new PaisModel(10, "Chile"));
		paisModels.add(new PaisModel(11, "Aruba"));
		paisModels.add(new PaisModel(12, "Malaysia"));
		paisModels.add(new PaisModel(13, "Chile"));
		paisModels.add(new PaisModel(14, "Belgium"));
		paisModels.add(new PaisModel(15, "Chile"));
		paisModels.add(new PaisModel(16, "Guatemala"));
		paisModels.add(new PaisModel(17, "Japan"));
		paisModels.add(new PaisModel(18, "Monaco"));
		paisModels.add(new PaisModel(19, "United Kingdom"));
		model.addAttribute("paisModels", paisModels);

		List<InteresesModel> interesesModels = new ArrayList<>();
		interesesModels.add(new InteresesModel(1, "Musica"));
		interesesModels.add(new InteresesModel(2, "Deporte"));
		interesesModels.add(new InteresesModel(3, "Programacion"));
		interesesModels.add(new InteresesModel(4, "Viajes"));
		interesesModels.add(new InteresesModel(5, "Leer"));
		interesesModels.add(new InteresesModel(6, "Redactar"));
		interesesModels.add(new InteresesModel(7, "Bailar"));
		interesesModels.add(new InteresesModel(8, "Comprar"));
		interesesModels.add(new InteresesModel(9, "Economia"));
		interesesModels.add(new InteresesModel(10, "Politica"));
		model.addAttribute("interesesModels", interesesModels);
	}
}
