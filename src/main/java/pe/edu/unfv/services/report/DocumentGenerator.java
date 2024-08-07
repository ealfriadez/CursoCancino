package pe.edu.unfv.services.report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.unfv.models.ProductoMongoModel;
import pe.edu.unfv.util.Utilidades;

@Service
public class DocumentGenerator {
 
	@Value("${elio.valores.ruta_upload}")
	private String ruta_upload;
	
	private List <ProductoMongoModel> productsList;
	
	private XSSFWorkbook workbook;
	
	private XSSFSheet sheet;
	
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
	
	public DocumentGenerator(List <ProductoMongoModel> productsList) {
        this.productsList = productsList;
        workbook = new XSSFWorkbook();
    }
	
	 private void writeHeader() {
	        sheet = workbook.createSheet("Productos");
	        Row row = sheet.createRow(0);
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setBold(true);
	        font.setFontHeight(16);
	        style.setFont(font);
	        createCell(row, 0, "ID", style);
	        createCell(row, 1, "Categoria", style);
	        createCell(row, 2, "Nombre", style);
	        createCell(row, 3, "Slug", style);
	        createCell(row, 4, "Descripcion", style);
	        createCell(row, 5, "Precio", style);
	        createCell(row, 6, "Foto", style);
	}
	 
	 private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
	        sheet.autoSizeColumn(columnCount);
	        Cell cell = row.createCell(columnCount);
	        if (valueOfCell instanceof Integer) {
	            cell.setCellValue((Integer) valueOfCell);
	        } else if (valueOfCell instanceof Long) {
	            cell.setCellValue((Long) valueOfCell);
	        } else if (valueOfCell instanceof String) {
	            cell.setCellValue((String) valueOfCell);
	        } else {
	            cell.setCellValue((Boolean) valueOfCell);
	        }
	        cell.setCellStyle(style);
	}
	 
	 private void write() {
	        int rowCount = 1;
	        CellStyle style = workbook.createCellStyle();
	        XSSFFont font = workbook.createFont();
	        font.setFontHeight(14);
	        style.setFont(font);
	        for (ProductoMongoModel record: productsList) {
	            Row row = sheet.createRow(rowCount++);
	            int columnCount = 0;
	            createCell(row, columnCount++, record.getId(), style);
	            createCell(row, columnCount++, record.getCategoriaId().getNombre(), style);
	            createCell(row, columnCount++, record.getNombre(), style);
	            createCell(row, columnCount++, record.getSlug(), style);
	            createCell(row, columnCount++, record.getDescripcion(), style);
	            createCell(row, columnCount++, Utilidades.numberFormat(record.getPrecio()), style);
				createCell(row, columnCount++, "C:/local/productos_image/" + record.getFoto(), style);
	        }
	 }
	 
	 public void generateExcelFile(HttpServletResponse response) throws IOException {
	        writeHeader();
	        write();
	        ServletOutputStream outputStream = response.getOutputStream();
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();
	    }
}
