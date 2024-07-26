package pe.edu.unfv.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailService {

	@Value("${elio.valores.smtp_server}")
	private String smtp_server;
	@Value("${elio.valores.smtp_port}")
	private String smtp_port;
	@Value("${elio.valores.smtp_username}")
	private String smtp_username;
	@Value("${elio.valores.smtp_password}")
	private String smtp_password;
	
	public void sendMail(String mail, String asunto) {
		
	}
}
