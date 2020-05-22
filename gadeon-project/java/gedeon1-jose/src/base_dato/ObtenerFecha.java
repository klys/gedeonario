package base_dato;

/**
 * @file ObtenerFecha.java
 * @version 1.1
 * @author Linea de Codigo (http://lineadecodigo.com)
 * @date   16-octubre-2007
 * @url    http://lineadecodigo.com/2007/10/16/obtener-fecha-actual-con-java/
 * @description Obtener la fecha del d�a con Java.  
 */

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ObtenerFecha {

	public String ObtenerFecha() {

	   System.out.println (new Date());
	   
	   Calendar c = new GregorianCalendar(); 
	   
	   String dia, mes, annio;
	   
	   dia = Integer.toString(c.get(Calendar.DATE));
	   mes = Integer.toString(c.get(Calendar.MONTH) + 1 );
	   annio = Integer.toString(c.get(Calendar.YEAR));
	   return(annio+"-"+mes+"-"+dia);
	   //System.out.println (dia + "/" + mes +"/" + annio);
		
		
	}

}
