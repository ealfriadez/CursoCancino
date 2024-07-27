package pe.edu.unfv.controllers;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.zxing.WriterException;

import pe.edu.unfv.services.QrCodeService;

@Controller
@RequestMapping("/qr")
public class QrCoontroller {

	@Autowired
	private QrCodeService qrCodeService;
	
	@GetMapping("")
	public String home() {
		return "qr/home";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		
		String url="https://www.tamila.cl";
		byte[] image = new byte[0];
		try {
			image=this.qrCodeService.createQR(url, 250, 250);
		}catch(WriterException | IOException e){
			
		}
		//convertir el byte en base64 string
		String qrCode=Base64.getEncoder().encodeToString(image); 
		
		model.addAttribute("qrCode", qrCode);
		model.addAttribute("url", url);
		
		return "qr/crear";
	}
}
