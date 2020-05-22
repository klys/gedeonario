/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ahorcado;

import base_dato.*;
import gedeon1.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 *
 * @author usuario
 */
public class etapa1 extends javax.swing.JFrame {

    /**
     * Creates new form etapa1
     */
    public etapa1(int dificultad, int nivel) {
        initComponents();
        this.dificultad = dificultad;
        this.nivel = nivel;
        actualizarListas();
        this.llenarLabelera();
        this.seleccionarPalabra();
        this.decidirIntentosPosibles();
        this.llenarImagenes();
        this.EmpezarJuego();
    }
    
    private int dificultad;
    
    public void actualizarListas() {
        try{
            validaciones v = new validaciones();
            ManejadorSQL db = new ManejadorSQL("postgres", Gedeon1.ipcondb, "freddy", "1234", "org.postgresql.Driver", "5432", "jdbc:postgresql", "2", v);
            
            //Statement stmt = Gedeon1.db.getConexion().createStatement();
            ResultSet rs = db.Select("tbl_palabra", "tx_palabra, int_puntos", "int_dificultad = "+dificultad);//stmt.executeQuery("SELECT tx_palabra, int_puntos FROM tbl_palabra WHERE int_dificultad = "+dificultad);
            
        
        //rs = Gedeon1.db.Select2("tbl_palabra", "tx_palabra, int_puntos", "int_dificultad = "+dificultad);
        
        palabras.clear();
        puntos.clear();
        
            while(rs.next()) {
                palabras.add(rs.getString("tx_palabra"));
                puntos.add(rs.getInt("int_puntos"));
            }
        
        db.close();
         } catch (SQLException ex) {
                Logger.getLogger(etapa1.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    ArrayList<String> palabras = new ArrayList<String>();
    ArrayList<Integer> puntos = new ArrayList<Integer>();
    
    ArrayList<Character> letras = new ArrayList<Character>();
    
    ArrayList<org.edisoncor.gui.label.LabelHeader> letras_label = new ArrayList<org.edisoncor.gui.label.LabelHeader>();
    
    ArrayList<ImageIcon> imagenes = new ArrayList<ImageIcon>();
    
    public void llenarImagenes() {
        imagenes.clear();
        for(int i = 0; i <=10; i ++) {
            try{
                ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/ahorcadito/"+i+".png"));
                imagenes.add(img);
            } catch (NullPointerException e) {
                System.out.println("/imagenes.ahorcadito/"+i+".png not found. Error(404).");
            }
        }
    }
    
    public void seleccionarPalabra() {
        Double indexPalabraSeleccionada = (Math.random()*palabras.size());
        System.out.println("id de palabra seleccionada: "+indexPalabraSeleccionada);
        String palabra = palabras.get(indexPalabraSeleccionada.intValue());
        System.out.println("Palabra seleccionada: "+palabra);
        
        letras.clear();
        for(int i = 0; i < palabra.length(); i++) {
            letras.add( palabra.charAt(i));
        }
        
    }
    
    private int imgMomento = 0;
    private int punto = 0;
    private int nivel = 0;
    private int intentosPosibles = 0;
    private int intentosRealizados = 0;
    
    public void EmpezarJuego() {
        Character primeraLetra = letras.get(0);
        Character ultimaLetra = letras.get(letras.size()-1);
        letras_label.get(0).setText(primeraLetra.toString());
        letras_label.get(letras.size()-1).setText(ultimaLetra.toString());
        for(int i = 1; i <= letras.size()-2; i++) {
            letras_label.get(i).setText("_");
        }
        for(int j = letras.size(); j < letras_label.size(); j ++) {
            letras_label.get(j).setVisible(false);
        }
    }
    
    public void decidirIntentosPosibles() {
//        Double d;
//        switch(this.dificultad) {
//            case 1: // facil
//                this.intentosPosibles = (letras.size()-2)*2;
//                break;
//            case 2: // normal
//                d = Math.floor((letras.size()-2)*1.5);
//                this.intentosPosibles = d.intValue();
//                break;
//            case 3: // dificil
//                d = Math.floor((letras.size()-2)*1.25);
//                this.intentosPosibles = d.intValue();
//                break;
//        }
        this.intentosPosibles = 10;
        this.labelIntentosPosibles.setText(Integer.toString(this.intentosPosibles));
    }
    
    public boolean intentoFallido() {
        if (this.intentosPosibles-2 < this.intentosRealizados) {
            this.Perder();
            return false;
        } 
        
        this.intentosRealizados++;
        this.labelIntentosRealizados.setText(Integer.toString(this.intentosRealizados));
        return true;
    }
    
    public boolean isPrimeraLetra(Character letra) {
        if (letra == letras.get(0)) return true;
        return false;
    }
    
    public boolean isUltimaLetra(Character letra) {
        if (letra == letras.get(letras.size()-1)) return true;
        return false;
    }
    
    public boolean isRepetida(Character letra) {
        //if (this.isPrimetraLetra(letra) && this.isUltimaLetra(letra)) return false;
        
        for(int i = 1; i < letras.size()-2;i++) {
            if (letra.toString().equals(letras_label.get(i).getText())) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isAllFilled() {
        for(int i = 1; i < letras_label.size()-2;i++) {
            if (letras_label.get(i).getText().equals("_")) {
                return false;
            }
        }
        return true;
    }
    
    public boolean encontrarLetra(Character letra) {
        //if (this.isPrimeraLetra(letra)) return false;
        //if (this.isUltimaLetra(letra)) return false;
        for(int i = 1; i <= letras.size()-2; i++) {
            System.out.println(letras.get(i));
            if (letras.get(i).equals(letra)) {
                return true;
            }
        }
        return false;
    }
    
    public void IntentarLetra(Character letra) {
        int ocurrencias = 0;
        ArrayList<String> encontrados = new ArrayList<String>();
        if(this.encontrarLetra(letra) && !this.isRepetida(letra)) {
            // SI SE CONSIGUE
            ocurrencias = 0;
            for(int i = 1; i < letras.size()-1; i++) {
                if (letras.get(i) == letra) {
                    ocurrencias++;
                    encontrados.add(Integer.toString(i));
                }
            }
            if (ocurrencias != 0) {
                for(int j = 0; j < encontrados.size(); j++) {
                    letras_label.get(Integer.parseInt(encontrados.get(j))).setText(Character.toString(letras.get(Integer.parseInt(encontrados.get(j)))));
                }
                puntear(10);
                if (this.isAllFilled()) {
                    this.Ganar();
                }
            }
        } else {
            // SI SE EQUIVOCA
            this.intentoFallido();
            puntear(-5);
        }
    }
    
    public void puntear(int puntos) {
        this.punto += puntos;
        labelPuntos.setText(Integer.toString(this.punto));
        if (puntos < 0) {
            ahorcar();
        }
    }
    
    public void ahorcar() {
        int x = (this.intentosRealizados*100)/this.intentosPosibles;
        Double d = Math.floor((10*x)/100);
        this.imgMomento = d.intValue();
        this.labelImagen.setIcon(imagenes.get(this.imgMomento));
    }
    
    public void llenarLabelera() {
        letras_label.clear();
        
        letras_label.add(l1);
        letras_label.add(l2);
        letras_label.add(l3);
        letras_label.add(l4);
        letras_label.add(l5);
        letras_label.add(l6);
        letras_label.add(l7);
        letras_label.add(l8);
        letras_label.add(l9);
        letras_label.add(l10);
        letras_label.add(l11);
        letras_label.add(l12);
        letras_label.add(l13);
        letras_label.add(l14);
        letras_label.add(l15);
        letras_label.add(l16);
        letras_label.add(l17);
        letras_label.add(l18);
        letras_label.add(l19);
        letras_label.add(l20);
        letras_label.add(l21);
        letras_label.add(l22);
        letras_label.add(l23);
        letras_label.add(l24);
        letras_label.add(l25);
        letras_label.add(l26);
        letras_label.add(l27);

        
    }
    
    public void Ganar() {
        // Provisional
        //JOptionPane.showMessageDialog(null, "Ganaste!!!!  ERES UN CAMPEON!!! WIIII ! LO HICISTE!!! :D !!!");
        scoreboard_decision ven = new scoreboard_decision("ganar", "pala___tras", this.punto, this.nivel);
        ven.setLocationRelativeTo(null);
        ven.setVisible(true);
        this.dispose();
    }
    
    public void Perder() {
        //JOptionPane.showMessageDialog(null, "Perdiste!!! Vuelve a intentarlo!!! :( ");
        scoreboard_decision ven = new scoreboard_decision("perder", "pala___tras",this.punto, this.nivel);
        ven.setLocationRelativeTo(null);
        ven.setVisible(true);
        this.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        compositeEffect1 = new com.sun.animation.effects.CompositeEffect();
        compositeEffect2 = new com.sun.animation.effects.CompositeEffect();
        compositeEffect3 = new com.sun.animation.effects.CompositeEffect();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        jPanel1 = new javax.swing.JPanel();
        labelHeader1 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader5 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader7 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader24 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader10 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader14 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader23 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader28 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader25 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader29 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader20 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader27 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader15 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader19 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader16 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader12 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader9 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader4 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader6 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader18 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader21 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader13 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader22 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader3 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader17 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader8 = new org.edisoncor.gui.label.LabelHeader();
        labelHeader26 = new org.edisoncor.gui.label.LabelHeader();
        jPanel2 = new javax.swing.JPanel();
        l27 = new org.edisoncor.gui.label.LabelHeader();
        l26 = new org.edisoncor.gui.label.LabelHeader();
        l25 = new org.edisoncor.gui.label.LabelHeader();
        l24 = new org.edisoncor.gui.label.LabelHeader();
        l23 = new org.edisoncor.gui.label.LabelHeader();
        l22 = new org.edisoncor.gui.label.LabelHeader();
        l21 = new org.edisoncor.gui.label.LabelHeader();
        l20 = new org.edisoncor.gui.label.LabelHeader();
        l19 = new org.edisoncor.gui.label.LabelHeader();
        l18 = new org.edisoncor.gui.label.LabelHeader();
        l17 = new org.edisoncor.gui.label.LabelHeader();
        l16 = new org.edisoncor.gui.label.LabelHeader();
        l15 = new org.edisoncor.gui.label.LabelHeader();
        l14 = new org.edisoncor.gui.label.LabelHeader();
        l13 = new org.edisoncor.gui.label.LabelHeader();
        l12 = new org.edisoncor.gui.label.LabelHeader();
        l11 = new org.edisoncor.gui.label.LabelHeader();
        l10 = new org.edisoncor.gui.label.LabelHeader();
        l9 = new org.edisoncor.gui.label.LabelHeader();
        l8 = new org.edisoncor.gui.label.LabelHeader();
        l7 = new org.edisoncor.gui.label.LabelHeader();
        l6 = new org.edisoncor.gui.label.LabelHeader();
        l5 = new org.edisoncor.gui.label.LabelHeader();
        l4 = new org.edisoncor.gui.label.LabelHeader();
        l3 = new org.edisoncor.gui.label.LabelHeader();
        l2 = new org.edisoncor.gui.label.LabelHeader();
        l1 = new org.edisoncor.gui.label.LabelHeader();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelPuntos = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelIntentosRealizados = new javax.swing.JLabel();
        labelPuntos2 = new javax.swing.JLabel();
        labelIntentosPosibles = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        labelImagen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelNivel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pala___tras");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gedeon.jpg"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        labelHeader1.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader1.setText("A");
        labelHeader1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader1MouseClicked(evt);
            }
        });

        labelHeader5.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader5.setText("B");
        labelHeader5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader5MouseClicked(evt);
            }
        });

