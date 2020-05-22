/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bd_avanzada;

import java.awt.Color;

/**
 *
 * @author Usuario
 */
public class cargar_conf{
    private static Color col_f;
    private static Color col_t;
    private static Color col_l;
    
public  Color Colorf(){
return col_f;
}
public  Color Colort(){
return col_t;
}
public  Color Colorl(){
return col_l;
}
public void setColorf(Color acolor_f){
    col_f= acolor_f;
}
public void setColort(Color acolor_t){
    col_t= acolor_t;
}
public void setColorl(Color acolor_l){
    col_l= acolor_l;
}
}