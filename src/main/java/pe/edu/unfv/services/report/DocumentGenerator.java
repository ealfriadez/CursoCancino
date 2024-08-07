package pe.edu.unfv.services.report;

import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class DocumentGenerator {
 
	@Value("${elio.valores.ruta_upload}")
	private String ruta_upload;
	
	public String htmlToPdf(String processedHtml) {
		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {
			PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
			
			DefaultFontProvider defaultFontProvider = new DefaultFontProvider(false, true, false);
			
			ConverterProperties converterProperties = new ConverterProperties();
			
			converterProperties.setFontProvider(defaultFontProvider);
			
			HtmlConverter.convertToPdf(processedHtml, pdfWriter, converterProperties);
			
			FileOutputStream fileOutputStream = new FileOutputStream(this.ruta_upload + "reportes/categorias.pdf");
			
			byteArrayOutputStream.writeTo(fileOutputStream);
			byteArrayOutputStream.close();
			
			byteArrayOutputStream.flush();
			fileOutputStream.close();
			
			return null;
			
		} catch (Exception e) {
			//exception occured
		}
		
		return null;
	}
}
