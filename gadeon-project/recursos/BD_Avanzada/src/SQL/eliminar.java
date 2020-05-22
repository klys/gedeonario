/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SQL;

import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author BERLIN COELLO
 */
public class eliminar {
  public static void borrar(String tabla,String codigo)throws java.sql.SQLException, ClassNotFoundException{
        //boolean hecho=false;
        String datos="DELETE from public."+tabla+" WHERE cod_alim='"+codigo+"'";
        Conexion c=new Conexion();

        Statement sentencia= c.Conexion_usuarios().createStatement();
        sentencia.executeUpdate(datos);
        sentencia.close();
        c.Conexion_usuarios().createStatement().close();
         JOptionPane.showMessageDialog(null, "Borrado Realizado","Operación Exitosa",JOptionPane.INFORMATION_MESSAGE);
    } 
   public static void borrarmenu(String tabla,String codigo)throws java.sql.SQLException, ClassNotFoundException{
        //boolean hecho=false;
        String datos="DELETE from public."+tabla+" WHERE id_menu='"+codigo+"'";
        Conexion c=new Conexion();

        Statement sentencia= c.Conexion_usuarios().createStatement();
        sentencia.executeUpdate(datos);
        sentencia.close();
        c.Conexion_usuarios().createStatement().close();
         JOptionPane.showMessageDialog(null, "Borrado Realizado","Operación Exitosa",JOptionPane.INFORMATION_MESSAGE);
    } 
   public static void borrarbeneficiados(String tabla,String codigb)throws java.sql.SQLException, ClassNotFoundException{
        //boolean hecho=false;
        String datos="DELETE from public."+tabla+" WHERE codigo='"+codigb+"'";
        Conexion c=new Conexion();

        Statement sentencia= c.Conexion_usuarios().createStatement();
        sentencia.executeUpdate(datos);
        sentencia.close();
        c.Conexion_usuarios().createStatement().close();
         JOptionPane.showMessageDialog(null, "Borrado Realizado","Operación Exitosa",JOptionPane.INFORMATION_MESSAGE);
    } 
    
}