        labelHeader7.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader7.setText("C");
        labelHeader7.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader7MouseClicked(evt);
            }
        });

        labelHeader24.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader24.setText("D");
        labelHeader24.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader24MouseClicked(evt);
            }
        });

        labelHeader10.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader10.setText("E");
        labelHeader10.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader10MouseClicked(evt);
            }
        });

        labelHeader14.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader14.setText("F");
        labelHeader14.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader14MouseClicked(evt);
            }
        });

        labelHeader23.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader23.setText("G");
        labelHeader23.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader23MouseClicked(evt);
            }
        });

        labelHeader28.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader28.setText("H");
        labelHeader28.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader28MouseClicked(evt);
            }
        });

        labelHeader25.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader25.setText("I");
        labelHeader25.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader25MouseClicked(evt);
            }
        });

        labelHeader29.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader29.setText("J");
        labelHeader29.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader29MouseClicked(evt);
            }
        });

        labelHeader20.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader20.setText("R");
        labelHeader20.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader20MouseClicked(evt);
            }
        });

        labelHeader27.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader27.setText("Q");
        labelHeader27.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader27MouseClicked(evt);
            }
        });

        labelHeader15.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader15.setText("P");
        labelHeader15.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader15MouseClicked(evt);
            }
        });

        labelHeader19.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader19.setText("O");
        labelHeader19.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader19MouseClicked(evt);
            }
        });

        labelHeader16.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader16.setText("Ñ");
        labelHeader16.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader16MouseClicked(evt);
            }
        });

        labelHeader12.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader12.setText("N");
        labelHeader12.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader12MouseClicked(evt);
            }
        });

        labelHeader9.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader9.setText("M");
        labelHeader9.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader9MouseClicked(evt);
            }
        });

        labelHeader4.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader4.setText("L");
        labelHeader4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader4MouseClicked(evt);
            }
        });

        labelHeader6.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader6.setText("K");
        labelHeader6.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader6MouseClicked(evt);
            }
        });

        labelHeader18.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader18.setText("S");
        labelHeader18.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader18MouseClicked(evt);
            }
        });

        labelHeader21.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader21.setText("T");
        labelHeader21.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader21MouseClicked(evt);
            }
        });

        labelHeader13.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader13.setText("U");
        labelHeader13.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader13MouseClicked(evt);
            }
        });

        labelHeader22.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader22.setText("V");
        labelHeader22.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader22MouseClicked(evt);
            }
        });

        labelHeader3.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader3.setText("W");
        labelHeader3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader3MouseClicked(evt);
            }
        });

        labelHeader17.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader17.setText("X");
        labelHeader17.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader17MouseClicked(evt);
            }
        });

        labelHeader8.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader8.setText("Y");
        labelHeader8.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader8MouseClicked(evt);
            }
        });

        labelHeader26.setBackground(new java.awt.Color(255, 255, 51));
        labelHeader26.setText("Z");
        labelHeader26.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        labelHeader26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelHeader26MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(labelHeader18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader21, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader22, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader26, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(labelHeader6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelHeader4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(labelHeader9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader20, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader24, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelHeader25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelHeader29, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader24, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader28, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader25, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader29, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeader6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader27, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelHeader21, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader22, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelHeader18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        l27.setBackground(new java.awt.Color(255, 255, 51));
        l27.setText("27");
        l27.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l27MouseClicked(evt);
            }
        });

        l26.setBackground(new java.awt.Color(255, 255, 51));
        l26.setText("A");
        l26.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l26MouseClicked(evt);
            }
        });

        l25.setBackground(new java.awt.Color(255, 255, 51));
        l25.setText("A");
        l25.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l25MouseClicked(evt);
            }
        });

        l24.setBackground(new java.awt.Color(255, 255, 51));
        l24.setText("A");
        l24.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l24MouseClicked(evt);
            }
        });

        l23.setBackground(new java.awt.Color(255, 255, 51));
        l23.setText("A");
        l23.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l23MouseClicked(evt);
            }
        });

        l22.setBackground(new java.awt.Color(255, 255, 51));
        l22.setText("A");
        l22.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l22MouseClicked(evt);
            }
        });

        l21.setBackground(new java.awt.Color(255, 255, 51));
        l21.setText("A");
        l21.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l21MouseClicked(evt);
            }
        });

        l20.setBackground(new java.awt.Color(255, 255, 51));
        l20.setText("20");
        l20.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l20MouseClicked(evt);
            }
        });

        l19.setBackground(new java.awt.Color(255, 255, 51));
        l19.setText("A");
        l19.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l19MouseClicked(evt);
            }
        });

        l18.setBackground(new java.awt.Color(255, 255, 51));
        l18.setText("A");
        l18.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l18MouseClicked(evt);
            }
        });

        l17.setBackground(new java.awt.Color(255, 255, 51));
        l17.setText("A");
        l17.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l17MouseClicked(evt);
            }
        });

        l16.setBackground(new java.awt.Color(255, 255, 51));
        l16.setText("A");
        l16.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l16MouseClicked(evt);
            }
        });

        l15.setBackground(new java.awt.Color(255, 255, 51));
        l15.setText("A");
        l15.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l15MouseClicked(evt);
            }
        });

        l14.setBackground(new java.awt.Color(255, 255, 51));
        l14.setText("14");
        l14.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l14MouseClicked(evt);
            }
        });

        l13.setBackground(new java.awt.Color(255, 255, 51));
        l13.setText("A");
        l13.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l13MouseClicked(evt);
            }
        });

        l12.setBackground(new java.awt.Color(255, 255, 51));
        l12.setText("A");
        l12.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l12MouseClicked(evt);
            }
        });

        l11.setBackground(new java.awt.Color(255, 255, 51));
        l11.setText("A");
        l11.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l11MouseClicked(evt);
            }
        });

        l10.setBackground(new java.awt.Color(255, 255, 51));
        l10.setText("J");
        l10.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l10MouseClicked(evt);
            }
        });

        l9.setBackground(new java.awt.Color(255, 255, 51));
        l9.setText("9");
        l9.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l9MouseClicked(evt);
            }
        });

        l8.setBackground(new java.awt.Color(255, 255, 51));
        l8.setText("H");
        l8.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l8MouseClicked(evt);
            }
        });

        l7.setBackground(new java.awt.Color(255, 255, 51));
        l7.setText("G");
        l7.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l7MouseClicked(evt);
            }
        });

        l6.setBackground(new java.awt.Color(255, 255, 51));
        l6.setText("F");
        l6.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l6MouseClicked(evt);
            }
        });

        l5.setBackground(new java.awt.Color(255, 255, 51));
        l5.setText("5");
        l5.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l5MouseClicked(evt);
            }
        });

        l4.setBackground(new java.awt.Color(255, 255, 51));
        l4.setText("D");
        l4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l4MouseClicked(evt);
            }
        });

        l3.setBackground(new java.awt.Color(255, 255, 51));
        l3.setText("C");
        l3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l3MouseClicked(evt);
            }
        });

        l2.setBackground(new java.awt.Color(255, 255, 51));
        l2.setText("B");
        l2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l2MouseClicked(evt);
            }
        });

        l1.setBackground(new java.awt.Color(255, 255, 51));
        l1.setText("1");
        l1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        l1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(l15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l16, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l17, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l18, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l19, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l20, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l21, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l22, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l23, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(l24, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l26, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l27, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l13, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l15, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l16, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l17, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l18, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l19, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l20, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l21, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l22, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l24, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l25, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l26, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(l27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Puntaje");

        labelPuntos.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelPuntos.setForeground(new java.awt.Color(0, 255, 0));
        labelPuntos.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Intentos");

        labelIntentosRealizados.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelIntentosRealizados.setForeground(new java.awt.Color(255, 0, 0));
        labelIntentosRealizados.setText("0");

        labelPuntos2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelPuntos2.setForeground(new java.awt.Color(255, 0, 0));
        labelPuntos2.setText("/");

        labelIntentosPosibles.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        labelIntentosPosibles.setForeground(new java.awt.Color(255, 0, 0));
        labelIntentosPosibles.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelPuntos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelIntentosRealizados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelPuntos2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelIntentosPosibles))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelPuntos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(labelIntentosRealizados)
                    .addComponent(labelPuntos2)
                    .addComponent(labelIntentosPosibles))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("RENDIRSE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        labelImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ahorcadito/0.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nivel");

        labelNivel.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelNivel.setForeground(new java.awt.Color(255, 255, 255));
        labelNivel.setText("0");

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelImage1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelImage1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel1))
                                    .addGroup(panelImage1Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(labelNivel))))))
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(jButton1)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNivel)))
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelImage1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void l4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l4MouseClicked

    private void l3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l3MouseClicked

    private void l2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l2MouseClicked

    private void l1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l1MouseClicked

    private void labelHeader1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader1MouseClicked
        // Letra A
        this.IntentarLetra('a');
    }//GEN-LAST:event_labelHeader1MouseClicked

    private void l5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l5MouseClicked

    private void l6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l6MouseClicked

    private void l7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l7MouseClicked

    private void l8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l8MouseClicked

    private void l9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l9MouseClicked

    private void l10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l10MouseClicked

    private void l11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l11MouseClicked

    private void l12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l12MouseClicked

    private void l13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l13MouseClicked

    private void l14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l14MouseClicked

    private void l27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l27MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l27MouseClicked

    private void l21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l21MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l21MouseClicked

    private void l26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l26MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l26MouseClicked

    private void l25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l25MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l25MouseClicked

    private void l24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l24MouseClicked

    private void l23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l23MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l23MouseClicked

    private void l22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l22MouseClicked

    private void l20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l20MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l20MouseClicked

    private void l19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l19MouseClicked

    private void l18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l18MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l18MouseClicked

    private void l17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l17MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l17MouseClicked

    private void l16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l16MouseClicked

    private void l15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_l15MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void labelHeader5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader5MouseClicked
        // Letra B
        this.IntentarLetra('b');
    }//GEN-LAST:event_labelHeader5MouseClicked

    private void labelHeader7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader7MouseClicked
        // Letra c
        this.IntentarLetra('c');
    }//GEN-LAST:event_labelHeader7MouseClicked

    private void labelHeader24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader24MouseClicked
        // letra D
        this.IntentarLetra('d');
    }//GEN-LAST:event_labelHeader24MouseClicked

    private void labelHeader10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader10MouseClicked
        // letra E
        this.IntentarLetra('e');
    }//GEN-LAST:event_labelHeader10MouseClicked

    private void labelHeader14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader14MouseClicked
        // letra F
        this.IntentarLetra('f');
    }//GEN-LAST:event_labelHeader14MouseClicked

    private void labelHeader23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader23MouseClicked
        //Letra G
        this.IntentarLetra('g');
    }//GEN-LAST:event_labelHeader23MouseClicked

    private void labelHeader28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader28MouseClicked
        // Letra H
        this.IntentarLetra('h');
    }//GEN-LAST:event_labelHeader28MouseClicked

    private void labelHeader25MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader25MouseClicked
        // Letra I
        this.IntentarLetra('i');
    }//GEN-LAST:event_labelHeader25MouseClicked

    private void labelHeader29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader29MouseClicked
        // Letra J
        this.IntentarLetra('j');
    }//GEN-LAST:event_labelHeader29MouseClicked

    private void labelHeader6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader6MouseClicked
        //Letra k
        this.IntentarLetra('k');
    }//GEN-LAST:event_labelHeader6MouseClicked

    private void labelHeader4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader4MouseClicked
        // Letra L
        this.IntentarLetra('l');
    }//GEN-LAST:event_labelHeader4MouseClicked

    private void labelHeader9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader9MouseClicked
        // Letra M
        this.IntentarLetra('m');
    }//GEN-LAST:event_labelHeader9MouseClicked

    private void labelHeader12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader12MouseClicked
        // Letra N
        this.IntentarLetra('n');
    }//GEN-LAST:event_labelHeader12MouseClicked

    private void labelHeader16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader16MouseClicked
        // Letra Ñ
        this.IntentarLetra('ñ');
    }//GEN-LAST:event_labelHeader16MouseClicked

    private void labelHeader19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader19MouseClicked
        //Letra O
        this.IntentarLetra('o');
    }//GEN-LAST:event_labelHeader19MouseClicked

    private void labelHeader15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader15MouseClicked
        // Letra P
        this.IntentarLetra('p');
    }//GEN-LAST:event_labelHeader15MouseClicked

    private void labelHeader27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader27MouseClicked
        // Letra Q
        this.IntentarLetra('q');
    }//GEN-LAST:event_labelHeader27MouseClicked

    private void labelHeader20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader20MouseClicked
        // Letra R
        this.IntentarLetra('r');
    }//GEN-LAST:event_labelHeader20MouseClicked

    private void labelHeader18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader18MouseClicked
        // Letra S
        this.IntentarLetra('s');
    }//GEN-LAST:event_labelHeader18MouseClicked

    private void labelHeader21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader21MouseClicked
        // Letra T
        this.IntentarLetra('t');
    }//GEN-LAST:event_labelHeader21MouseClicked

    private void labelHeader13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader13MouseClicked
        // Letra U
        this.IntentarLetra('u');
    }//GEN-LAST:event_labelHeader13MouseClicked

    private void labelHeader22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader22MouseClicked
        // Letra V
        this.IntentarLetra('v');
    }//GEN-LAST:event_labelHeader22MouseClicked

    private void labelHeader3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader3MouseClicked
       // Letra W
        this.IntentarLetra('w');
    }//GEN-LAST:event_labelHeader3MouseClicked

    private void labelHeader17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader17MouseClicked
       // Letra X
        this.IntentarLetra('x');
    }//GEN-LAST:event_labelHeader17MouseClicked

    private void labelHeader8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader8MouseClicked
       // Letra Y
        this.IntentarLetra('y');
    }//GEN-LAST:event_labelHeader8MouseClicked

    private void labelHeader26MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelHeader26MouseClicked
       // Letra Z
        this.IntentarLetra('z');
    }//GEN-LAST:event_labelHeader26MouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.sun.animation.effects.CompositeEffect compositeEffect1;
    private com.sun.animation.effects.CompositeEffect compositeEffect2;
    private com.sun.animation.effects.CompositeEffect compositeEffect3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private org.edisoncor.gui.label.LabelHeader l1;
    private org.edisoncor.gui.label.LabelHeader l10;
    private org.edisoncor.gui.label.LabelHeader l11;
    private org.edisoncor.gui.label.LabelHeader l12;
    private org.edisoncor.gui.label.LabelHeader l13;
    private org.edisoncor.gui.label.LabelHeader l14;
    private org.edisoncor.gui.label.LabelHeader l15;
    private org.edisoncor.gui.label.LabelHeader l16;
    private org.edisoncor.gui.label.LabelHeader l17;
    private org.edisoncor.gui.label.LabelHeader l18;
    private org.edisoncor.gui.label.LabelHeader l19;
    private org.edisoncor.gui.label.LabelHeader l2;
    private org.edisoncor.gui.label.LabelHeader l20;
    private org.edisoncor.gui.label.LabelHeader l21;
    private org.edisoncor.gui.label.LabelHeader l22;
    private org.edisoncor.gui.label.LabelHeader l23;
    private org.edisoncor.gui.label.LabelHeader l24;
    private org.edisoncor.gui.label.LabelHeader l25;
    private org.edisoncor.gui.label.LabelHeader l26;
    private org.edisoncor.gui.label.LabelHeader l27;
    private org.edisoncor.gui.label.LabelHeader l3;
    private org.edisoncor.gui.label.LabelHeader l4;
    private org.edisoncor.gui.label.LabelHeader l5;
    private org.edisoncor.gui.label.LabelHeader l6;
    private org.edisoncor.gui.label.LabelHeader l7;
    private org.edisoncor.gui.label.LabelHeader l8;
    private org.edisoncor.gui.label.LabelHeader l9;
    private org.edisoncor.gui.label.LabelHeader labelHeader1;
    private org.edisoncor.gui.label.LabelHeader labelHeader10;
    private org.edisoncor.gui.label.LabelHeader labelHeader12;
    private org.edisoncor.gui.label.LabelHeader labelHeader13;
    private org.edisoncor.gui.label.LabelHeader labelHeader14;
    private org.edisoncor.gui.label.LabelHeader labelHeader15;
    private org.edisoncor.gui.label.LabelHeader labelHeader16;
    private org.edisoncor.gui.label.LabelHeader labelHeader17;
    private org.edisoncor.gui.label.LabelHeader labelHeader18;
    private org.edisoncor.gui.label.LabelHeader labelHeader19;
    private org.edisoncor.gui.label.LabelHeader labelHeader20;
    private org.edisoncor.gui.label.LabelHeader labelHeader21;
    private org.edisoncor.gui.label.LabelHeader labelHeader22;
    private org.edisoncor.gui.label.LabelHeader labelHeader23;
    private org.edisoncor.gui.label.LabelHeader labelHeader24;
    private org.edisoncor.gui.label.LabelHeader labelHeader25;
    private org.edisoncor.gui.label.LabelHeader labelHeader26;
    private org.edisoncor.gui.label.LabelHeader labelHeader27;
    private org.edisoncor.gui.label.LabelHeader labelHeader28;
    private org.edisoncor.gui.label.LabelHeader labelHeader29;
    private org.edisoncor.gui.label.LabelHeader labelHeader3;
    private org.edisoncor.gui.label.LabelHeader labelHeader4;
    private org.edisoncor.gui.label.LabelHeader labelHeader5;
    private org.edisoncor.gui.label.LabelHeader labelHeader6;
    private org.edisoncor.gui.label.LabelHeader labelHeader7;
    private org.edisoncor.gui.label.LabelHeader labelHeader8;
    private org.edisoncor.gui.label.LabelHeader labelHeader9;
    private javax.swing.JLabel labelImagen;
    private javax.swing.JLabel labelIntentosPosibles;
    private javax.swing.JLabel labelIntentosRealizados;
    private javax.swing.JLabel labelNivel;
    private javax.swing.JLabel labelPuntos;
    private javax.swing.JLabel labelPuntos2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}
