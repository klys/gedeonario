/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base_dato;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.KeyEvent;
import javax.swing.InputMap;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import sun.security.util.Length;

/**
 *
 * @author
 */
public class validaciones {

    public void permitir_solo_letra(java.awt.event.KeyEvent evt) { //Se valida solo letras
        char car;
        car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE) && (car != 'ñ' && car != 'Ñ') && (car != 'á' && car != 'é' && car != 'í' && car != 'ó' && car != 'ú' && car != 'Á' && car != 'É' && car != 'Í' && car != 'Ó' && car != 'Ú')) {
            evt.consume();
        }
    }

    public void permitir_letras_numeros(java.awt.event.KeyEvent evt) { //Se valida letras/numeros
        char car;
        car = evt.getKeyChar();
        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car < '0' || car > '9') && (car != (char) KeyEvent.VK_SPACE)) {

            evt.consume();
        }
    }

    public void permitir_espacio(java.awt.event.KeyEvent evt) { //Se valida el espacio entre caracteres
        char car;
        car = evt.getKeyChar();
        if ((car == (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }

    public void permitir_solo_numero(java.awt.event.KeyEvent evt) { //Se valida solo numeros
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }

    }

    public void permitir_decimales(java.awt.event.KeyEvent e, JTextField j) {
        char c = e.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)
                && (c != '.')) {
            e.consume();
        }
        if (c == ',' && j.getText().contains(",")) {
            e.consume();
        }
    }

    public void cambio_de_color_rojo(JTextField x) {
        x.setBackground(Color.red);

    }

    public void cambio_de_color_verde(JTextField x) {
        x.setBackground(Color.green);
    }

    public void copiar_pegar(JTextField x) {                       //Se valida copiar/pegar de caracteres
        InputMap map4 = x.getInputMap(x.WHEN_FOCUSED);
        map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");

    }

    public void copiar_pegar2(JTextArea x) {                       //Se valida copiar/pegar de caracteres
        InputMap map4 = x.getInputMap(x.WHEN_FOCUSED);
        map4.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Event.CTRL_MASK), "null");

    }

    public void longitud(JTextField x, int l, java.awt.event.KeyEvent evt) { //Se determina la longitud

        if (x.getText().length() >= l) {
            evt.consume();
        }
    }

    public void longitud2(JTextArea x, int l, java.awt.event.KeyEvent evt) { //Se determina la longitud

        if (x.getText().length() >= l) {
            evt.consume();
        }
    }
    
    public int contar_mayusculas(String clave){
        
        Character evaluar;
          
        int mayus=0;
        //char evaluar=clave.charAt(index);
        for(int i=0;i<clave.length();i++){
            evaluar=clave.charAt(i);
            if(Character.isUpperCase(evaluar)){
                mayus++;
            }
        }
        return mayus;
        
    }


public int contar_minusculas(String clave){
        
        Character evaluar;
          
        int minus=0;
        //char evaluar=clave.charAt(index);
        for(int i=0;i<clave.length();i++){
            evaluar=clave.charAt(i);
            if(Character.isLowerCase(evaluar)){
                minus++;
            }
        }
        return minus;
    }

}
