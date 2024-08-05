package pe.edu.unfv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/reportes")
public class ReportesControllers {

	@GetMapping("")
	public String home(Model model) {
		return "reportes/home";
	}
	
	//===================================================
	//=======================PDF=========================
	//===================================================
	private final TemplateEngine templateEngine;
	
	public ReportesControllers(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("/pdf")
	public ResponseEntity<?> productos_pdf(HttpServletRequest request, HttpServletResponse response){
		WebContext context = new WebContext(request, response, this.servletContext);
		context.setVariables("titulo", "PDF Dinamico desde Spring Boot");
	}
}
