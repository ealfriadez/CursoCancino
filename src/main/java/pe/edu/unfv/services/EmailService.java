package pe.edu.unfv.services;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import jakarta.mail.Session;

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
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", this.smtp_server);
		props.put("mail.smtp.port", this.smtp_port);
		
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			
		});
	}
}
