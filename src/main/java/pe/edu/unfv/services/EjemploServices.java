package pe.edu.unfv.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EjemploServices {

	public String saludo(){
		return "Hola desde un service inyectado desde Spring";
	}
}
