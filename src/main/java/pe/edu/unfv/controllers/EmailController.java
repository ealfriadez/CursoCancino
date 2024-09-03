package pe.edu.unfv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import pe.edu.unfv.services.EmailService;
import pe.edu.unfv.util.Constantes;

@Controller
@RequestMapping("/email")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@GetMapping("")
	public String home(Model model) {
		return "email/home";
	}
	
	@GetMapping("/send")
	public String send(Model model, RedirectAttributes flash) throws AddressException, MessagingException {		
		
		this.emailService.sendMail("ealfriadez@gmail.com", "Probando envio de mensajes desde Spring", Constantes.PARAM_1, Constantes.PARAM_2);
		
		flash.addFlashAttribute("clase", "success");
		flash.addFlashAttribute("mensaje", "El envio del E-Mail fue enviado con exito!!!");
		return "redirect:/email";
	}
}
