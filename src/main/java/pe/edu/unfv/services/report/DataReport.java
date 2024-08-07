package pe.edu.unfv.services.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import pe.edu.unfv.models.CategoriaMongoModel;

@Service
public class DataReport {

	public Context setData(List<CategoriaMongoModel> categoriaMongoList) {
		
		Context context = new Context();
		
		Map<String, Object> data = new HashMap<>();
		
		data.put("datos", categoriaMongoList);
		
		context.setVariables(data);
		
		return context;
	}
}
