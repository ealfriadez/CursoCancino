package pe.edu.unfv.services;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import pe.edu.unfv.util.Constantes;

@Service
@Primary
public class EmailService {
	
	public void sendMail(String mail, String asunto, String name, String product) throws AddressException, MessagingException {
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", Constantes.SMTP_SERVER);
		props.put("mail.smtp.port", Constantes.SMTP_PORT);
		
		Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constantes.SMTP_MAIL, Constantes.SMTP_PASSWORD);				
			}
		});
		
		String htmlContent = this.readHTMLTemplate(name, product);
		
		Message msg = new MimeMessage(session);		
		msg.setFrom(new InternetAddress(Constantes.SMTP_MAIL, false));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
		msg.setSubject(asunto);
		msg.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);
		msg.setSentDate(new Date(0));		
		
		Transport.send(msg);
	}	
	
	private String readHTMLTemplate(String param1, String param2) {
		try(var lines = Files.lines(Constantes.TEMPLATE_PATH)) {
			var html = lines.collect(Collectors.joining());
			
			return html.replace("{name}", param1).replace("{product}", param2);
		}catch (IOException e) {			
			throw new RuntimeException();
		} 
	}	
}
