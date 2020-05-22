/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gedeon1;

import base_dato.*;
import gedeon1.Gedeon1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author usuario
 */
public class Gedeon1 {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        iniciar_sistema();
        //i=new inicio();
        //i.setLocationRelativeTo(null);
        //i.setVisible(true);
    }
    public static inicio i;
    public static String icono = "/imagenes/Imagen2.png";
    public static String ipcondb = "localhost";
    public static ManejadorSQL db;

    public static void iniciar_sistema() {
        Timer timer = new Timer (1000, new ActionListener () 
        { 
            inicio ven = new inicio();
            boolean hecho = true;
            int decontador = 30;
            boolean Onbloqueo = false;
            boolean bloqueo = false;
            public void actionPerformed(ActionEvent e)
            { 
                
                if (hecho) {
                    System.out.println("hola");
                    hecho = false;
                    ven.setLocationRelativeTo(null);
                    ven.setVisible(true);
                    //ven.bloquear_campos();
                }
                if (ven.int_bloqueo<ven.equivoco) {
                    System.out.println("bloquear");
                    bloqueo = true;
                    ven.equivoco=0;
                }
                if (bloqueo) {
                    bloqueo = false;
                    ven.bloquear_campos();
                    Onbloqueo = true;
                }
                if (Onbloqueo) {
                    ven.mostrar_mensaje("Debe esperar ("+decontador+") segundos.");
                    decontador--;
                    if (decontador == 0) {
                        ven.desbloquear();
                        ven.mostrar_mensaje("");
                        Onbloqueo = false;
                        decontador = 30;
                       
                        //((Timer)e.getSource()).stop();
                    }
                }
            } 

        }); 
        timer.start();
    }
    static void inicio_bloqueado() {
        Timer timer = new Timer (1000, new ActionListener () 
        { 
            inicio ven = new inicio();
            boolean hecho = true;
            int decontador = 30;
            boolean Onbloqueo = false;
            boolean bloqueo = false;
            public void actionPerformed(ActionEvent e)
            { 
                if (hecho) {
                    hecho = false;
                    ven.setVisible(true);
                    //ven.bloquear_campos();
                }
                if (bloqueo) {
                    
                }
                if (Onbloqueo) {
                    ven.mostrar_mensaje("Debe esperar ("+decontador+") segundos.");
                    decontador--;
                    if (decontador == 0) {
                        ven.desbloquear();
                        ven.mostrar_mensaje("");
                        ((Timer)e.getSource()).stop();
                    }
                }
            } 

        }); 
        timer.start();
    }
    public validaciones v = new validaciones();
    public static int id_usuario = -1;

    public Gedeon1() {
        this.db = new ManejadorSQL("postgres", Gedeon1.ipcondb, "freddy", "1234", "org.postgresql.Driver", "5432", "jdbc:postgresql", "2", v);
    }
    
    
}
