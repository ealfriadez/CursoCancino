package pe.edu.unfv.controllers;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import pe.edu.unfv.models.CategoriaMongoModel;
import pe.edu.unfv.models.ProductoMongoModel;
import pe.edu.unfv.services.CategoriaMongoService;
import pe.edu.unfv.services.ProductosMongoService;
import pe.edu.unfv.util.Constantes;
import pe.edu.unfv.util.Utilidades;


@Controller
@RequestMapping("/jpa-mongodb")
public class MongoController {

	@Autowired
	private CategoriaMongoService categoriaMongoService;
	@Autowired
	private ProductosMongoService productosMongoService;
	
	@Value("${elio.valores.base_url_upload}")
	private String base_url_upload;
	
	@Value("${elio.valores.ruta_upload}")
	private String ruta_upload;
	
	@Value("${server.servlet.context-path}")
	private String context_path;
	
	@GetMapping("")
	public String home() {		
		return "jpa_mongodb/home";
	}
	
	@GetMapping("/categorias")
	public String categorias(Model model) {
		model.addAttribute("datos", this.categoriaMongoService.listar());
		return "jpa_mongodb/categorias";
	}

	@GetMapping("/categorias/add")
	public String categorias_add(Model model) {		
		model.addAttribute("categoria", new CategoriaMongoModel());
		return "jpa_mongodb/categorias_add";
	}
	
	@PostMapping("/categorias/add")
	public String categorias_add_post(
			@Valid CategoriaMongoModel categoria,
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
			return "jpa_mongodb/categorias_add";
		}
		
		String slug = Utilidades.getSlug(categoria.getNombre());
		
