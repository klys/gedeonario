
package SQL;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class buscar {
    public static ResultSet buscarusuario(String clave2) throws java.sql.SQLException, ClassNotFoundException
    {

        String Consulta="SELECT id_t FROM  public.usuario WHERE clave='"+clave2+"'";

        ResultSet Resultado1 = null;
        Conexion c=new Conexion();

        Statement sentencia3= c.Conexion_usuarios().createStatement();
        ResultSet resultado2 = sentencia3.executeQuery(Consulta);

        while (resultado2.next())
        {
           Resultado1 = resultado2;
           break;
        }
        if(Resultado1==null){
            JOptionPane.showMessageDialog(null, "Usuario y/o Contraseña Inválida","Acceso Denegado",JOptionPane.ERROR_MESSAGE);
        }

         return Resultado1;
    }
    public static ResultSet buscaralimento(String cod) throws java.sql.SQLException, ClassNotFoundException
    {

        String Consulta="SELECT * FROM  public.inventario WHERE cod_alim='"+cod+"'";

        ResultSet Resultado1 = null;
        Conexion c=new Conexion();

        Statement sentencia3= c.Conexion_usuarios().createStatement();
        ResultSet resultado2 = sentencia3.executeQuery(Consulta);

        while (resultado2.next())
        {
           Resultado1 = resultado2;
           break;
        }
        if(Resultado1==null){
            JOptionPane.showMessageDialog(null, "Alimento no Encontrado","Acceso Denegado",JOptionPane.ERROR_MESSAGE);
        }

         return Resultado1;
    }
    public static ResultSet buscarmenu(String cod) throws java.sql.SQLException, ClassNotFoundException
    {

        String Consulta="SELECT * FROM  public.menu WHERE id_menu='"+cod+"'";

        ResultSet Resultado1 = null;
        Conexion c=new Conexion();

        Statement sentencia3= c.Conexion_usuarios().createStatement();
        ResultSet resultado2 = sentencia3.executeQuery(Consulta);

        while (resultado2.next())
        {
           Resultado1 = resultado2;
           break;
        }
        if(Resultado1==null){
            JOptionPane.showMessageDialog(null, "Menu no Encontrado","Acceso Denegado",JOptionPane.ERROR_MESSAGE);
        }

         return Resultado1;
    }
public static ResultSet buscarbeneficiados(String codi) throws java.sql.SQLException, ClassNotFoundException
    {

        String Consulta="SELECT * FROM  public.beneficiados WHERE codigo='"+codi+"'";

        ResultSet Resultado1 = null;
        Conexion c=new Conexion();

        Statement sentencia3= c.Conexion_usuarios().createStatement();
        ResultSet resultado2 = sentencia3.executeQuery(Consulta);

        while (resultado2.next())
        {
           Resultado1 = resultado2;
           break;
        }
        if(Resultado1==null){
            JOptionPane.showMessageDialog(null, "Beneficiado no Encontrado","Acceso Denegado",JOptionPane.ERROR_MESSAGE);
        }

         return Resultado1;
    }

}
