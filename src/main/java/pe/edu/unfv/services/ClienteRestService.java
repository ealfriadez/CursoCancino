package pe.edu.unfv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class ClienteRestService {

	@Autowired
	private RestTemplate clienteRest;
	
	@Value("${elio.valores.api}")
	private String apiHost;
}
