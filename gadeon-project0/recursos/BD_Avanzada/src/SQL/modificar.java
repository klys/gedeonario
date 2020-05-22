
package SQL;

import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class modificar {
    
     public static void modificarinventario(String codigo,String nombre,String cantidad)throws java.sql.SQLException
    {
        try {
           // boolean listo = false;
            String sentenciamodificar = "update public.inventario set cod_alim='" + codigo + "',nombre='" +nombre + "',cantidad='" + cantidad  +"' WHERE cod_alim='" + codigo + "'";
            Conexion c=new Conexion();

        Statement sentencia4= c.Conexion_usuarios().createStatement();sentencia4.executeUpdate(sentenciamodificar);
            sentencia4.close();
           c.Conexion_usuarios().createStatement().close();
           // listo = true;
           // return listo;
            JOptionPane.showMessageDialog(null, "Actualización Realizada","Operación Exitosa",JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modificar.class.getName()).log(Level.SEVERE, null, ex);
            //return(false);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     public static void modificarbeneficiado(String codig,String nombre,String ape,String edd,String diag)throws java.sql.SQLException
    {
        try {
           // boolean listo = false;
            String sentenciamodificar = "update public.beneficiados set codigo='" + codig + "',nombre='" +nombre + "',apellido='" + ape  +"',edad='"+ edd +"',diagnostico='"+diag+"' WHERE codigo='" + codig+ "'";
            Conexion c=new Conexion();

        Statement sentencia4= c.Conexion_usuarios().createStatement();sentencia4.executeUpdate(sentenciamodificar);
            sentencia4.close();
           c.Conexion_usuarios().createStatement().close();
           // listo = true;
           // return listo;
            JOptionPane.showMessageDialog(null, "Actualización Realizada","Operación Exitosa",JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modificar.class.getName()).log(Level.SEVERE, null, ex);
            //return(false);
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}
