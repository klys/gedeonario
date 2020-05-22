
package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
     public Connection Conexion_usuarios() throws ClassNotFoundException, SQLException{
        Connection conn=null;
        
        String user="postgres";
        String pass="1234";//"esperanza";
        String driver="org.postgresql.Driver";
        String connectString="jdbc:postgresql://localhost:5432/DB_avanzada";

        try{
            Class.forName(driver);
            conn=DriverManager.getConnection(connectString, user, pass);
            JOptionPane.showMessageDialog(null, "CONEXION A BASE DE DATOS EXITOSA...");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }
        return(conn);
    }
}
