/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jugador;

import base_dato.*;
import gedeon1.Gedeon1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class jugador {
    public static int getPuntos(int id_jugador) {
        validaciones v = new validaciones();
        ManejadorSQL db = new ManejadorSQL("postgres", Gedeon1.ipcondb, "freddy", "1234", "org.postgresql.Driver", "5432", "jdbc:postgresql", "2", v);
        int puntos = 0;
        ResultSet rs = db.Select("tbl_partida", "puntaje", "id_usuario = "+id_jugador);
        
        try {
            if (rs.next()) {
                puntos = rs.getInt("puntaje");
            }
        } catch (SQLException ex) {
            Logger.getLogger(jugador.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.close();
        return puntos;
    }
    
    public static void setPuntos(int puntos, int id_jugador) {
        validaciones v = new validaciones();
        ManejadorSQL db = new ManejadorSQL("postgres", Gedeon1.ipcondb, "freddy", "1234", "org.postgresql.Driver", "5432", "jdbc:postgresql", "2", v);
        
        puntos += getPuntos(id_jugador);
        
        db.Update("tbl_partida", "puntaje = "+puntos, "id_usuario = "+id_jugador); 
        db.close();
    }
    
    
    
}
