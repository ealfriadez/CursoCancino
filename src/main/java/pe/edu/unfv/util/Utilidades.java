package pe.edu.unfv.util;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


public class Utilidades {

	public static String guardarArchivo(MultipartFile multipartFile, String ruta) {
		if(Utilidades.validaImagen(multipartFile.getContentType()) == false) {
			return "no";
		}else {
			long time = System.currentTimeMillis();
			String nombre = time + Utilidades.getExtension(multipartFile.getContentType());
			try {
				File imageFile = new File(ruta+nombre);
				multipartFile.transferTo(imageFile);
				return nombre;
			} catch (IOException e) {
				return null;
			}
		}
	}
	
	public static boolean validaImagen(String mime) {
		
		boolean retorno = false;
		switch (mime) {
			case "image/jpeg": 
				retorno=true;
			break;
			case "image/jpg": 
				retorno=true;
			break;
			case "image/png": 
				retorno=true;
			break;
			default:
				retorno=false;
			break;
		}
		return retorno;
	}
	
public static String getExtension(String mime) {
		
		String retorno = "";
		switch (mime) {
			case "image/jpeg": 
				retorno=".jpg";
			break;
			case "image/jpg": 
				retorno=".jpg";
			break;
			case "image/png": 
				retorno=".png";
			break;			
		}
		return retorno;
	}
}
