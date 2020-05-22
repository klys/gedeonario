/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd_avanzada;

/**
 *
 * @author VROSENDO
 */
import java.io.*;
import java.util.Calendar;

/**
 *
 * @author GeekZero
 */
public class Archivos {

    public void Guardar()
            throws Exception {

        // instancio objetos de escritura
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            // asigno nombre de fichero, mantengo la nomenclatura
            String directorio = System.getProperty("user.dir");
            String separador = System.getProperty("file.separator");
            String nbFold = directorio + separador + "reportes";
            File fichero2 = new File(nbFold);

            this.Log("General", "Creando Folder: " + nbFold, "ARCHIVOS");
            fichero2.mkdirs();

            this.ocultar_Carpeta(nbFold);
            fichero = new FileWriter(directorio + separador + "reportes" + separador + "dbxconnections.ini");
            // asigno PrintWriter a fichero
            System.out.println(fichero+"..................");
            pw = new PrintWriter(fichero);

            this.Log("General", "Creando Archivo: dbxconnections.ini", "ARCHIVOS");
            // escribo los parametros
            pw.println("[DB]");
            pw.println("DirecIp = " + "localhost");
            pw.println("Database = " + "BASE_AVANZADA");
            pw.println("UserName = " + "postgres");//postgre
            pw.println("Password = " + "1234");
            pw.println("DDriver = " + "org.postgresql.Driver");
            pw.println("DirPort = " + "jdbc:postgresql");
            pw.println("DirPort2 = " + "5432");
            pw.println("DirPortT = " + "2");

            // vuelco la memoria al disco duro
            pw.flush();
            this.Log("General", "Archivo Creado: dbxconnections.ini", "ARCHIVOS");
            
        } catch (Exception e) {
            this.Log("Error Critico", e.getMessage(), "ARCHIVOS");
            throw new Exception(e);
        } finally {
            // cierro los canales de escritura
            fichero.close();
            pw.close();
        }// end try
    }// end Guardar

    public String[] Leer() throws Exception {

        // Array a retornar
        String parametros[] = new String[9];
        // instancio objetos de lectura
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {

            String directorio = System.getProperty("user.dir");
            String separador = System.getProperty("file.separator");
            archivo = new File(directorio + separador + "reportes" + separador + "dbxconnections.ini");
            if (!archivo.exists()) {
                Guardar();
                this.ocultar_Carpeta(directorio+separador+"reportes");
            }
            
            //JOptionPane.showMessageDialog(null,directorio+separador+"reportes"+separador+"dbxconnections.ini");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            this.Log("General", "Leyendo Archivo: dbxconnections.ini", "ARCHIVOS");
            // Lectura del fichero
            String linea;
            // indice para el array
            int i = 0;
            while ((linea = br.readLine()) != null) {
                /*
                 * si no es el encabezado corto el tag y solo cargo cargo el
                 * valor despues del = sin los espacios
                 */
                if (i != 0) {
                    System.out.println(linea = linea.substring(10).toString().trim());
                } // end fi
                // System.out.println(linea);
                parametros[i] = linea;
                i++;
            }// end while
        } catch (FileNotFoundException e) {
            this.Log("Error Critico", "No existe el fichero [dbxconnections.ini] \n"
                    + e.getMessage(), "ARCHIVOS");
            //JOptionPane.showMessageDialog(null,e );
            throw new Exception("No existe el fichero [dbxconnections.ini] \n"
                    + e);

        } catch (Exception ex) {
            this.Log("Error Critico", ex.getMessage(), "ARCHIVOS");
            //JOptionPane.showMessageDialog(null,ex );
            throw new Exception(ex);

        } finally {
			// En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
                this.Log("Error Critico", e2.getMessage(), "ARCHIVOS");
                // JOptionPane.showMessageDialog(null,e2 );
                throw new Exception(e2);
            }// end try
        }// end try
        return parametros;
    }// end Guardar

    public void Log(String Usr, String Mensaje, String tipo) throws IOException {
//try{
        String nbFile;
        BufferedWriter bfw;
        FileReader fr = null;

        Object file = new Object();//verificar este tipo de variable para encontrar el correcto
        String nbFold;
        Calendar Calendario = Calendar.getInstance();
        //Fecha del Sistema********************************************************
        String ano = Integer.toString(Calendario.get(Calendar.YEAR));
        String mes = Integer.toString(Calendario.get(Calendar.MONTH) + 1);
        String dia = Integer.toString(Calendario.get(Calendar.DATE));
        String sistema = dia + "/" + mes + "/" + ano;
        //************************************************************************* 
        File fichero1 = null;
        FileWriter fichero = null;
        PrintWriter pw = null;
        if (mes.length() == 1) {
            mes = "0" + mes;
        }

        if (dia.length() == 1) {
            dia = "0" + dia;
        }

        String directorio = System.getProperty("user.dir");
        String separador = System.getProperty("file.separator");
        nbFold = directorio + separador + "log" + separador + ano + mes + dia;
        nbFile = nbFold + separador + Usr + ".xml";//verificar el doble / que antes era solo 1

        File fichero2 = new File(nbFold);
        if (!fichero2.exists()) {
            fichero2.mkdirs();
            this.ocultar_Carpeta(directorio + separador + "log");
        }
        FileWriter fichero3 = new FileWriter(nbFile, true);

        pw = new PrintWriter(fichero3);
        String linea = "<LOG TP='" + tipo + "'><![CDATA[" + sistema + " -> " + Mensaje + "]]></LOG>";
        pw.append(linea);
        pw.flush();
        pw.close();
        fichero3.close();

    }

    public String ocultar_Carpeta(String carpeta) {
        Runtime aplicacion = Runtime.getRuntime();
        String tx = "";
        try {
            Process p = Runtime.getRuntime().exec("cmd /C attrib +h +s " + carpeta);
             this.Log("General", "se Oculto la Carpeta con el siguiente comando: cmd /C attrib +h +s " + carpeta, "ARCHIVOS");
            //System.out.println("cmd.exe attrib +r +h +s " + carpeta);
           // Process p = aplicacion.exec("cmd.exe attrib +r +h +s " + carpeta);
            //System.out.println("Se Oculto");
        } catch (IOException e) {
            System.out.println(e);
        }
        return tx;
    }

}// end class
