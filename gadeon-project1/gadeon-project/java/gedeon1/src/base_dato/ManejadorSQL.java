/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package base_dato;


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
import java.net.InetAddress;
import java.net.NetworkInterface;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ManejadorSQL {

    private Connection c; // conexion
    private Statement q; // query
    private String driver, nombre_db, ip, port, user, pass, jdbc;
    private String clave, usuario, tipo, id, nombre_usuario;
    String h2;
    public int id_rol;
    public String cedulai;
    public validaciones val;
    public String id_usuario;
    Calendar calendario = new GregorianCalendar();
    
    

    public ManejadorSQL(String db_name, String ip, String user, String pass, String driver2, String port2, String jdbc2, String h3, validaciones val2) {

        this.ip = ip;
        this.nombre_db = db_name;
        this.user = user;
        this.pass = pass;
        this.driver = driver2;
        this.port = port2;
        this.jdbc = jdbc2;
        this.h2 = h3;
        this.val = val2;
        
        Conectar();
    }

    public void cerrar_aplicacion() {
//        String separador = System.getProperty("file.separator");
//        File ruta = new File("C:" + separador + "dbxconnections2.ini");
//        ruta.delete();
        NetworkInterface a;
        String macpc = null;
        try {
            a = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
            byte[] mac = a.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }

            macpc = sb.toString();
            this.Update("tblxian_ipapp", "in_act=0", "mac='" + macpc + "'");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
            e.printStackTrace();
        }
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

    /**
     * *
     * 34 Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     * 35
     *
     * @param message texto a encriptar 36
     * @param algorithm algoritmo de encriptacion, puede ser: MD2, MD5, SHA-1,
     * SHA-256, SHA-384, SHA-512 37
     * @return mensaje encriptado
     *
     */
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

    public Statement realizarstatment() {
        try {
            q = c.createStatement();

        } catch (SQLException ex) {
            try {
                this.Log("Error Critico", ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return (q);
    }

    public Connection devolverconexion() {

        return (this.getConexion());
    }

    private boolean Conectar() {
        try {
            Class.forName(this.driver);
            if (h2.equals("1")) {
                this.c = DriverManager.getConnection(this.jdbc + "://" + this.ip + ";databaseName=" + this.nombre_db + ";user=" + this.user + ";password=" + this.pass + ";");
            } else {
                this.c = DriverManager.getConnection(this.jdbc + "://" + this.ip + ":" + this.port + "/" + this.nombre_db, this.user, this.pass);
            }

            if (this.c != null) {
                this.q = c.createStatement();
            }

            return true;
        } catch (ClassNotFoundException ex) {
            try {
                this.Log("Error Critico", ex.getMessage(), "BD");

            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
//System.out.println(ex);
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("[ClassNotFoundException]Ocurrio un error al conectar con la base de datos.");
            return false;
            //Logger.getLogger(ManejadorSQL.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            try {
                //System.out.println(ex);
                //JOptionPane.showMessageDialog(null, ex.getMessage());
                this.Log("Error Critico", ex.getMessage(), "BD");

//                verificar_estado_conexion();

            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
//System.out.println("[SQLException]Ocurrio un error al conectar con la base de datos.");
            return false;

        }
    }

    public void Desconectar() {
        try {
            c.close();
        } catch (SQLException ex) {
            try {
                this.Log("Error Critico", ex.getMessage(), "BD");

            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean Update(String tabla, String set, String codition) {
        try {
            this.q.executeUpdate("UPDATE " + tabla + " SET " + set + " WHERE " + codition + ";");
            try {
                this.Log(this.getUsuario(), "UPDATE " + tabla + " SET " + set + " WHERE " + codition + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            try {
                //JOptionPane.showMessageDialog(null, ex.getMessage());
                this.Log(this.getUsuario(), "UPDATE " + tabla + " SET " + set + " WHERE " + codition + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Uso: INSERT INTO table_name VALUES (value1,value2,value3,...)
     */
    public boolean Insert(String tabla, String values) {
        try {
            this.q.executeUpdate("INSERT INTO " + tabla + " VALUES(" + values + ");");
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + " VALUES(" + values + "); " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + " VALUES(" + values + "); " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean Insert(String tabla, String campos, String values) {
        try {
            this.q.executeUpdate("INSERT INTO " + tabla + "(" + campos + ") VALUES(" + values + ");");
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + "(" + campos + ") VALUES(" + values + "); " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "INSERT INTO " + tabla + "(" + campos + ") VALUES(" + values + "); " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
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
            this.q.execute("DELETE FROM " + tabla + " WHERE " + codition);
            try {
                this.Log(this.getUsuario(), "DELETE FROM " + tabla + " WHERE " + codition + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "DELETE FROM " + tabla + " WHERE " + codition + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
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
            set = this.q.executeQuery("SELECT " + columns + " FROM " + tabla + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return set;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
              //  verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet Select(String tabla, String columns, String Where) {
        ResultSet set;
        try {
            set = this.q.executeQuery("SELECT " + columns + " FROM " + tabla + " WHERE " + Where + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            return set;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
//JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ResultSet Select2(String tabla, String columns, String Where) {
        ResultSet set, set2 = null;
        try {
            set = this.q.executeQuery("SELECT " + columns + " FROM " + tabla + " WHERE " + Where + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {
                set2 = set;
                break;
            }
            return set2;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
// JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean Select3(String tabla, String columns, String Where) {
        ResultSet set, set2 = null;
        try {
            set = this.q.executeQuery("SELECT " + columns + " FROM " + tabla + " WHERE " + Where + ";");
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {
                set2 = set;
                return true;
            }

        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT " + columns + " FROM " + tabla + " WHERE " + Where + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
//                verificar_estado_conexion();
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

    public ResultSet SelectUltimo(String tabla, String condicion) {
        ResultSet set, set2 = null;
        try {
            set = this.q.executeQuery("SELECT * FROM " + tabla + " WHERE " + condicion + "");
            try {
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + condicion + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
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
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + condicion + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean SelectUltimo2(String tabla, String condicion) {
        ResultSet set, set2 = null;
        try {
            set = this.q.executeQuery("SELECT * FROM " + tabla + " WHERE " + condicion + "");
            try {
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + condicion + "; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {
                set2 = set;
                if (set2.isLast()) {
                    return true;
                }
            }

        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT * FROM " + tabla + " WHERE " + condicion + "; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
///                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean Admin_Existe(String adminNombre, String adminClave) {
        try {
            // "select * from public.usuario where nombre_usuario='"+n+"' and clave='"+c+"'";
            //System.out.println(this.c+"...conexion vacia");
            ResultSet t = Select("tblsit_usr", "id_rol_usr, in_status_usr", "tx_login = '" + adminNombre + "' and tx_clave = '" + adminClave + "'");
            if (t.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), ex.getMessage(), "BD");
//                verificar_estado_conexion();

            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            //JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);

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
        //        verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
//JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);

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
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
//JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);

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
            try {
                this.Log(this.getUsuario(), ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
// JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);

            return false;
        }
    }

    public Connection getConexion() {
        return this.c;
    }

    public Statement getStatement() {
        return this.q;
    }

    //FUNCIONES PARA MANEJAR IMAGENES PARA LA TABLA REPUESTOS
    public boolean guardarfoto(String codigo, String descrip, String contado, String credito, String cantidad, String categoria, String name, String ruta) {
        FileInputStream fis = null;
        try {
            File file = new File(ruta);
            fis = new FileInputStream(file);
            PreparedStatement pstm = c.prepareStatement("INSERT into "
                    + " tblxian_repuesto(cod_repuesto,tx_descripcion,nu_precio_contado,nu_precio_credito,nu_cant_disponible"
                    + ",id_categoria,nb_imagen, img_imagen) " + " VALUES(?,?,?,?,?,?,?,?)");
            pstm.setString(1, codigo);
            pstm.setString(2, descrip);
            pstm.setDouble(3, Double.parseDouble(contado));
            pstm.setDouble(4, Double.parseDouble(credito));
            pstm.setInt(5, Integer.parseInt(cantidad));
            pstm.setInt(6, Integer.parseInt(categoria));
            pstm.setString(7, name);
            pstm.setBinaryStream(8, fis, (int) file.length());
            pstm.execute();
            try {
                this.Log(this.getUsuario(), "INSERT into "
                        + " tblxian_repuesto(cod_repuesto,tx_descripcion,nu_precio_contado,nu_precio_credito,nu_cant_disponible"
                        + ",id_categoria,nb_imagen, img_imagen) " + " VALUES(?,?,?,?,?,?,?,?); " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            pstm.close();
            return true;
        } catch (FileNotFoundException | SQLException e) {
            try {
                this.Log(this.getUsuario(), "INSERT into "
                        + " tblxian_repuesto(cod_repuesto,tx_descripcion,nu_precio_contado,nu_precio_credito,nu_cant_disponible"
                        + ",id_categoria,nb_imagen, img_imagen) " + " VALUES(?,?,?,?,?,?,?,?); " + calendario.getTime() + "ERROR= " + e.getMessage(), "BD");
                //System.out.println(e.getMessage());
                //System.out.println(e.getMessage());
//                verificar_estado_conexion();
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                try {
                    this.Log(this.getUsuario(), e.getMessage(), "BD");

                } catch (IOException ex) {
                    Logger.getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
//JOptionPane.showMessageDialog(null, e.getMessage());
                //System.out.println(e.getMessage());
            }
        }
        return false;
    }
    
    public boolean guardarfotocrucifijo(String nombre, String ruta, String cantidad, String puntos) {
        FileInputStream fis = null;
        try {
            File file = new File(ruta);
            fis = new FileInputStream(file);
            PreparedStatement pstm = c.prepareStatement("INSERT into"
                    + " tbl_crucifijo(nb_crucifijo,img_crucifijo,cant_resp_correcta,puntos) " + " VALUES(?,?,?,?)");
            pstm.setString(1, nombre);
            pstm.setBinaryStream(2, fis, (int) file.length());
            pstm.setInt(3, Integer.parseInt(cantidad));
            pstm.setInt(4, Integer.parseInt(puntos));
           
            
            pstm.execute();
            pstm.close();
            return true;
        } catch (FileNotFoundException | SQLException e) {
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                try {
                    this.Log(this.getUsuario(), e.getMessage(), "BD");

                } catch (IOException ex) {
                    Logger.getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
//JOptionPane.showMessageDialog(null, e.getMessage());
                //System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public boolean modificarfoto(String codigo, String descrip, String contado, String credito, String cantidad, String categoria, String name, String ruta) {
        FileInputStream fis = null;
        try {
            File file = new File(ruta);
            fis = new FileInputStream(file);
            // PreparedStatement pstm = c.prepareStatement("UPDATE" +
            //          " public.imagenes SET tx_descr_repuesto='"+descrip+"',nu_precio_contado="+contado+",nu_precio_credito="
            //     + ""+credito+",nu_cant_disponible="+cantidad+",id_categoria="+categoria+",nb_imagen='"+name+"', img_imagen WHERE cod_repuesto='"+codigo+"'");
            PreparedStatement pstm = c.prepareStatement("UPDATE tblxian_repuesto "
                    + "set tx_descripcion = ? ,"
                    + "nu_precio_contado = ? ,"
                    + "nu_precio_credito = ? ,"
                    + "nu_cant_disponible = ? ,"
                    + "id_categoria = ? ,"
                    + "nb_imagen = ? ,"
                    + "img_imagen = ? "
                    + "WHERE cod_repuesto = ? ");

            //  pstm.setString(1, name);
            //  pstm.setBinaryStream(2, fis,(int) file.length());
            // pstm.setString(1, codigo);
            pstm.setString(1, descrip);
            pstm.setDouble(2, Double.parseDouble(contado));
            pstm.setDouble(3, Double.parseDouble(credito));
            pstm.setInt(4, Integer.parseInt(cantidad));
            pstm.setInt(5, Integer.parseInt(categoria));
            pstm.setString(6, name);
            pstm.setBinaryStream(7, fis, (int) file.length());
            pstm.setString(8, codigo);
            pstm.execute();
            try {
                this.Log(this.getUsuario(), "UPDATE tblxian_repuesto "
                        + "set tx_descripcion = ? ,"
                        + "nu_precio_contado = ? ,"
                        + "nu_precio_credito = ? ,"
                        + "nu_cant_disponible = ? ,"
                        + "id_categoria = ? ,"
                        + "nb_imagen = ? ,"
                        + "img_imagen = ? "
                        + "WHERE cod_repuesto = ? ; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            pstm.close();
            return true;
        } catch (FileNotFoundException e) {
            try {
                this.Log(this.getUsuario(), "UPDATE tblxian_repuesto "
                        + "set tx_descripcion = ? ,"
                        + "nu_precio_contado = ? ,"
                        + "nu_precio_credito = ? ,"
                        + "nu_cant_disponible = ? ,"
                        + "id_categoria = ? ,"
                        + "nb_imagen = ? ,"
                        + "img_imagen = ? "
                        + "WHERE cod_repuesto = ? ; " + calendario.getTime() + "ERROR= " + e.getMessage(), "BD");
                //JOptionPane.showMessageDialog(null, e.getMessage());
                //System.out.println(e.getMessage());

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            try {
                //JOptionPane.showMessageDialog(null, e.getMessage());
                this.Log(this.getUsuario(), "UPDATE tblxian_repuesto "
                        + "set tx_descripcion = ? ,"
                        + "nu_precio_contado = ? ,"
                        + "nu_precio_credito = ? ,"
                        + "nu_cant_disponible = ? ,"
                        + "id_categoria = ? ,"
                        + "nb_imagen = ? ,"
                        + "img_imagen = ? "
                        + "WHERE cod_repuesto = ? ; " + calendario.getTime() + "ERROR= " + e.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, e);
            // System.out.println(e.getMessage());

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                try {
                    this.Log(this.getUsuario(), "UPDATE tblxian_repuesto "
                            + "set tx_descripcion = ? ,"
                            + "nu_precio_contado = ? ,"
                            + "nu_precio_credito = ? ,"
                            + "nu_cant_disponible = ? ,"
                            + "id_categoria = ? ,"
                            + "nb_imagen = ? ,"
                            + "img_imagen = ? "
                            + "WHERE cod_repuesto = ? ; " + calendario.getTime() + "ERROR= " + e.getMessage(), "BD");
                    //System.out.println(e.getMessage());
                    //System.out.println(e.getMessage());

                } catch (IOException ex) {
                    Logger.getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    public boolean modificarfoto2(String codigo, String descrip, String contado, String credito, String cantidad, String categoria) {
        try {
            // PreparedStatement pstm = c.prepareStatement("UPDATE" +
            //          " public.imagenes SET tx_descr_repuesto='"+descrip+"',nu_precio_contado="+contado+",nu_precio_credito="
            //     + ""+credito+",nu_cant_disponible="+cantidad+",id_categoria="+categoria+",nb_imagen='"+name+"', img_imagen WHERE cod_repuesto='"+codigo+"'");
            PreparedStatement pstm = c.prepareStatement("UPDATE tblxian_repuesto "
                    + "set tx_descripcion = ? ,"
                    + "nu_precio_contado = ? ,"
                    + "nu_precio_credito = ? ,"
                    + "nu_cant_disponible = ? ,"
                    + "id_categoria = ? "
                    + "WHERE cod_repuesto = ? ");

            //  pstm.setString(1, name);
            //  pstm.setBinaryStream(2, fis,(int) file.length());
            // pstm.setString(1, codigo);
            pstm.setString(1, descrip);
            pstm.setDouble(2, Double.parseDouble(contado));
            pstm.setDouble(3, Double.parseDouble(credito));
            pstm.setInt(4, Integer.parseInt(cantidad));
            pstm.setInt(5, Integer.parseInt(categoria));
            pstm.setString(6, codigo);
            pstm.execute();
            try {
                this.Log(this.getUsuario(), "UPDATE tblxian_repuesto "
                        + "set tx_descripcion = ? ,"
                        + "nu_precio_contado = ? ,"
                        + "nu_precio_credito = ? ,"
                        + "nu_cant_disponible = ? ,"
                        + "id_categoria = ? "
                        + "WHERE cod_repuesto = ?  ; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            pstm.close();
            return true;

        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "UPDATE tblxian_repuesto "
                        + "set tx_descripcion = ? ,"
                        + "nu_precio_contado = ? ,"
                        + "nu_precio_credito = ? ,"
                        + "nu_cant_disponible = ? ,"
                        + "id_categoria = ? "
                        + "WHERE cod_repuesto = ?  ; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
//JOptionPane.showMessageDialog(null, ex);
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /* Metodo que convierte una cadena de bytes en una imagen JPG
     * input:
     * bytes: array que contiene los binarios de la imagen
     * Output: la foto JPG en formato IMAGE
     */
    private Image ConvertirImagen(byte[] bytes, String tipo) throws IOException {
        //String tipo="gif";
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName(tipo);
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }

    /* Metodo que extrae los registros de la tabla IMAGEN de la base de datos
     * crea instancia nueva de la clase imagen.java y añade los datos
     * agrega estos datos a un DefaultListModel
     * output:
     * dml: Es un DefaultListModel que contiene instancia de la clase imagen.java
     */
    public imagen cargarFoto(String id_foto) {
        imagen img = new imagen();
        try {

            q = c.createStatement();
            ResultSet set = q.executeQuery("SELECT cod_repuesto,nb_imagen,img_imagen FROM tblxian_repuesto  WHERE cod_repuesto='" + id_foto + "'");
            try {
                this.Log(this.getUsuario(), "SELECT cod_repuesto,nb_imagen,img_imagen FROM tblxian_repuesto  WHERE cod_repuesto='" + id_foto + "' ; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {

                img.setId(set.getString("cod_repuesto"));
                img.setName(set.getString("nb_imagen"));
                String t = img.getName();//obtengo el nombre de la imagen
                int indice = 0;
                int longitud = t.length();//obtenemos la longitud del nombre
                int indicepunto = t.indexOf('.', indice);//obtengo el lugar donde esta el primer punto en ese nombre
                while (indicepunto >= 0) {//verificamos si existen mas puntos

                    indice = indicepunto + 1;//aumentamos el indice a la posicion del primer punto
                    indicepunto = t.indexOf('.', indice);//volvemos a buscar un punto a partir del nuevo indice
                }
                t = t.substring(indice, longitud);
                try { //antes de agregar el campo imagen, realiza la conversion de bytes a JPG
                    img.setArchivo(ConvertirImagen(set.getBytes("img_imagen"), t));
                } catch (IOException ex) {
                    try {
                        this.Log(this.getUsuario(), "SELECT cod_repuesto,nb_imagen,img_imagen FROM tblxian_repuesto  WHERE cod_repuesto='" + id_foto + "' ; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
                        //System.err.println(ex.getMessage());
                        //System.err.println(ex.getMessage());

                    } catch (IOException ex1) {
                        Logger.getLogger(ManejadorSQL.class
                                .getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }

        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT cod_repuesto,nb_imagen,img_imagen FROM tblxian_repuesto  WHERE cod_repuesto='" + id_foto + "' ; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
            // JOptionPane.showMessageDialog(null, ex);
        }
        return img;
    }

    public imagen cargarFotocrucifijo(String id_foto) {
        imagen img = new imagen();
        try {

            q = c.createStatement();
            ResultSet set = q.executeQuery("SELECT * FROM tbl_crucifijo  WHERE id_crucifijo=" + id_foto + "");
            try {
                this.Log(this.getUsuario(), "SELECT * FROM tbl_crucifijo  WHERE id_crucifijo=" + id_foto + " ; " + calendario.getTime(), "BD");

            } catch (IOException ex) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            while (set.next()) {

                img.setId(set.getString("id_crucifijo"));
                img.setName(set.getString("nb_crucifijo"));
                String t = img.getName();//obtengo el nombre de la imagen
                int indice = 0;
                int longitud = t.length();//obtenemos la longitud del nombre
                int indicepunto = t.indexOf('.', indice);//obtengo el lugar donde esta el primer punto en ese nombre
                while (indicepunto >= 0) {//verificamos si existen mas puntos

                    indice = indicepunto + 1;//aumentamos el indice a la posicion del primer punto
                    indicepunto = t.indexOf('.', indice);//volvemos a buscar un punto a partir del nuevo indice
                }
                t = t.substring(indice, longitud);
                try { //antes de agregar el campo imagen, realiza la conversion de bytes a JPG
                    img.setArchivo(ConvertirImagen(set.getBytes("img_crucifijo"), t));
                } catch (IOException ex) {
                    try {
                        this.Log(this.getUsuario(), "SELECT * FROM tbl_crucifijo  WHERE id_crucifijo='" + id_foto + "' ; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
                        //System.err.println(ex.getMessage());
                        //System.err.println(ex.getMessage());

                    } catch (IOException ex1) {
                        Logger.getLogger(ManejadorSQL.class
                                .getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }

        } catch (SQLException ex) {
            try {
                this.Log(this.getUsuario(), "SELECT cod_repuesto,nb_imagen,img_imagen FROM tblxian_repuesto  WHERE cod_repuesto='" + id_foto + "' ; " + calendario.getTime() + "ERROR= " + ex.getMessage(), "BD");
//                verificar_estado_conexion();
            } catch (IOException ex1) {
                Logger.getLogger(ManejadorSQL.class
                        .getName()).log(Level.SEVERE, null, ex1);
            }
            Logger
                    .getLogger(ManejadorSQL.class
                            .getName()).log(Level.SEVERE, null, ex);
            // JOptionPane.showMessageDialog(null, ex);
        }
        return img;
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
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    /**
     * @param nombre_usuario the nombre_usuario to set
     */
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
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
        }
        FileWriter fichero3 = new FileWriter(nbFile, true);

        pw = new PrintWriter(fichero3);
        String linea = "<LOG TP='" + tipo + "'><![CDATA[" + sistema + " -> " + Mensaje + "]]></LOG>";
        pw.append(linea);
        pw.flush();
        pw.close();
        fichero3.close();

    }

//    public void verificar_estado_conexion() {
//
//     //   Icon icono = new ImageIcon(ClassLoader.getSystemResource("imagenesavatar/noconexion2.png"));
//
//        if (JOptionPane.showConfirmDialog(null, "Error al Conectarse a la Base de Datos ¿Desea intentar conectarse nuevamente\n (Si el problema persiste, Favor comuníquese con su Administrador)",
//                "Error de Conexión", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icono) == JOptionPane.YES_OPTION) {
//
//            if (this.Conectar()) {
//                JOptionPane.showMessageDialog(null, "Conexión a Base de Datos exitosa", "Conexión Restablecida", JOptionPane.INFORMATION_MESSAGE, icono);
//            }
//        }
//
//        //JOptionPane.showMessageDialog(null, "No hay comunicación con la Base de Datos", "Error de Conexión", JOptionPane.ERROR_MESSAGE, icono);
//        //this.cerrar_aplicacion();
//        // this.Desconectar();
//        // ImportadoraXian ia=new ImportadoraXian();
//        //System.exit(0);
//    }

  

    /**
     * @return the cvt
     */
 
}
