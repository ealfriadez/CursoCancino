package pe.edu.unfv.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import pe.edu.unfv.models.Usuario2Model;
import pe.edu.unfv.models.UsuarioModel;

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
	public String validaciones(
			@Valid Usuario2Model usuario, 
			BindingResult result,
			Model model) {

		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors()
			.forEach(error -> {
				errores.put(error.getField(), "El campo ");
			})
		}
		
		model.addAttribute("usuario", usuario);
		return "";
	}
}
