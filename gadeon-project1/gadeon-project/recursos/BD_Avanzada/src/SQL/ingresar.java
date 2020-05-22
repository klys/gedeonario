
package SQL;

import java.sql.Statement;


public class ingresar {
     public static boolean registrarcliente(String cedula,String nombre, String apellido,String telefono, String direccion)throws java.sql.SQLException, ClassNotFoundException
    {
        boolean done=false;
        String datos="insert into public.beneficiados (codigo,cedula,nombre,apellido,telefono,edad,diagnostico) values ('"+cedula+"','"+nombre+"','"+apellido+"','"+direccion+"',"+telefono+")";
        Conexion c=new Conexion();

        Statement sentencia= c.Conexion_usuarios().createStatement();
        sentencia.executeUpdate(datos);
        sentencia.close();
        c.Conexion_usuarios().createStatement().close();
        done=true;
        return done;
    }
     public static boolean registraralimento(String coda,String noma, String cana)throws java.sql.SQLException, ClassNotFoundException
    {
        boolean done=false;
        String datos="insert into public.inventario (cod_alim,nombre,cantidad) values ('"+coda+"','"+noma+"','"+cana+"')";
        Conexion c=new Conexion();

        Statement sentencia= c.Conexion_usuarios().createStatement();
        sentencia.executeUpdate(datos);
        sentencia.close();
        c.Conexion_usuarios().createStatement().close();
        done=true;
        return done;
    }
      public static boolean registrarmenu(String codm,String nomm, String fm)throws java.sql.SQLException, ClassNotFoundException
    {
        boolean done=false;
        String datos="insert into public.menu (id_menu,fecha,comida) values ('"+codm+"','"+fm+"','"+nomm+"')";
        Conexion c=new Conexion();

        Statement sentencia= c.Conexion_usuarios().createStatement();
        sentencia.executeUpdate(datos);
        sentencia.close();
        c.Conexion_usuarios().createStatement().close();
        done=true;
        return done;
    }
      public static boolean registrarbeneficiados(String cod_b,String nom_b, String ape_b, String edad_b, String diag_b)throws java.sql.SQLException, ClassNotFoundException
    {
        boolean done=false;
        String datos="insert into public.beneficiados (codigo,nombre,apellido,edad,diagnostico) values ('"+cod_b+"','"+nom_b+"','"+ape_b+"','"+edad_b+"','"+diag_b+"')";
        Conexion c=new Conexion();

        Statement sentencia= c.Conexion_usuarios().createStatement();
        sentencia.executeUpdate(datos);
        sentencia.close();
        c.Conexion_usuarios().createStatement().close();
        done=true;
        return done;
    }
}
