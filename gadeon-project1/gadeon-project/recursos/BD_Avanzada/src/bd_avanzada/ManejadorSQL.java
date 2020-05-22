package bd_avanzada;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ManejadorSQL {

    private Connection c; // conexion
    private Statement q; // query
    private String driver, nombre_db, ip, port, user, pass, jdbc;
    private String clave, usuario, tipo, id, nombre, apellido, cedula, direccion, rol, seguridad, respuesta, estatus;
    private String h2;
    private String[] lineas;
    private Calendar calendario = new GregorianCalendar();

    public ManejadorSQL(String db_name, String ip, String user, String pass, String driver2, String port2, String jdbc2) {

        this.ip = ip;
        this.nombre_db = db_name;
        this.user = user;
        this.pass = pass;
        this.driver = driver2;
        this.port = port2;
        this.jdbc = jdbc2;


        Conectar();
    }

    public Statement realizarstatment() {
        try {
            q = c.createStatement();

        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (q);
    }

    private boolean Conectar() {
        try {
            Class.forName(this.getDriver());
            this.setC(DriverManager.getConnection(this.getJdbc() + "://" + this.getIp() + ":" + this.getPort() + "/" + this.getNombre_db(), this.getUser(), this.getPass()));
            System.out.println("Conexion Abierta....!!!");
            if (this.getC() != null) {
                this.setQ(getC().createStatement());
            }
            return true;
        } catch (ClassNotFoundException ex) {
            try {
                this.Log("Error Critico", ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex);
            System.out.println("[ClassNotFoundException]Ocurrio un error al conectar con la base de datos.");
            return false;
            //Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            try {
                this.Log("Error Critico", ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println(ex);
            System.out.println("[SQLException]Ocurrio un error al conectar con la base de datos.");
            return false;
        }
    }

    public void Desconectar() {
        try {
            getC().close();
            System.out.println("Conexion Cerrada....!!!");
        } catch (SQLException ex) {
            try {
                this.Log("Error Critico", ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection devolverconexion() {

        return (this.getConexion());
    }

    private String toHexadecimal(byte[] digest) {

        String hash = "";

        for (byte aux : digest) {

            int b = aux & 0xff;

            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }

            hash += Integer.toHexString(b);

        }

        return hash;

    }

    public String getStringMessageDigest(String message, String algorithm) {

        byte[] digest = null;

        byte[] buffer = message.getBytes();

        try {

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            messageDigest.reset();

            messageDigest.update(buffer);

            digest = messageDigest.digest();

        } catch (NoSuchAlgorithmException ex) {

            System.out.println("Error creando Digest");

        }

        return toHexadecimal(digest);

    }

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

    public ResultSet contar_preguntas(String tabla) {//muestra cual es el ultimo reguistro ordenada de manera ascendente
        ResultSet set, set2 = null;
        try {
            set = this.getQ().executeQuery("SELECT id_pregunta FROM " + tabla + " order by id_pregunta ASC");
            while (set.next()) {
                set2 = set;
                if (set2.isLast()) {
                    break;
                }
            }
            return set2;
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int SelectCount(String tabla, String Where) {
        ResultSet set, set2 = null;
        int cantidadFilas = 0;
        //int cont=0;
        try {
            set = this.q.executeQuery("SELECT * FROM " + tabla + " WHERE " + Where + ";");
            try {
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {
                set2 = set;
                cantidadFilas++;
            }

        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");

            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return cantidadFilas;
    }

    public String fecha_sistema() {
        Calendar Calendario = Calendar.getInstance();
        //Fecha del Sistema********************************************************
        String ano = Integer.toString(Calendario.get(Calendar.YEAR));
        String mes = Integer.toString(Calendario.get(Calendar.MONTH) + 1);
        String dia = Integer.toString(Calendario.get(Calendar.DATE));
        String sistema = dia + "/" + mes + "/" + ano;
        //************************************************************************* 
        return sistema;
    }

    public boolean guardarfoto(String expediente, String name, String apellido, String sexo, int edad, String n_imagen, String ruta, String se) {
        FileInputStream fis = null;
        try {

            File file = new File(ruta);
            fis = new FileInputStream(file);
            PreparedStatement pstm = getC().prepareStatement("INSERT into "
                    + " estudiantes(cod_expediente,nombre , apellido, sexo, edad,nombre_img,imagen,nombre_s) " + " VALUES(?,?,?,?,?,?,?,?)");
            pstm.setString(1, expediente);
            pstm.setString(2, name);
            pstm.setString(3, apellido);
            pstm.setString(4, sexo);
            pstm.setInt(5, edad);
            pstm.setString(6, n_imagen);
            pstm.setBinaryStream(7, fis, (int) file.length());
            pstm.setString(8, se);
            pstm.execute();
            pstm.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarfoto(String tabla, String id, String name, String ruta) {
        FileInputStream fis = null;
        try {
            File file = new File(ruta);
            fis = new FileInputStream(file);
            PreparedStatement pstm = getC().prepareStatement("UPDATE"
                    + " " + tabla + " SET " + name + ",imagen='" + fis + "' WHERE " + id + "");

            //  pstm.setString(1, name);
            //  pstm.setBinaryStream(2, fis,(int) file.length());
            pstm.executeUpdate();
            pstm.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
            System.out.println("2");
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public Image ConvertirImagen(byte[] bytes, String tipo) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName(tipo);
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }

    public boolean guardarfotopreg(String pre, String res, int cat, int ni, String u, String d, String t, String c, int p, String n_imagen, String ruta, String categoria) {
        FileInputStream fis = null;

        try {

            File file = new File(ruta);
            fis = new FileInputStream(file);
            PreparedStatement pstm = this.getC().prepareStatement("INSERT into "
                    + " preguntas(preg,resp,id_supcat,niv,opc1,opc2,opc3,opc4,punt,nombre_img,imagen,cat) " + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            pstm.setString(1, pre);
            pstm.setString(2, res);
            pstm.setInt(3, cat);
            pstm.setInt(4, ni);
            pstm.setString(5, u);
            pstm.setString(6, d);
            pstm.setString(7, t);
            pstm.setString(8, c);
            pstm.setInt(9, p);
            pstm.setString(10, n_imagen);
            pstm.setBinaryStream(11, fis, (int) file.length());
            pstm.setString(12, categoria);
            pstm.execute();
            pstm.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    /**
     * Uso: UPDATE tbl_name SET col1 = val1... WHERE condicion
     */
    public boolean Update(String tabla, String set, String codition) {
        try {

            this.getQ().executeUpdate("UPDATE " + tabla + " SET " + set + " WHERE " + codition + ";");
            try {
                this.Log(this.getUsuario(), "UPDATE " + tabla + " SET " + set + " WHERE " + codition + "; " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean Updatetodo(String tabla, String set) {
        try {
            this.getQ().executeUpdate("UPDATE " + tabla + " SET " + set + "");

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Uso: INSERT INTO table_name VALUES (value1,value2,value3,...)
     */
    public boolean Insert(String tabla, String values) {
        try {
            this.getQ().executeUpdate("INSERT INTO " + tabla + " VALUES(" + values + ");");
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + " VALUES(" + values + "); " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + " VALUES(" + values + "); " + calendario.getTime(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean Insert(String tabla, String campos, String values) {
        try {
            this.getQ().executeUpdate("INSERT INTO " + tabla + "(" + campos + ") VALUES(" + values + ");");
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + "(" + campos + ") VALUES(" + values + "); " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + "(" + campos + ") VALUES(" + values + "); " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Not yet tested!! DELETE FROM table_name WHERE some_column=some_value
     *
     * @param tabla
     * @return cierto o falso
     */
    public boolean Delete(String tabla, String codition) {
        try {
            this.getQ().execute("DELETE FROM " + tabla + " WHERE " + codition);
            try {
                this.Log(this.getUsuario(), "DELETE FROM " + tabla + " WHERE " + codition + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "DELETE FROM " + tabla + " WHERE " + codition + calendario.getTime(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     *
     * SELECT column_name,column_name FROM table_name
     */
    public ResultSet Select(String tabla, String columns) {
        ResultSet set;
        try {
            set = this.getQ().executeQuery("SELECT " + columns + " FROM " + tabla + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + "; " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return set;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + "; " + calendario.getTime(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet Select(String tabla, String columns, String Where) {
        ResultSet set;
        try {
            set = this.getQ().executeQuery("SELECT " + columns + " FROM " + tabla + " WHERE " + Where + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return set;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet Select2(String tabla, String columns, String Where) {
        ResultSet set, set2 = null;
        try {
            set = this.getQ().executeQuery("SELECT " + columns + " FROM " + tabla + " WHERE " + Where + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {
                set2 = set;
                break;
            }
            return set2;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean Select3(String tabla, String columns, String Where) {
        ResultSet set, set2 = null;
        try {
            set = this.getQ().executeQuery("SELECT " + columns + " FROM " + tabla + " WHERE " + Where + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {
                set2 = set;
                return true;
            }

        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    public ResultSet SelectUltimo(String tabla, String campo) {
        ResultSet set, set2 = null;
        try {
            set = this.getQ().executeQuery("SELECT * FROM " + tabla + " where " + campo + "");
            try {
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + campo + "; " + calendario.getTime(), "BD");
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {
                set2 = set;
                if (set2.isLast()) {
                    break;
                }
            }
            return set2;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + campo + "; " + calendario.getTime(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet SelectUltimo2(String tabla) {
        ResultSet set, set2 = null;
        try {
            set = this.getQ().executeQuery("SELECT * FROM " + tabla + "");
            while (set.next()) {
                set2 = set;
                if (set2.isLast()) {
                    break;
                }
            }
            return set2;
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean admin_existe(String usuario, String clave) {
        try {
            // "select * from public.usuario where nombre_usuario='"+n+"' and clave='"+c+"'";
            ResultSet t = Select("public.personales", "nombre_usuario, clave", "nombre_usuario= '" + usuario + "' and clave = '" + clave + "'");
            if (t.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean Respuesta_Existe(String pregunta, String respuesta, String usuario) {
        try {
            // "select * from public.usuario where nombre_usuario='"+n+"' and clave='"+c+"'";
            ResultSet t = Select("public.usuario", "pregunta, respuesta", "pregunta = '" + pregunta + "' and respuesta = '" + respuesta + "' and nombreu='" + usuario + "'");
            if (t.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     *
     * @param militarCedula
     * @return
     */
    public boolean ExisteUnCampo(String tabla, String campos, String campoClave, String nombreUsuario) {
        try {
            // "select * from public.usuario where nombre_usuario='"+n+"' and clave='"+c+"'";
            ResultSet t = Select(tabla, campos, campoClave + "= '" + nombreUsuario + "'");
            if (t.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), ex.getMessage(), "BD");
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ExisteCampo(String tabla, String campos, String campoClave) {
        try {
            // "select * from public.usuario where nombre_usuario='"+n+"' and clave='"+c+"'";
            ResultSet t = Select(tabla, campos, campoClave);
            if (t.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Connection getConexion() {
        return this.getC();
    }

    public Statement getStatement() {
        return this.getQ();
    }

    //funciones para modificar las fotos de la tabla preguntas
    public boolean modificarfoto(String id_p, String preg, String resp, String id_sub, String niv, String op1, String op2, String op3, String op4, String punt, String nombre, String imagen, String cat) {
        FileInputStream fis = null;
        try {
            File file = new File(imagen);
            fis = new FileInputStream(file);
            PreparedStatement pstm = getC().prepareStatement("UPDATE preguntas "
                    + "set preg = ? ,"
                    + "resp = ? ,"
                    + "id_supcat = ? ,"
                    + "niv = ? ,"
                    + "opc1 = ? ,"
                    + "opc2 = ? ,"
                    + "opc3 = ? ,"
                    + "opc4 = ? ,"
                    + "punt = ? ,"
                    + "nombre_img = ? ,"
                    + "imagen = ? ,"
                    + "cat = ? "
                    + "WHERE id_pregunta = ? ");

            pstm.setString(1, preg);
            pstm.setString(2, resp);
            pstm.setInt(3, Integer.parseInt(id_sub));
            pstm.setInt(4, Integer.parseInt(niv));
            pstm.setString(5, op1);
            pstm.setString(6, op2);
            pstm.setString(7, op3);
            pstm.setString(8, op4);
            pstm.setInt(9, Integer.parseInt(punt));
            pstm.setString(10, nombre);
            pstm.setBinaryStream(11, fis, (int) file.length());
            pstm.setString(12, cat);
            pstm.setInt(13, Integer.parseInt(id_p));

            pstm.execute();
            pstm.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarfoto2(String id_p, String preg, String resp, String id_sub, String niv, String op1, String op2, String op3, String op4, String punt, String cat) {
        try {
            PreparedStatement pstm = getC().prepareStatement("UPDATE preguntas "
                    + "set preg = ? ,"
                    + "resp = ? ,"
                    + "id_supcat = ? ,"
                    + "niv = ? ,"
                    + "opc1 = ? ,"
                    + "opc2 = ? ,"
                    + "opc3 = ? ,"
                    + "opc4 = ? ,"
                    + "punt = ? ,"
                    + "cat = ? "
                    + "WHERE id_pregunta = ? ");

            pstm.setString(1, preg);
            pstm.setString(2, resp);
            pstm.setInt(3, Integer.parseInt(id_sub));
            pstm.setInt(4, Integer.parseInt(niv));
            pstm.setString(5, op1);
            pstm.setString(6, op2);
            pstm.setString(7, op3);
            pstm.setString(8, op4);
            pstm.setInt(9, Integer.parseInt(punt));

            pstm.setString(10, cat);
            pstm.setInt(11, Integer.parseInt(id_p));
            pstm.execute();
            pstm.close();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean modificarfoto3(String cod, String nombre, String apellido, String sexo, String edad, String nombre_img, String imagen, String nombre_s) {
        FileInputStream fis = null;
        try {
            File file = new File(imagen);
            fis = new FileInputStream(file);
            PreparedStatement pstm = getC().prepareStatement("UPDATE estudiantes "
                    + "set nombre = ? ,"
                    + "apellido = ? ,"
                    + "sexo = ? ,"
                    + "edad= ? ,"
                    + "nombre_img= ? ,"
                    + "imagen = ? ,"
                    + "nombre_s = ? "
                    + "WHERE cod_expediente= ? ");

            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, sexo);
            pstm.setInt(4, Integer.parseInt(edad));
            pstm.setString(5, nombre_img);
            pstm.setBinaryStream(6, fis, (int) file.length());
            pstm.setString(7, nombre_s);
            pstm.setString(8, cod);

            pstm.execute();
            pstm.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarfoto4(String cod, String nombre, String apellido, String sexo, String edad, String nombre_s) {
        try {
            PreparedStatement pstm = getC().prepareStatement("UPDATE estudiantes "
                    + "set nombre = ? ,"
                    + "apellido = ? ,"
                    + "sexo = ? ,"
                    + "edad= ? ,"
                    + "nombre_s = ? "
                    + "WHERE cod_expediente= ? ");

            pstm.setString(1, nombre);
            pstm.setString(2, apellido);
            pstm.setString(3, sexo);
            pstm.setInt(4, Integer.parseInt(edad));
            pstm.setString(5, nombre_s);
            pstm.setString(6, cod);

            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean modificarfototodas(String nombre_img, String imagen, String cate, String niv) {
        FileInputStream fis = null;
        try {
            File file = new File(imagen);
            fis = new FileInputStream(file);
            PreparedStatement pstm = getC().prepareStatement("UPDATE preguntas "
                    + "set nombre_img = ? ,"
                    + "imagen = ? "
                    + "WHERE cat= ? "
                    + " AND niv= ? ");

            pstm.setString(1, nombre_img);
            pstm.setBinaryStream(2, fis, (int) file.length());
            pstm.setString(3, cate);
            pstm.setInt(4, Integer.parseInt(niv));

            pstm.execute();
            pstm.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, e);
            System.out.println(e.getMessage());

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public String fecha_vencimiento() {
        String fecha = null;
        try {
            Calendar Calendario = Calendar.getInstance();
            int dias_vencimiento = this.SelectUltimo2("tbl_conf_clave").getInt("nu_dias");
            Calendario.add(Calendar.DATE, dias_vencimiento);
            //Fecha del Sistema********************************************************
            String anno = Integer.toString(Calendario.get(Calendar.YEAR));
            String mes = Integer.toString(Calendario.get(Calendar.MONTH) + 1);
            String dia = Integer.toString(Calendario.get(Calendar.DATE));
            fecha = dia + "/" + mes + "/" + anno;
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fecha;

    }

    /**
     * @return the c
     */
    public Connection getC() {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(Connection c) {
        this.c = c;
    }

    /**
     * @return the q
     */
    public Statement getQ() {
        return q;
    }

    /**
     * @param q the q to set
     */
    public void setQ(Statement q) {
        this.q = q;
    }

    /**
     * @return the driver
     */
    public String getDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * @return the nombre_db
     */
    public String getNombre_db() {
        return nombre_db;
    }

    /**
     * @param nombre_db the nombre_db to set
     */
    public void setNombre_db(String nombre_db) {
        this.nombre_db = nombre_db;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * @return the jdbc
     */
    public String getJdbc() {
        return jdbc;
    }

    /**
     * @param jdbc the jdbc to set
     */
    public void setJdbc(String jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nombre_usuario
     */
    /**
     * @return the h2
     */
    public String getH2() {
        return h2;
    }

    /**
     * @param h2 the h2 to set
     */
    public void setH2(String h2) {
        this.h2 = h2;
    }

    /**
     * @return the calendario
     */
    public Calendar getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(Calendar calendario) {
        this.calendario = calendario;
    }

    /**
     * @return the lineas
     */
    public String[] getLineas() {
        return lineas;
    }

    /**
     * @param lineas the lineas to set
     */
    public void setLineas(String[] lineas) {
        this.lineas = lineas;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    /**
     * @param rol the rol to set
     */
    public void setRol(String rol) {
        this.rol = rol;
    }

    /**
     * @return the seguridad
     */
    public String getSeguridad() {
        return seguridad;
    }

    /**
     * @param seguridad the seguridad to set
     */
    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    /**
     * @return the respuesta
     */
    public String getRespuesta() {
        return respuesta;
    }

    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
