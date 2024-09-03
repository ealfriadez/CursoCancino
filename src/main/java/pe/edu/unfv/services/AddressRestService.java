package pe.edu.unfv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.edu.unfv.models.UsuarioRestModel;


@Service
@Primary
public class AddressRestService {

	@Autowired
	private RestTemplate dataRest;
	
	@Value("${elio.valores.api}")
	private String apiHost;
	
	public AddressRestService(RestTemplateBuilder builder) {
		this.dataRest = builder.build();
	}
	
	public List<UsuarioRestModel> listar(){
		
		HttpEntity<String> entity = new HttpEntity<String>(this.toString());
		
		ResponseEntity<List<UsuarioRestModel>> response = this.dataRest.exchange(
				apiHost, 
				HttpMethod.GET, 
				entity, 
				new ParameterizedTypeReference<List<UsuarioRestModel>>() {});		
		
		return response.getBody();
	}
}