		if (categoriaMongoService.buscarPorSlug(slug) == false) {
			
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "La categoria ingresada ya existe en la base de datos");			 			
			return "redirect:/jpa-mongodb/categorias/add";
		} else {
			categoria.setSlug(slug);
			this.categoriaMongoService.guardar(categoria);
			
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Se creo el registro satisfactoriamente");			 
			return "redirect:/jpa-mongodb/categorias/add";
		}
	}
	
	@GetMapping("/categorias/edit/{id}")
	public String categorias_edit(@PathVariable("id") String id, Model model) {
				
		CategoriaMongoModel categoriaMongoModel = this.categoriaMongoService.buscarPorId(id);		
		model.addAttribute("categoria", categoriaMongoModel);
		return "jpa_mongodb/categorias_edit";
	}
	
	@PostMapping("/categorias/edit/{id}")
	public String categorias_edit_post(
			@Valid CategoriaMongoModel categoriaMongoModel,
			BindingResult result,
			@PathVariable("id") String id,
			RedirectAttributes flash,
			Model model) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});
	
			model.addAttribute("errores", errores);
			model.addAttribute("categoria", categoriaMongoModel);
			return "jpa_mongodb/categorias_add";
		}
		
		categoriaMongoModel.setSlug(Utilidades.getSlug(categoriaMongoModel.getNombre()));
		this.categoriaMongoService.guardar(categoriaMongoModel);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("mensaje", "Se edito el registro satisfactoriamente");			 
		return "redirect:/jpa-mongodb/categorias/edit/"+id;
	}
	
	@GetMapping("/categorias/delete/{id}")
	public String categorias_delete(
			@PathVariable("id") String id,
			RedirectAttributes flash){
		
		try {
			this.categoriaMongoService.eliminar(id);
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Se elimino el registro satisfactoriamente");			 
			return "redirect:/jpa-mongodb/categorias";
		} catch (Exception e) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "No se puede eliminar el registro. Intentelo mas tarde.");			 
			return "redirect:/jpa-mongodb/categorias";
		}
	}
	
	//===================================================
	//=====================PRODUCTOS=====================
	//===================================================
	
	@GetMapping("/productos")
	public String productos(Model model) {
		
		model.addAttribute("datos", this.productosMongoService.listar());
		return "jpa_mongodb/productos";
	}
	
	@GetMapping("/productos/add")
	public String productos_add(Model model) {
		
		model.addAttribute("producto", new ProductoMongoModel());		
		return "jpa_mongodb/productos_add";
	}
	
	@PostMapping("/productos/add")
	public String productos_add_post(
			@Valid ProductoMongoModel producto, 
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
			model.addAttribute("producto", producto);
			return "jpa-mongodb/productos_add";
		}
		
		if(multiPart.isEmpty()) {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "El archivo para la foto es obligatorio, debe ser JPG|JPEG|PNG");
			return "redirect:/jpa-mongodb/productos/add";
		}
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utilidades.guardarArchivo(multiPart, this.ruta_upload + "productos_image/");
			if(nombreImagen.equals("no")) {
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("mensaje", "El archivo para la foto no es valido, debe ser JPG|JPEG|PNG");
				return "redirect:/jpa-mongodb/productos/add";
			}
			if(!nombreImagen.isEmpty()) {
				producto.setFoto(nombreImagen);
			}
		}
		
		producto.setSlug(Utilidades.getSlug(producto.getNombre()));
		this.productosMongoService.guardar(producto);
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("mensaje", "Se creo el registro satisfactoriamente");
		return "redirect:/jpa-mongodb/productos/add";
	}
	
	@GetMapping("/productos/edit/{id}")
	public String productos_edit(
			@PathVariable("id") String id,
			Model model) {
		
		ProductoMongoModel producto = this.productosMongoService.buscarPorId(id);
		model.addAttribute("producto", producto);
		producto.setCategoriaId(producto.getCategoriaId());		
		return "jpa_mongodb/productos_edit";
	}
	
	@PostMapping("/productos/edit/{id}")
	public String productos_edit_post(
			@Valid ProductoMongoModel producto, 
			BindingResult result,
			RedirectAttributes flash,
			@PathVariable("id") String id,
			Model model,
			@RequestParam("archivoImagen") MultipartFile multiPart) {
		
		if (result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(error -> {
				errores.put(error.getField(),
						"El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});

			model.addAttribute("errores", errores);
			model.addAttribute("producto", producto);
			return "jpa-mongodb/productos_add";
		}
		
		if(!multiPart.isEmpty()) {
			String nombreImagen = Utilidades.guardarArchivo(multiPart, ruta_upload + "productos_image/");
			if(nombreImagen.equals("no")) {
				flash.addFlashAttribute("clase", "danger");
				flash.addFlashAttribute("mensaje", "El archivo para la foto no es valido, debe ser JPG|JPEG|PNG");
				return "redirect:/jpa-mongodb/productos/edit/" + id;
			}
			if(nombreImagen!=null) {
				producto.setFoto(nombreImagen);
			}
		}
		
		producto.setSlug(Utilidades.getSlug(producto.getNombre()));
		this.productosMongoService.guardar(producto);
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("mensaje", "Se edito el registro satisfactoriamente");
		return "redirect:/jpa-mongodb/productos/edit/" + id;
	}
	
	@GetMapping("/productos/delete/{id}")
	public String productos_delete(
			@PathVariable("id") String id,
			RedirectAttributes flash) {
		
		ProductoMongoModel producto = this.productosMongoService.buscarPorId(id);
		File myObj = new File(this.ruta_upload + "productos_image/" + producto.getFoto());
		
		if(myObj.delete()) {
			this.productosMongoService.eliminar(id);
			flash.addFlashAttribute("clase", "success");
			flash.addFlashAttribute("mensaje", "Se elimino el registro satisfactoriamente");
		}else {
			flash.addFlashAttribute("clase", "danger");
			flash.addFlashAttribute("mensaje", "Ocurrio un error, por favor vuelve a intentarlo mas tardes.");
		}
		
		return "redirect:/jpa-mongodb/productos";
	}	
	
	//===================================================
	//==================NEW FEATURES=====================
	//===================================================
	@GetMapping("/productos/categorias/{id}")
	public String productos_categoria(
			@PathVariable("id") String id,			
			Model model) {
		
		CategoriaMongoModel categoria = this.categoriaMongoService.buscarPorId(id);
		if (categoria==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagina no encontrada");			
		}		
		model.addAttribute("datos", this.productosMongoService.listar_por_categorias(categoria));
		model.addAttribute("categoria", categoria);
		return "jpa_mongodb/productos_categoria";
	}
	
	@GetMapping("/productos_paginacion")
	public String productos_paginacion(
			@RequestParam(value = "page", required = false) Integer page,
			Model model) {
		
		Pageable pageable = PageRequest.of((page == null) ? 0 : page, 
				Constantes.CANTIDAD_POR_PAGINA, Sort.by("id").descending());				
		
		model.addAttribute("datos", this.productosMongoService.listar_paginacion(pageable));
		model.addAttribute("paginacion", context_path.replace("/", "").concat("/jpa-mongodb/productos_paginacion"));
		return "jpa_mongodb/productos_paginacion";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categorias", this.categoriaMongoService.listar());
	}
}
