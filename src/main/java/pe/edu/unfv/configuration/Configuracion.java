package pe.edu.unfv.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Configuracion implements WebMvcConfigurer{

	@Value("${elio.valores.ruta_upload}")
	private String ruta_upload;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//WebMvcConfigurer.super.addResourceHandlers(registry);
		
		registry.addResourceHandler("/local/**")
		.addResourceLocations("file:" + this.ruta_upload + "/productos_image");
	}	
}
