package pe.edu.unfv.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.servlet.http.HttpServletResponse;
import pe.edu.unfv.models.ProductoMongoModel;
import pe.edu.unfv.services.CategoriaMongoService;
import pe.edu.unfv.services.ProductosMongoService;
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

	@Autowired
	private ProductosMongoService productoService;		

	@GetMapping("")
	public String home(Model model) {
		return "reportes/home";
	}

	// ===================================================
	// =======================PDF=========================
	// ===================================================

	@PostMapping("/pdf/categorias")
	public String categorias() {

		String finalHtml = null;

		Context context = dataReport.setData(this.categoriaMongoService.listar());

		finalHtml = springTemplateEngine.process("reportes/categorias", context);

		documentGenerator.htmlToPdf(finalHtml);

		return "reportes/home";
	}
	
	// ===================================================
	// =======================PDF=========================
	// ===================================================

	@GetMapping("/export-to-excel")
	public void exportIntoExcelFile(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=products_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);

		List<ProductoMongoModel> listOfProducts = productoService.listar();
		DocumentGenerator generator = new DocumentGenerator(listOfProducts);
		generator.generateExcelFile(response);
	}
}
