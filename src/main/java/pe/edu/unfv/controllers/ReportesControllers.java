package pe.edu.unfv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import pe.edu.unfv.services.CategoriaMongoService;
import pe.edu.unfv.services.report.DataReport;
import pe.edu.unfv.services.report.DocumentGenerator;

@Controller
@RequestMapping("/reportes")
public class ReportesControllers {

	@Autowired
	private DocumentGenerator documentGenerator;
	
	@Autowired
	private SpringTemplateEngine springTemplateEngine;
	
	@Autowired
	private DataReport dataReport;
	
	@Autowired
	private CategoriaMongoService categoriaMongoService;	
	
	@GetMapping("")
	public String home(Model model) {
		return "reportes/home";
	}
	
	//===================================================
	//=======================PDF=========================
	//===================================================
	
	@PostMapping("/categorias")
	public String categorias() {
		
		String finalHtml = null;
		
		Context context = dataReport.setData(this.categoriaMongoService.listar());
		
		finalHtml = springTemplateEngine.process("reportes/categorias", context);
		
		documentGenerator.htmlToPdf(finalHtml);
		
		return "Success";
	}
	
}
