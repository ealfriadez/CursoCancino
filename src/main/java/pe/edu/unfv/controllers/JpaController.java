package pe.edu.unfv.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pe.edu.unfv.models.CategoriaModel;
import pe.edu.unfv.models.ProductosModel;
import pe.edu.unfv.services.CategoriaService;
import pe.edu.unfv.services.ProductosService;
import pe.edu.unfv.util.Utilidades;

@Controller
@RequestMapping("/jpa-repository")
public class JpaController {

	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private ProductosService productosService;
	
	@Value("${elio.valores.base_url_upload}")
	private String base_url_upload;
	
	@Value("${elio.valores.ruta_upload}")
	private String ruta_upload;
	
	@GetMapping("")
	public String home(Model model) {
		return "jpa_repository/home";
	}
	
	@GetMapping("/categorias")
	public String categorias(Model model) {
		
		model.addAttribute("datos", this.categoriaService.listar());
		return "jpa_repository/categorias";
	}
	
	@GetMapping("/categorias/add")
	public String categorias_add(Model model) {
		
		CategoriaModel categoriaModel = new CategoriaModel();
		
		model.addAttribute("categoriaModel", categoriaModel);
		return "jpa_repository/categorias_add";
	}
	
	@PostMapping("/categorias/add")
	public String categorias_add_post(
			@Valid CategoriaModel categoria, 
			BindingResult result,
			RedirectAttributes flash,
			Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("categoria", categoria);
			return "jpa_repository/categorias_add";
		}
		
		String slug = Utilidades.getSlug(categoria.getNombre());		
		if (categoriaService.buscarPorSlug(slug) == false) {
			
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "La categoria ingresada ya existe en la base de datos");			 			
			return "redirect:/jpa-repository/categorias/add";
		} else {
			categoria.setSlug(slug);
			this.categoriaService.guardar(categoria);
			
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Se creo el registro satisfactoriamente");			 
			return "redirect:/jpa-repository/categorias/add";
		}
	}
	
	@GetMapping("/categorias/edit/{id}")
	public String categorias_edit(@PathVariable("id") Integer id, Model model) {
				
		CategoriaModel categoriaModel = this.categoriaService.buscarPorId(id);		
		model.addAttribute("categoriaModel", categoriaModel);
		return "jpa_repository/categorias_edit";
	}
	
	@PostMapping("/categorias/edit/{id}")
	public String categorias_edit_post(
			@Valid CategoriaModel categoria, 
			BindingResult result,
			RedirectAttributes flash,
			@PathVariable("id") Integer id,
			Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("categoria", categoria);
			return "jpa_repository/categorias_add";
		}
		
		categoria.setSlug(Utilidades.getSlug(categoria.getNombre()));
		this.categoriaService.guardar(categoria);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("mensaje", "Se edito el registro satisfactoriamente");			 
		return "redirect:/jpa-repository/categorias/edit/"+id;
	}
	
	@GetMapping("/categorias/delete/{id}")
	public String categorias_delete(
			@PathVariable("id") Integer id,
			RedirectAttributes flash){
		
		try {
			this.categoriaService.eliminar(id);
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Se elimino el registro satisfactoriamente");			 
			return "redirect:/jpa-repository/categorias";
		} catch (Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "No se puede eliminar el registro. Intentelo mas tarde.");			 
			return "redirect:/jpa-repository/categorias";
		}
	}
	
	//===================================================
	//=====================PRODUCTOS=====================
	//===================================================	
	@GetMapping("/productos")
	public String productos(Model model) {
		
		model.addAttribute("datos", this.productosService.listar());
		return "jpa_repository/productos";
	}
	
	@GetMapping("/productos/add")
	public String productos_add(Model model) {
		
		ProductosModel productos = new ProductosModel();
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", this.categoriaService.listar());
		return "jpa_repository/productos_add";
	}
	
	@PostMapping("/productos/add")
	public String productos_add_post(
			@Valid ProductosModel producto, 
			BindingResult result,
			RedirectAttributes flash,
			Model model,
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("categoria", producto);
			return "jpa_repository/productos_add";
		}
		if(multiPart.isEmpty()) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "El archivo para la foto es obligatorio, debe ser JPG|JPEG|PNG");
			return "redirect:/jpa_repository/productos/add";
		}
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utilidades.guardarArchivo(multiPart, this.ruta_upload+"udemy/");
			if(nombreImagen.equals("no")) {
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("mensaje", "El archivo para la foto no es valido, debe ser JPG|JPEG|PNG");
				return "redirect:/formularios/upload";
			}
			if(!nombreImagen.isEmpty()) {
				producto.setFoto(nombreImagen);
			}
		}
		
		producto.setSlug(Utilidades.getSlug(producto.getNombre()));
		this.productosService.guardar(producto);
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("mensaje", "Se creo el registro satisfactoriamente");
		return "jpa_repository/productos_add";
	}
	
	
	
	
	
	
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("base_url_upload", this.base_url_upload);
	}
}