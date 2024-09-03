package pe.edu.unfv.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constantes {

	public static final String SMTP_SERVER="smtp.gmail.com";
	public static final String SMTP_PORT="587";
	public static final String SMTP_MAIL="ealfriadez@gmail.com";
	public static final String SMTP_PASSWORD="wmil xgrt htxt csiv";
	public static final String PARAM_1="elio@net";
	public static final String PARAM_2="content e-mail";
	public static final String PNG="png";
	public static final Path TEMPLATE_PATH = Paths.get("src/main/resources/templates/email/template/email_template.html");
	public static final Integer CANTIDAD_POR_PAGINA=3;
}
