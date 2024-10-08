package pe.edu.unfv.components;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import pe.edu.unfv.models.InteresesModel;

@Component
public class ConvertirInteresesId implements Converter<String, InteresesModel>{

	@Override
	public InteresesModel convert(String source) {
		
		Integer id = Integer.valueOf(source);
		InteresesModel datos = new InteresesModel();
		datos.setId(id);	
		
		return datos;
	}

}
