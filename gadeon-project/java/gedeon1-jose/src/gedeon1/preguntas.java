/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gedeon1;

import base_dato.ManejadorSQL;
import base_dato.validaciones;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class preguntas extends javax.swing.JFrame {

    ManejadorSQL m;
validaciones v= new validaciones();
int inicio=0;
    /**
     * Creates new form preguntas
     */
    public preguntas(ManejadorSQL m2) {
        m = m2;
        initComponents();
         ImageIcon img = new ImageIcon(getClass().getResource(Gedeon1.icono));
        this.setIconImage(img.getImage());
   
        
       jcbetapa.setEnabled(true);
    btnguardar.setVisible(true);
    jtfingresarfop.setVisible(false);
    jcblibro.setVisible(false);
    jlbingresar.setVisible(true);
                jlbopciona.setVisible(false);
                jlbrespuestacorrec.setVisible(false);
                jlbrespueta.setVisible(false);
                jtfopcionb.setVisible(false);
                jtfopcionc.setVisible(false);
                jtfopciona.setVisible(false);
                jtfrespuestacorrecta.setVisible(false);
                jtfingresarfop.setVisible(false);
                jlbllenarc.setVisible(false);
                jlbllenarc.setVisible(false);
                jlbllenarc.setText("Ingrese las cuatro opciones y seleccione la correcta");
                jlbllenarc.setForeground(Color.WHITE);
                jlbmensajep.setVisible(false);
                jlbopciond.setVisible(false);
                jtfopciond.setVisible(false);
                jlbopcionc.setVisible(false);
                jlbopcionb.setVisible(false);
                btnguardar1.setVisible(false);
                btnguardar.setVisible(false);
                jlbrespuestacorrec.setVisible(false);
                btn_aceptar.setVisible(true);
                labelPreguntaPuntos.setVisible(false);
                preguntaPuntos.setVisible(false);
        etapas();
        llenar_libros();
       jcbetapa1.setEnabled(true);
    btnguardar1.setVisible(true);
  
    jcblibro1.setVisible(false);
    jlbingresar1.setVisible(true);
                jlbopciona1.setVisible(false);
                jlbrespuestacorrec1.setVisible(false);
                jlbrespueta1.setVisible(false);
                jtfopcionb1.setVisible(false);
                jtfopcionc1.setVisible(false);
                jtfopciona1.setVisible(false);
                jtfrespuestacorrecta1.setVisible(false);
                jtfingresarfop.setVisible(false);
                jlbllenarc1.setVisible(false);
                jlbllenarc1.setVisible(false);
                jlbllenarc1.setText("Ingrese las cuatro opciones y seleccione la correcta");
                jlbllenarc1.setForeground(Color.WHITE);
               
                jlbopciond1.setVisible(false);
                jtfopciond1.setVisible(false);
                jlbopcionc1.setVisible(false);
                jlbopcionb1.setVisible(false);
                btnguardar1.setVisible(false);
                btnguardar1.setVisible(false);
                jlbrespuestacorrec1.setVisible(false);
                btn_aceptar1.setVisible(true);
                jlbingresar1.setVisible(false);
                jcbpreguntas.setVisible(false);
                 btnmostrar.setVisible(false);
                 jlthabilitar.setVisible(false);
                 jlbmensajetx.setVisible(false);
                  jtfingresarfop1.setVisible(false);
                   jlbingresar2.setVisible(false);
                   btnguardarcam.setVisible(false);
        etapasm();
        inicio=1;
        llenar_libro();
       
         
    }
    public void llenar_preguntas(String id_etapa) {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_preguntas WHERE id_etapa=" + id_etapa+ "");
            jcbpreguntas.removeAllItems();
    
           
            if(id_etapa.equals("3")){
            modeloCombo.addElement("Seleccione una pregunta");
            }
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("tx_preguntas"));
            }
            rs.close();
            jcbpreguntas.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void llenar_libro(){
        try{
     DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_libro");
            jcblibro1.removeAllItems();
            modeloCombo.addElement("libro del que extrajo la pregunta");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_libro"));
            }
            rs.close();
            jcblibro1.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void etapasm(){
    try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_etapas");
            jcbetapa1.removeAllItems();
            modeloCombo.addElement("Seleccione una etapa");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre_etapa"));
            }
            rs.close();
            jcbetapa1.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
        
    
            

    public void etapas() {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_etapas");
            jcbetapa.removeAllItems();
            modeloCombo.addElement("Seleccione una etapa");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nombre_etapa"));
            }
            rs.close();
            jcbetapa.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llenar_dificultad(){
      try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_dificultad");
            jcblibro.removeAllItems();
            modeloCombo.addElement("Elija la dificultad de la palabra");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("tx_nombre"));
            }
            rs.close();
            jcblibro.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     
    }
     public void llenar_palabras(){
      try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_palabra");
            jcbpreguntas.removeAllItems();
            modeloCombo.addElement("Seleccionar palabra");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("tx_palabra"));
            }
            rs.close();
            jcbpreguntas.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    public void llenar_libros() {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_libro");
            jcblibro.removeAllItems();
            modeloCombo.addElement("libro del que extrajo la pregunta");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_libro"));
            }
            rs.close();
            jcblibro.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tabbedPaneHeader1 = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        jPanel3 = new javax.swing.JPanel();
        jcbetapa = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jlbingresar = new javax.swing.JLabel();
        jtfingresarfop = new javax.swing.JTextField();
        jlbrespueta = new javax.swing.JLabel();
        jlbopciona = new javax.swing.JLabel();
        jtfopcionb = new javax.swing.JTextField();
        jtfopcionc = new javax.swing.JTextField();
        jtfopciona = new javax.swing.JTextField();
        jtfrespuestacorrecta = new javax.swing.JTextField();
        btn_aceptar = new javax.swing.JButton();
        jlbmensajep = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnguardar1 = new javax.swing.JButton();
        jcblibro = new javax.swing.JComboBox();
        jtfopciond = new javax.swing.JTextField();
        jlbopcionb = new javax.swing.JLabel();
        jlbrespuestacorrec = new javax.swing.JLabel();
        jlbopcionc = new javax.swing.JLabel();
        jlbopciond = new javax.swing.JLabel();
        jlbllenarc = new javax.swing.JLabel();
        preguntaPuntos = new javax.swing.JTextField();
        labelPreguntaPuntos = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jcbetapa1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jlbingresar1 = new javax.swing.JLabel();
        jlbrespueta1 = new javax.swing.JLabel();
        jlbopciona1 = new javax.swing.JLabel();
        jtfopcionb1 = new javax.swing.JTextField();
        jtfopcionc1 = new javax.swing.JTextField();
        jtfopciona1 = new javax.swing.JTextField();
        jtfrespuestacorrecta1 = new javax.swing.JTextField();
        btn_aceptar1 = new javax.swing.JButton();
        btnguardarcam = new javax.swing.JButton();
        jcblibro1 = new javax.swing.JComboBox();
        jtfopciond1 = new javax.swing.JTextField();
        jlbopcionb1 = new javax.swing.JLabel();
        jlbrespuestacorrec1 = new javax.swing.JLabel();
        jlbopcionc1 = new javax.swing.JLabel();
        jlbopciond1 = new javax.swing.JLabel();
        jlbllenarc1 = new javax.swing.JLabel();
        jcbpreguntas = new javax.swing.JComboBox();
        btnmostrar = new javax.swing.JButton();
        jlthabilitar = new org.edisoncor.gui.label.LabelTask();
        jlbingresar2 = new javax.swing.JLabel();
        jtfingresarfop1 = new javax.swing.JTextField();
        jlbmensajetx = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Centro de Control  de Preguntas");

        tabbedPaneHeader1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabbedPaneHeader1MouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 0, 204));

        jcbetapa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbetapa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleciones la etapa");

        jlbingresar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbingresar.setForeground(new java.awt.Color(255, 255, 255));

        jtfingresarfop.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfingresarfop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfingresarfopKeyTyped(evt);
            }
        });

        jlbrespueta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbrespueta.setForeground(new java.awt.Color(255, 255, 255));
        jlbrespueta.setText("Ingrese las opciones de respuesta");

        jlbopciona.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopciona.setForeground(new java.awt.Color(255, 255, 255));
        jlbopciona.setText("A");
        jlbopciona.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopciona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopcionaMouseClicked(evt);
            }
        });

        jtfopcionb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jtfopcionc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jtfopciona.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfopciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfopcionaActionPerformed(evt);
            }
        });

        jtfrespuestacorrecta.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_aceptar.setText("Aceptar");
        btn_aceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_aceptarMouseClicked(evt);
            }
        });

        jlbmensajep.setText("jLabel6");

        btnguardar.setText("Guardar");
        btnguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnguardarMouseClicked(evt);
            }
        });
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnguardar1.setText("Guardar");
        btnguardar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnguardar1MouseClicked(evt);
            }
        });

        jcblibro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlbopcionb.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopcionb.setForeground(new java.awt.Color(255, 255, 255));
        jlbopcionb.setText("B");
        jlbopcionb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopcionb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopcionbMouseClicked(evt);
            }
        });

        jlbrespuestacorrec.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbrespuestacorrec.setForeground(new java.awt.Color(255, 255, 255));
        jlbrespuestacorrec.setText("Respuesta correcta");

        jlbopcionc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopcionc.setForeground(new java.awt.Color(255, 255, 255));
        jlbopcionc.setText("C");
        jlbopcionc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopcionc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopcioncMouseClicked(evt);
            }
        });

        jlbopciond.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopciond.setForeground(new java.awt.Color(255, 255, 255));
        jlbopciond.setText("D");
        jlbopciond.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopciond.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopciondMouseClicked(evt);
            }
        });

        jlbllenarc.setText("jLabel6");

        labelPreguntaPuntos.setText("Puntos:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbrespuestacorrec, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfrespuestacorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnguardar1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlbrespueta, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbopciona, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbopcionc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfopciona, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jtfopcionc, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlbopcionb, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbopciond, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfopcionb, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                            .addComponent(jtfopciond))))
                .addGap(272, 272, 272))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jtfingresarfop, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbetapa, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcblibro, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnguardar))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(labelPreguntaPuntos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(preguntaPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jlbmensajep, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(366, 366, 366)
                        .addComponent(jlbllenarc, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbetapa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_aceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfingresarfop, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlbmensajep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jlbingresar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcblibro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPreguntaPuntos)
                    .addComponent(preguntaPuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar)
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfrespuestacorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbrespuestacorrec, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardar1))
                .addGap(18, 18, 18)
                .addComponent(jlbrespueta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfopciona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfopcionb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbopciona)
                    .addComponent(jlbopcionb))
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfopcionc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfopciond, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbopcionc)
                    .addComponent(jlbopciond))
                .addGap(18, 18, 18)
                .addComponent(jlbllenarc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabbedPaneHeader1.addTab("Agregar", jPanel3);

        jPanel2.setBackground(new java.awt.Color(51, 0, 204));

        jcbetapa1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jcbetapa1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbetapa1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbetapa1ItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Seleciones la etapa");

        jlbingresar1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbingresar1.setForeground(new java.awt.Color(255, 255, 255));
        jlbingresar1.setText("Selecionar la pregunta");

        jlbrespueta1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbrespueta1.setForeground(new java.awt.Color(255, 255, 255));
        jlbrespueta1.setText("Modifique las opciones de respuesta");

        jlbopciona1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopciona1.setForeground(new java.awt.Color(255, 255, 255));
        jlbopciona1.setText("A");
        jlbopciona1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopciona1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopciona1MouseClicked(evt);
            }
        });

        jtfopcionb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jtfopcionc1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jtfopciona1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfopciona1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfopciona1ActionPerformed(evt);
            }
        });

        jtfrespuestacorrecta1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_aceptar1.setText("Aceptar");
        btn_aceptar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_aceptar1MouseClicked(evt);
            }
        });
        btn_aceptar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptar1ActionPerformed(evt);
            }
        });

        btnguardarcam.setText("Guardar Cambios");
        btnguardarcam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnguardarcamMouseClicked(evt);
            }
        });

        jcblibro1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jlbopcionb1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopcionb1.setForeground(new java.awt.Color(255, 255, 255));
        jlbopcionb1.setText("B");
        jlbopcionb1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopcionb1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopcionb1MouseClicked(evt);
            }
        });

        jlbrespuestacorrec1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbrespuestacorrec1.setForeground(new java.awt.Color(255, 255, 255));
        jlbrespuestacorrec1.setText("Respuesta correcta");

        jlbopcionc1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopcionc1.setForeground(new java.awt.Color(255, 255, 255));
        jlbopcionc1.setText("C");
        jlbopcionc1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopcionc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopcionc1MouseClicked(evt);
            }
        });

        jlbopciond1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbopciond1.setForeground(new java.awt.Color(255, 255, 255));
        jlbopciond1.setText("D");
        jlbopciond1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jlbopciond1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbopciond1MouseClicked(evt);
            }
        });

        jlbllenarc1.setText("jLabel6");

        jcbpreguntas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbpreguntas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbpreguntasItemStateChanged(evt);
            }
        });
        jcbpreguntas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbpreguntasActionPerformed(evt);
            }
        });

        btnmostrar.setText("Mostrar Opciones");
        btnmostrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnmostrarMouseClicked(evt);
            }
        });

        jlthabilitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/HABILITAREDICION.png"))); // NOI18N
        jlthabilitar.setText("Habilitar Edicion");
        jlthabilitar.setDescription("Desbloquee Los Campos");
        jlthabilitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlthabilitarMouseClicked(evt);
            }
        });

        jlbingresar2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jlbingresar2.setForeground(new java.awt.Color(255, 255, 255));
        jlbingresar2.setText("Ingrese el texto de la pregunta");

        jtfingresarfop1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtfingresarfop1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfingresarfop1KeyTyped(evt);
            }
        });

        jlbmensajetx.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jlbmensajetx.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbopcionc1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlbopciona1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfopciona1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtfopcionc1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbopciond1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jlbopcionb1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfopcionb1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfopciond1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(272, 272, 272))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jlbllenarc1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(jlthabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(179, 179, 179)
                                        .addComponent(jlbrespueta1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jlbrespuestacorrec1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtfrespuestacorrecta1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnguardarcam))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jlbingresar2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jtfingresarfop1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jlbingresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(113, 113, 113)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jcbetapa1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcblibro1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcbpreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_aceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnmostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 81, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jlbmensajetx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jcbetapa1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_aceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlbingresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbpreguntas, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnmostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jcblibro1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jtfingresarfop1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlbmensajetx))
                    .addComponent(jlbingresar2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfrespuestacorrecta1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnguardarcam)
                    .addComponent(jlbrespuestacorrec1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbrespueta1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfopcionb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbopcionb1)
                    .addComponent(jlbopciona1)
                    .addComponent(jtfopciona1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbopcionc1)
                    .addComponent(jtfopcionc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfopciond1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbopciond1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbllenarc1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlthabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        tabbedPaneHeader1.addTab("Modificar", jPanel2);

        jButton1.setText("Atras");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        btncancelar.setText("cancelar");
        btncancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btncancelarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(176, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(387, 387, 387))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btncancelar)
                        .addGap(41, 41, 41)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(tabbedPaneHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 1026, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tabbedPaneHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncancelar)
                    .addComponent(jButton1))
                .addGap(51, 51, 51))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aceptarMouseClicked
        String etapa = jcbetapa.getSelectedItem().toString();
        if (etapa.equals("Seleccione una etapa")) {
            jlbingresar.setVisible(false);
            jlbopciona.setVisible(false);
jlbrespuestacorrec.setVisible(false);
            jlbrespueta.setVisible(false);
            jtfopcionb.setVisible(false);
            jtfopcionc.setVisible(false);
            jtfopciona.setVisible(false);
            jtfrespuestacorrecta.setVisible(false);
            jtfingresarfop.setVisible(false);
            
            jlbmensajep.setVisible(false);
            btnguardar.setVisible(false);
            btnguardar1.setVisible(false);
            jcblibro.setVisible(false);

            JOptionPane.showMessageDialog(null, "Debe seleccionar una etapa", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
        } else {

            if (etapa.equals("segunda etapa")) {
                jlbingresar.setVisible(true);
                jlbingresar.setText("Ingrese el texto de la pregunta");
                jlbingresar.setForeground(Color.WHITE);
                jtfingresarfop.setVisible(true);
                
                jlbmensajep.setVisible(true);
                jlbmensajep.setText("Ingrese el la pregunta y precione enter");
                jlbmensajep.setForeground(Color.WHITE);
                jlbopciona.setVisible(false);
                jlbrespueta.setVisible(false);
                jtfopcionb.setVisible(false);
                jtfopcionc.setVisible(false);
                jtfopciona.setVisible(false);
                jtfrespuestacorrecta.setVisible(false);
                btnguardar.setVisible(false);
                btnguardar1.setVisible(false);
                jcblibro.setVisible(true);
                llenar_libros();
                jcbetapa.setEnabled(false);
                btn_aceptar.setVisible(false);
                
            } else if(etapa.equals("primera etapa")){
                jlbingresar.setVisible(true);
                jlbingresar.setText("Ingrese la palabra ");
                jlbingresar.setForeground(Color.WHITE);
                jtfingresarfop.setVisible(true);
                btnguardar.setVisible(true);
                jlbmensajep.setVisible(true);
                jlbmensajep.setText("Ingrese la palabra y has click en guardar");
                jlbmensajep.setForeground(Color.WHITE);
                jlbopciona.setVisible(false);
    
                jlbrespueta.setVisible(false);
                jtfopcionb.setVisible(false);
                jtfopcionc.setVisible(false);
                jtfopciona.setVisible(false);
                jtfrespuestacorrecta.setVisible(false);
                btnguardar1.setVisible(false);
                llenar_dificultad();
                jcblibro.setVisible(true);
                jcbetapa.setEnabled(false);
                btn_aceptar.setVisible(false);
                preguntaPuntos.setVisible(true);
                labelPreguntaPuntos.setVisible(true);
          
        }

        }  // TODO add your handling code here:
    }//GEN-LAST:event_btn_aceptarMouseClicked

    private void jtfingresarfopKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfingresarfopKeyTyped
v.permitir_espacio(evt);
v.permitir_solo_letra(evt);
        String dificultad=jcblibro.getSelectedItem().toString();
        if(dificultad.equals("facil")){
   
   v.longitud(jtfingresarfop, 27, evt);
}
        if(dificultad.equals("normal")){
           v.longitud(jtfingresarfop, 27, evt);
            
        }
        if(dificultad.equals("dificil")){
             v.longitud(jtfingresarfop, 27, evt);
        }
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            String pregunta = jtfingresarfop.getText();
            String libro = jcblibro.getSelectedItem().toString();
            if(libro.equals("libro del que extrajo la pregunta") || pregunta.equalsIgnoreCase("")){
            if(libro.equals("libro del que extrajo la pregunta")){    
             JOptionPane.showMessageDialog(null, "Debe selecionar un libro antes de agregar la pregunta", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
             jtfingresarfop.setText("");
            }
            
            if (pregunta.equalsIgnoreCase("")) {

                JOptionPane.showMessageDialog(null, "Debe ingresar la pregunta", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
            }
            }
            
            else {

                jlbingresar.setVisible(true);
                jlbopciona.setVisible(true);
                jlbrespuestacorrec.setVisible(true);
                jlbrespueta.setVisible(true);
                jtfopcionb.setVisible(true);
                jtfopcionc.setVisible(true);
                jtfopciona.setVisible(true);
                jtfrespuestacorrecta.setVisible(true);
                jtfingresarfop.setVisible(true);
                jlbllenarc.setVisible(true);
                jlbllenarc.setVisible(true);
                jlbllenarc.setText("Ingrese las cuatro opciones y seleccione la correcta");
                jlbllenarc.setForeground(Color.WHITE);
                jlbmensajep.setVisible(true);
                jlbopciond.setVisible(true);
                jtfopciond.setVisible(true);
                jlbopcionc.setVisible(true);
                jlbopcionb.setVisible(true);
                jcblibro.setEnabled(false);
                jtfingresarfop.setEditable(false);
                jlbmensajep.setVisible(false);
                jtfopcionb.setText("");
                    jtfopcionc.setText("");
                    jtfopciona.setText("");
                    jtfopciond.setText("");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfingresarfopKeyTyped

    private void btnguardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardarMouseClicked
       
       if(jcblibro.getSelectedItem().toString().equals("Elija la dificultad de la palabra")){
        JOptionPane.showMessageDialog(null, "Debe elegir una dificultad", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
        jtfingresarfop.setText("");
       }
           
       else if(jtfingresarfop.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Debe Ingresar la palabra", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                
}else{
        if(jcbetapa.getSelectedItem().toString().equals("primera etapa")){
            try {
                boolean exsiste=m.Select3("tbl_palabra", "*", "tx_palabra='" + jtfingresarfop.getText() + "'");
                
                if(exsiste==true){
                    JOptionPane.showMessageDialog(null, "Esa palabra ya fue ingresada... INGRESE OTRA PALABRA O CAMBIE A MODIFICAR EN MODIFICAR", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int dificultadNum=m.Select2("tbl_dificultad","id_dificultad", "tx_nombre = '"+jcblibro.getSelectedItem().toString()+"'").getInt("id_dificultad");
                    
                    boolean listo=m.Insert("tbl_palabra", "tx_palabra,int_dificultad, int_puntos", "'" + jtfingresarfop.getText().toString() + "', "+dificultadNum+", "+preguntaPuntos.getText());
                    if(listo==true){
                        JOptionPane.showMessageDialog(null, "Palabra ingresada correctamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                        jlbingresar.setVisible(false);
                        jtfingresarfop.setVisible(false);
                        btnguardar.setVisible(false);
                        jcbetapa.setEnabled(true);
                        jcbetapa.setSelectedItem("Seleccione una etapa");
                        jtfingresarfop.setText("");
                        btn_aceptar.setVisible(true);
                        jlbmensajep.setVisible(false);
                        labelPreguntaPuntos.setVisible(false);
                        preguntaPuntos.setVisible(false);
                        jcblibro.setSelectedItem("Seleccionar palabra");
                         jcblibro.setVisible(false);
                    }   else{
                        JOptionPane.showMessageDialog(null, " Palabra no ingresada ", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
           else {
            boolean ya=(m.Select3("tbl_preguntas", "*", "tx_preguntas='" + jtfingresarfop.getText() + "'and id_etapa=2"));
            int etapa=2;
            if(ya==true){
                JOptionPane.showMessageDialog(null, "Esa palabra ya fue ingresada... INGRESE OTRA PALABRA O CAMBIE A MODIFICAR EN MODIFICAR", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
            } else{
                boolean listo=m.Insert("tbl_preguntas", "tx_preguntas,id_etapa", "'" + jtfingresarfop.getText().toString() + "'," + etapa + "");
                if(listo==true){
                    JOptionPane.showMessageDialog(null, "Palabra ingresada correctamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                }   else{
                    JOptionPane.showMessageDialog(null, " Palabra no ingresada ", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                }
                jlbingresar.setVisible(false);
        jtfingresarfop.setVisible(false);
        btnguardar.setVisible(false);
        jcbetapa.setEnabled(true);
       jcbetapa.setSelectedItem("Seleccione una etapa");
        jtfingresarfop.setText("");
                btn_aceptar.setVisible(true);
                jlbmensajep.setVisible(false);
            }
               
                
        }

         
        } 
        
                
                // TODO add your handling code here:
    }//GEN-LAST:event_btnguardarMouseClicked

    private void btnguardar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardar1MouseClicked
        String pregunta,opa,opb,opc="c",opd="d", correcta,opciona, opcionb, opcionc, opciond, libro;
        pregunta = jtfingresarfop.getText();
        correcta = jtfrespuestacorrecta.getText();
        opcionb = jtfopcionb.getText();
        opcionc = jtfopcionc.getText();
        opciona = jtfopciona.getText();
        opciond = jtfopciond.getText();
        opa="a";
        opb="b";
      
        libro = jcblibro.getSelectedItem().toString();
        
        int etapa = 3;
        if (opciona.equals("") || opcionb.equals("") || opciona.equals("") || opcionb.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todas las opciones, incluyendo seleccionar un libro", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
        } 
        else if(opciona.equals(opcionb) || opciona.equals(opcionc) || opciona.equals(opciond) || opcionb.equals(opcionc) || opcionb.equals(opciond) || opcionc.equals(opciond)){
            JOptionPane.showMessageDialog(null, "ninguna de las opcions deben ser iguales", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
            
        }
        else {
            try {
                String id_libro = m.Select2("tbl_libro", "*", "nb_libro='" + libro + "'").getString("id_libro");

                boolean hecho = m.Insert("tbl_preguntas", "tx_preguntas,respuesta_correcta,id_etapa,id_libro", "'" + pregunta + "','" + correcta + "'," + etapa + ",'" + id_libro + "'");
                String id_pregunta=m.SelectUltimo("tbl_preguntas", "tx_preguntas='" + pregunta +"'").getString("id_preguntas");
                m.Insert("tbl_resp_inc", "resp_incorrecta,id_preguntas,opcion", "'" + opciona + "','" + id_pregunta + "','"+opa+"'");
                m.Insert("tbl_resp_inc", "resp_incorrecta,id_preguntas,opcion", "'" + opcionb + "','" + id_pregunta + "','"+opa+"'");
                m.Insert("tbl_resp_inc", "resp_incorrecta,id_preguntas,opcion", "'" + opcionc + "','" + id_pregunta + "','"+opa+"'");
                m.Insert("tbl_resp_inc", "resp_incorrecta,id_preguntas,opcion", "'" + opciond + "','" + id_pregunta + "','"+opa+"'");
                
                if (hecho == true) {
                    JOptionPane.showMessageDialog(null, "Pregunta agregada exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                    jtfingresarfop.setVisible(false);
                    jtfingresarfop.setText("");
                    jtfrespuestacorrecta.setText("");
                    jtfrespuestacorrecta.setVisible(false);
                    jtfopcionb.setText("");
                    jtfopcionc.setText("");
                    jtfopciona.setText("");
                    jtfopciond.setText("");
                    jtfopcionb.setVisible(false);
                    jtfopciona.setVisible(false);
                    jtfopcionc.setVisible(false);
                    jtfopciond.setVisible(false);
                    jlbingresar.setVisible(false);
                    jtfingresarfop.setVisible(false);
                    jtfingresarfop.setText("");
                    jcblibro.setVisible(false);
                    btnguardar1.setVisible(false);
                    jlbrespuestacorrec.setVisible(false);
                    jlbopcionb.setVisible(false);
                    jlbopciona.setVisible(false);
                    jlbopcionc.setVisible(false);
                    jlbopciond.setVisible(false);
                    jlbllenarc.setVisible(false);
                    jcbetapa.setEnabled(true);
                    jcbetapa.setSelectedItem("Seleccione una etapa");
                    btn_aceptar.setVisible(true);
                    jlbrespueta.setVisible(false);
//colocar la pantalla con las condiciones iniciales
                } else {
                    JOptionPane.showMessageDialog(null, "El registro no se pudo realizar", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
               Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        } 
       // insertar_incorrecta(pregunta,incorrecta1,incorrecta2,incorrecta3);// TODO add your handling code here:
    }//GEN-LAST:event_btnguardar1MouseClicked

    private void jtfopcionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfopcionaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfopcionaActionPerformed

    private void jlbopcionaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopcionaMouseClicked
    String opciona;
    opciona=jtfopciona.getText();
    if(opciona.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
    }
    else{
    jtfrespuestacorrecta.setText(opciona);
    btnguardar1.setVisible(true);
    }
// TODO add your handling code here:
    }//GEN-LAST:event_jlbopcionaMouseClicked

    private void jlbopcionbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopcionbMouseClicked
    String opcionb;
    opcionb=jtfopcionb.getText();
    if(opcionb.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
   
    }
    else{
    jtfrespuestacorrecta.setText(opcionb);
    btnguardar1.setVisible(true);
    } 
    // TODO add your handling code here:
    }//GEN-LAST:event_jlbopcionbMouseClicked

    private void jlbopcioncMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopcioncMouseClicked
     String opcionc;
    opcionc=jtfopcionc.getText();
    if(opcionc.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
    }
    else{
    jtfrespuestacorrecta.setText(opcionc);
    btnguardar1.setVisible(true);
    }    // TODO add your handling code here:
    }//GEN-LAST:event_jlbopcioncMouseClicked

    private void jlbopciondMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopciondMouseClicked
      String opciond;
    opciond=jtfopciond.getText();
    if(opciond.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
    }
    else{
    jtfrespuestacorrecta.setText(opciond);
    btnguardar1.setVisible(true);
    }   // TODO add your handling code here:
    }//GEN-LAST:event_jlbopciondMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
 new menu_principal(m).setVisible(true);
 this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1MouseClicked

    private void btncancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btncancelarMouseClicked
    jcbetapa.setEnabled(true);
    btnguardar.setVisible(true);
    jtfingresarfop.setVisible(false);
    jcblibro.setVisible(false);
    jlbingresar.setVisible(true);
                jlbopciona.setVisible(false);
                jlbrespuestacorrec.setVisible(false);
                jlbrespueta.setVisible(false);
                jtfopcionb.setVisible(false);
                jtfopcionc.setVisible(false);
                jtfopciona.setVisible(false);
                jtfrespuestacorrecta.setVisible(false);
                jtfingresarfop.setVisible(false);
                jlbllenarc.setVisible(false);
                jlbllenarc.setVisible(false);
                jlbllenarc.setText("Ingrese las cuatro opciones y seleccione la correcta");
                jlbllenarc.setForeground(Color.WHITE);
                jlbmensajep.setVisible(false);
                jlbopciond.setVisible(false);
                jtfopciond.setVisible(false);
                jlbopcionc.setVisible(false);
                jlbopcionb.setVisible(false);
                btnguardar1.setVisible(false);
                btnguardar.setVisible(false);
                jlbrespuestacorrec.setVisible(false);
                btn_aceptar.setVisible(true);
              jcbetapa1.setEnabled(true);
              jlbingresar.setVisible(false);
              jtfingresarfop.setText("");
              jcbetapa.setSelectedItem("Seleccione una etapa");
    btnguardar1.setVisible(true);
  
    jcblibro1.setVisible(false);
    jlbingresar1.setVisible(true);
                jlbopciona1.setVisible(false);
                jlbrespuestacorrec1.setVisible(false);
                jlbrespueta1.setVisible(false);
                jtfopcionb1.setVisible(false);
                jtfopcionc1.setVisible(false);
                jtfopciona1.setVisible(false);
                jtfrespuestacorrecta1.setVisible(false);
                jtfingresarfop.setVisible(false);
                jlbllenarc1.setVisible(false);
                jlbllenarc1.setVisible(false);
                jlbllenarc1.setText("Ingrese las cuatro opciones y seleccione la correcta");
                jlbllenarc1.setForeground(Color.WHITE);
               
                jlbopciond1.setVisible(false);
                jtfopciond1.setVisible(false);
                jlbopcionc1.setVisible(false);
                jlbopcionb1.setVisible(false);
                btnguardar1.setVisible(false);
                btnguardar1.setVisible(false);
                jlbrespuestacorrec1.setVisible(false);
                btn_aceptar1.setVisible(true);
                jlbingresar1.setVisible(false);
                jcbpreguntas.setVisible(false);
                 btnmostrar.setVisible(false);
                 jlthabilitar.setVisible(false);
                 jlbmensajetx.setVisible(false);
                  jtfingresarfop1.setVisible(false);
                   jlbingresar2.setVisible(false);
                   jcbetapa1.setSelectedItem("Selecione una etapa");
                   btnguardarcam.setVisible(false);
                 inicio=1;  
                 jcbpreguntas.setEnabled(true);
                 jcbpreguntas.setSelectedItem("Seleccione una pregunta");
        // TODO add your handling code here:
    }//GEN-LAST:event_btncancelarMouseClicked

    private void jlbopciona1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopciona1MouseClicked
String opciona;
    opciona=jtfopciona1.getText();
    if(opciona.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
    }
    else{
    jtfrespuestacorrecta1.setText(opciona);
    btnguardar1.setVisible(true);
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbopciona1MouseClicked

    private void jtfopciona1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfopciona1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfopciona1ActionPerformed

    private void btn_aceptar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_aceptar1MouseClicked
  String etapa = jcbetapa1.getSelectedItem().toString();
        if (etapa.equals("Seleccione una etapa")) {
            jlbingresar1.setVisible(false);
            jlbopciona1.setVisible(false);
jlbrespuestacorrec1.setVisible(false);
            jlbrespueta1.setVisible(false);
            jtfopcionb1.setVisible(false);
            jtfopcionc1.setVisible(false);
            jtfopciona1.setVisible(false);
            jtfrespuestacorrecta1.setVisible(false);
            
            jcblibro.setVisible(false);

            JOptionPane.showMessageDialog(null, "Debe seleccionar una etapa", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
        } else {

            if (etapa.equalsIgnoreCase("segunda etapa")) {
                jlbingresar1.setVisible(true);
                jlbingresar1.setText("Seleccione alguna pregunta");
                jlbingresar1.setForeground(Color.WHITE);
                
                jlbopciona1.setVisible(false);
                jlbrespueta1.setVisible(false);
                jtfopcionb1.setVisible(false);
                jtfopcionc1.setVisible(false);
                jtfopciona1.setVisible(false);
                jtfrespuestacorrecta1.setVisible(false);
               inicio=1;
                
                jcbetapa1.setEnabled(false);
                btn_aceptar1.setVisible(false);
                jcbpreguntas.setVisible(true);
                btnmostrar.setVisible(true);
                btnmostrar.setText("Modificar");
               
                
            } else if(etapa.equals("primera etapa")) {
                inicio=1;
                jcbpreguntas.setVisible(true);
                jlbingresar1.setVisible(true);
                jlbingresar1.setText("Seleccione alguna palabra");
                jlbingresar1.setForeground(Color.WHITE);
                llenar_palabras();
                btnguardar1.setVisible(false);
                jcblibro.setVisible(false);
                jcbetapa1.setEnabled(false);
                btn_aceptar1.setVisible(false);
                btnmostrar.setVisible(true);
                btnmostrar.setText("Modificar");
                
          
            }

        }      // TODO add your handling code here:
    }//GEN-LAST:event_btn_aceptar1MouseClicked

    
    private void btnguardarcamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnguardarcamMouseClicked
if(jcbetapa1.getSelectedItem().toString().equals("segunda etapa")){
        String pregunta, correcta,opciona, opcionb, opcionc, opciond, libro,opa,opb,opc,opd;
      opa="a";
      opb="b";
      
      opc="c";
      opd="d";
      if(inicio==3){
       pregunta = jtfingresarfop1.getText();
       
      }else{
      pregunta=jcbpreguntas.getSelectedItem().toString();
      }
       
      
        correcta = jtfrespuestacorrecta1.getText();
        opcionb = jtfopcionb1.getText();
        opcionc = jtfopcionc1.getText();
        opciona = jtfopciona1.getText();
        opciond = jtfopciond1.getText();
        
        libro = jcblibro1.getSelectedItem().toString();
        
        int etapa = 3;
        if (pregunta.equals("")||opciona.equals("") || opcionb.equals("") || opciona.equals("") || opcionb.equals("")||libro.equals("libro del que extrajo la pregunta")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar todos los campos, incluyendo seleccionar un libro", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
        } 
        else if(opciona.equals(opcionb) || opciona.equals(opcionc) || opciona.equals(opciond) || opcionb.equals(opcionc) || opcionb.equals(opciond) || opcionc.equals(opciond)){
            JOptionPane.showMessageDialog(null, "ninguna de las opcions deben ser iguales", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
            
        }
       // else if(opciona!=correcta || (opcionc!=correcta ||opcionb!=correcta || opciond!=correcta){
        //JOptionPane.showMessageDialog(null, "Debe seleccionar alguna de las opciones ingresadas como correcta", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);    
        //}
        else if(opciona.equals(correcta) || opciond.equals(correcta) || opcionc.equals(correcta)||opcionb.equals(correcta)){
            try {
                String id_libro = m.Select2("tbl_libro", "*", "nb_libro='" + libro + "'").getString("id_libro");
                String id_pregunta=m.Select2("tbl_preguntas", "*", "tx_preguntas='" + jcbpreguntas.getSelectedItem().toString() +"'").getString("id_preguntas");
                boolean hecho = m.Update("tbl_preguntas", "tx_preguntas='"+pregunta+"',respuesta_correcta='"+correcta+"',id_etapa='"+etapa+"',id_libro='"+id_libro+"'","id_preguntas='"+id_pregunta+"'");
               
                m.Update("tbl_resp_inc", "resp_incorrecta='" + opciona + "',opcion='"+opa+"'"," id_preguntas='"+id_pregunta+"'and opcion='"+opa+"'");
                m.Update("tbl_resp_inc", "resp_incorrecta='" + opcionb + "',opcion='"+opb+"'"," id_preguntas='"+id_pregunta+"'and opcion='"+opb+"'");
                m.Update("tbl_resp_inc", "resp_incorrecta='" + opcionc + "',opcion='"+opc+"'"," id_preguntas='"+id_pregunta+"'and opcion='"+opc+"'");
                m.Update("tbl_resp_inc", "resp_incorrecta='" + opciond + "',opcion='"+opd+"'"," id_preguntas='"+id_pregunta+"'and opcion='"+opd+"'");
                
                if (hecho == true) {
                    JOptionPane.showMessageDialog(null, "Los cambios se guardaron exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                   
//colocar la pantalla con las condiciones iniciales
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar los cambios", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
               Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
        jcbetapa1.setEnabled(true);
    btnguardar1.setVisible(true);
  
    jcblibro1.setVisible(false);
    jlbingresar1.setVisible(true);
                jlbopciona1.setVisible(false);
                jlbrespuestacorrec1.setVisible(false);
                jlbrespueta1.setVisible(false);
                jtfopcionb1.setVisible(false);
                jtfopcionc1.setVisible(false);
                jtfopciona1.setVisible(false);
                jtfrespuestacorrecta1.setVisible(false);
                jtfingresarfop.setVisible(false);
                jlbllenarc1.setVisible(false);
                jlbllenarc1.setVisible(false);
                jlbllenarc1.setText("Ingrese las cuatro opciones y seleccione la correcta");
                jlbllenarc1.setForeground(Color.WHITE);
               jcbpreguntas.setEnabled(true);
                jlbopciond1.setVisible(false);
                jtfopciond1.setVisible(false);
                jlbopcionc1.setVisible(false);
                jlbopcionb1.setVisible(false);
                btnguardar1.setVisible(false);
                btnguardar1.setVisible(false);
                jlbrespuestacorrec1.setVisible(false);
                btn_aceptar1.setVisible(true);
                jlbingresar1.setVisible(false);
                jcbpreguntas.setVisible(false);
                 btnmostrar.setVisible(false);
                 jlthabilitar.setVisible(false);
                 jlbmensajetx.setVisible(false);
                  jtfingresarfop1.setVisible(false);
                   jlbingresar2.setVisible(false);
                   jcbetapa1.setSelectedItem("Selecione una etapa");
                   btnguardarcam.setVisible(false);
                 inicio=1;   
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar alguna de las opciones ingresadas como correcta", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);    
        
        }
}else if(jcbetapa1.getSelectedItem().toString().equals("primera etapa")){

if (jtfingresarfop1.getText().equals("")){
      JOptionPane.showMessageDialog(null, "Debe ingresar alguna palabra", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
 
}else{
    try {
    String palabra=m.Select2("tbl_palabras", "*", "tx_nombre='" + jcbpreguntas.getSelectedItem().toString() +"'").getString("id_palabra");     
  boolean listo= m.Update("tbl_palabras", "tx_nombre='"+jtfingresarfop1.getText()+"'","id_palabra='"+palabra+"'");
if(listo==true){
    JOptionPane.showMessageDialog(null, "Los cambios se guardaron exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
}else{
    JOptionPane.showMessageDialog(null, "Error al guardar los cambios", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                
}
    

              } catch (SQLException ex) {
               Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }
         jlbingresar2.setVisible(false);
    jlbingresar2.setText("Ingrese el texto de la pregunta");
    jtfingresarfop1.setVisible(false);               
jlbingresar1.setText("seleccione una pregunta");
jlbingresar1.setVisible(false);jcbpreguntas.setVisible(false);
jcbpreguntas.setEnabled(true);
btnguardarcam.setVisible(false);
btn_aceptar1.setVisible(true);
jcbetapa1.setEnabled(true);
 jcbetapa1.setSelectedItem("Seleccione una etapa");
}
}
    }//GEN-LAST:event_btnguardarcamMouseClicked

    private void jlbopcionb1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopcionb1MouseClicked
    String opcionb;
    opcionb=jtfopcionb1.getText();
    if(opcionb.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
    }
    else{
    jtfrespuestacorrecta1.setText(opcionb);
    btnguardar1.setVisible(true);
    
    } // TODO add your handling code here:
    }//GEN-LAST:event_jlbopcionb1MouseClicked

    private void jlbopcionc1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopcionc1MouseClicked
      String opcionc;
    opcionc=jtfopcionc1.getText();
    if(opcionc.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
    }
    else{
    jtfrespuestacorrecta1.setText(opcionc);
    btnguardar1.setVisible(true);
    }   // TODO add your handling code here:
    }//GEN-LAST:event_jlbopcionc1MouseClicked

    private void jlbopciond1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbopciond1MouseClicked
       String opciond;
    opciond=jtfopciond1.getText();
    if(opciond.equals("")){
    JOptionPane.showMessageDialog(null, "Debe ingresar la opcion", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
    }
    else{
    jtfrespuestacorrecta1.setText(opciond);
    btnguardar1.setVisible(true);
    }  // TODO add your handling code here:
    }//GEN-LAST:event_jlbopciond1MouseClicked

    private void jcbpreguntasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbpreguntasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbpreguntasActionPerformed

    private void jcbetapa1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbetapa1ItemStateChanged
   try {
            // TODO add your handling code here:
            if (inicio == 1) {
                Object etapa = jcbetapa1.getSelectedItem();
                String etapa2 = etapa.toString();
                if (!etapa2.equals("Seleccione una etapa")) {
                    String id_etapa = m.Select2("tbl_etapas", "id_etapa", "nombre_etapa='" + etapa2 + "'").getString("id_etapa");
                    
                    llenar_preguntas(id_etapa);
                    inicio=2;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }     // TODO add your handling code here:
    }//GEN-LAST:event_jcbetapa1ItemStateChanged

    private void btnmostrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmostrarMouseClicked
String etapa=jcbetapa1.getSelectedItem().toString();
        if(etapa.equals("segunda etapa")){
        jcblibro1.setVisible(true); 
     if(jcbpreguntas.getSelectedItem().toString().equals("Seleccione una pregunta")){
         JOptionPane.showMessageDialog(null, "Debe seleccionar una pregunta", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
        
     }else{
      try{
         String id_pregunta=m.Select2("tbl_preguntas", "id_preguntas", "tx_preguntas='" + jcbpreguntas.getSelectedItem().toString()+ "'").getString("id_preguntas");
         String id_libro=m.Select2("tbl_preguntas", "id_libro", "tx_preguntas='" + jcbpreguntas.getSelectedItem().toString()+ "'").getString("id_libro");
       jcblibro1.setSelectedItem(m.Select2("tbl_libro", "nb_libro", "id_libro='" + id_libro + "'").getString("nb_libro"));
       jcblibro1.setVisible(true); 
       jlbrespuestacorrec1.setVisible(true);
       jtfrespuestacorrecta1.setVisible(true);
       jtfrespuestacorrecta1.setText(m.Select2("tbl_preguntas", "respuesta_correcta", "tx_preguntas='" + jcbpreguntas.getSelectedItem().toString()+ "'").getString("respuesta_correcta"));
       jlbrespueta1.setVisible(true);
       jlbopciona1.setVisible(true);
       jtfopciona1.setVisible(true);
       jlbopcionb1.setVisible(true);
       jtfopcionb1.setVisible(true);
       jlbopcionc1.setVisible(true);
       jtfopcionc1.setVisible(true);
       jlbopciond1.setVisible(true);
       jtfopciond1.setVisible(true);
       jtfopciona1.setText(m.Select2("tbl_resp_inc", "resp_incorrecta", "id_preguntas='" + id_pregunta + "' and opcion='"+ "a" +"'").getString("resp_incorrecta"));
       jtfopcionb1.setText(m.Select2("tbl_resp_inc", "resp_incorrecta", "id_preguntas='" + id_pregunta + "' and opcion='"+ "b" +"'").getString("resp_incorrecta"));
       jtfopcionc1.setText(m.Select2("tbl_resp_inc", "resp_incorrecta", "id_preguntas='" + id_pregunta + "' and opcion='"+ "c" +"'").getString("resp_incorrecta"));
       jtfopciond1.setText(m.Select2("tbl_resp_inc", "resp_incorrecta", "id_preguntas='" + id_pregunta + "' and opcion='"+ "d" +"'").getString("resp_incorrecta"));
       jcblibro1.setEnabled(false); 
       jlbrespueta1.setEnabled(false);
       jlbopciona1.setEnabled(false);
       jtfopciona1.setEditable(false);
       jlbopcionb1.setEnabled(false);
       jtfopcionb1.setEditable(false);
       jlbopcionc1.setEnabled(false);
       jtfopcionc1.setEditable(false);
       jlbopciond1.setEnabled(false);
       jtfopciond1.setEditable(false);
      jlbllenarc1.setVisible(true);
      jlbllenarc1.setText("Para realizar cambios en la pregunta presione habilitar edicion");
      jlbllenarc1.setForeground(Color.WHITE);
      btnmostrar.setVisible(false);
      jlthabilitar.setVisible(true);
       jcbpreguntas.setEnabled(false);
      jtfrespuestacorrecta1.setEditable(false);
      }catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        } 
     }     
} 
        if(etapa.equals("primera etapa")){
    if(jcbpreguntas.getSelectedItem().toString().equals("Seleccionar palabra")){
         JOptionPane.showMessageDialog(null, "Debe seleccionar una palabra", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
        
     }else{
        
    jlbingresar2.setVisible(true);
    jlbingresar2.setText("Edite la pregunta");
    jtfingresarfop1.setVisible(true);
    jtfingresarfop1.setText(jcbpreguntas.getSelectedItem().toString());
    jtfingresarfop1.setEditable(true);
    btnmostrar.setVisible(false);
    btnmostrar.setText("Mostrar opciones");
    btnguardarcam.setVisible(true);
    jcbpreguntas.setEnabled(false);
   
    
    }
}
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmostrarMouseClicked

    private void jcbpreguntasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbpreguntasItemStateChanged
     /*try {
        if (inicio == 2) {
                Object preg = jcbpreguntas.getSelectedItem();
                String preg2 = preg.toString();
                if (!preg2.equals("Seleccione una pegunta")) {
                    String id_libro = m.Select2("tbl_preguntas", "id_libro", "tx_preguntas='" + preg2 + "'").getString("id_libro");
                    llenar_libro();
                    
                    
                }
       
        }
        } catch (SQLException ex) {
            Logger.getLogger(preguntas.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jcbpreguntasItemStateChanged

    private void jlthabilitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlthabilitarMouseClicked
jcblibro1.setEnabled(true); 
       jlbrespueta1.setEnabled(true);
       jlbopciona1.setEnabled(true);
       jtfopciona1.setEditable(true);
       jlbopcionb1.setEnabled(true);
       jtfopcionb1.setEditable(true);
       jlbopcionc1.setEnabled(true);
       jtfopcionc1.setEditable(true);
       jlbopciond1.setEnabled(true);
       jtfopciond1.setEditable(true);
      jlbllenarc1.setVisible(true);
      jlbllenarc1.setText("Realice los cambios y seleccione la opccion que sera correcta");
      jlbllenarc1.setForeground(Color.WHITE);
      btnmostrar.setVisible(false);
      jlthabilitar.setVisible(false);
       jcbpreguntas.setEnabled(false);
      jtfrespuestacorrecta1.setEditable(false);
      btnguardarcam.setVisible(true);
   //   JOptionPane.showConfirmDialog(rootPane, "¿Desea editar tambien el texto de la pregunta?","Cambiar texto de la pregunta", JOptionPane.YES_NO_OPTION);
      if( JOptionPane.showConfirmDialog(rootPane, "¿Desea cambiar tambien el texto de la pregunta?","Cambiar texto de la pregunta", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
          jtfingresarfop1.setVisible(true);
          jlbingresar2.setVisible(true);
          inicio=3;
      }else{
           jtfingresarfop1.setVisible(false);
          jlbingresar2.setVisible(false);
      }
      
        
// TODO add your handling code here:
    }//GEN-LAST:event_jlthabilitarMouseClicked

    private void jtfingresarfop1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfingresarfop1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfingresarfop1KeyTyped

    private void tabbedPaneHeader1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabbedPaneHeader1MouseClicked
       
        // TODO add your handling code here:
    }//GEN-LAST:event_tabbedPaneHeader1MouseClicked

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btn_aceptar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_aceptar1ActionPerformed
    //public void insertar_incorrecta(pregunta,incorrecta1,incorrecta2,incorrecta3){
    //id_pregunta=m.Select2("tbl_preguntas", "*", "tx_preguntas='" + pregunta + "'").getString("id_libro");
//}

    /**
     * @param args the command line arguments
     */
    public void main(ManejadorSQL m2) {
        m = m2;
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(preguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(preguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(preguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(preguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new preguntas(m).setVisible(true);
                new menu_principal(m).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JButton btn_aceptar1;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnguardar1;
    private javax.swing.JButton btnguardarcam;
    private javax.swing.JButton btnmostrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox jcbetapa;
    private javax.swing.JComboBox jcbetapa1;
    private javax.swing.JComboBox jcblibro;
    private javax.swing.JComboBox jcblibro1;
    private javax.swing.JComboBox jcbpreguntas;
    private javax.swing.JLabel jlbingresar;
    private javax.swing.JLabel jlbingresar1;
    private javax.swing.JLabel jlbingresar2;
    private javax.swing.JLabel jlbllenarc;
    private javax.swing.JLabel jlbllenarc1;
    private javax.swing.JLabel jlbmensajep;
    private javax.swing.JLabel jlbmensajetx;
    private javax.swing.JLabel jlbopciona;
    private javax.swing.JLabel jlbopciona1;
    private javax.swing.JLabel jlbopcionb;
    private javax.swing.JLabel jlbopcionb1;
    private javax.swing.JLabel jlbopcionc;
    private javax.swing.JLabel jlbopcionc1;
    private javax.swing.JLabel jlbopciond;
    private javax.swing.JLabel jlbopciond1;
    private javax.swing.JLabel jlbrespuestacorrec;
    private javax.swing.JLabel jlbrespuestacorrec1;
    private javax.swing.JLabel jlbrespueta;
    private javax.swing.JLabel jlbrespueta1;
    private org.edisoncor.gui.label.LabelTask jlthabilitar;
    private javax.swing.JTextField jtfingresarfop;
    private javax.swing.JTextField jtfingresarfop1;
    private javax.swing.JTextField jtfopciona;
    private javax.swing.JTextField jtfopciona1;
    private javax.swing.JTextField jtfopcionb;
    private javax.swing.JTextField jtfopcionb1;
    private javax.swing.JTextField jtfopcionc;
    private javax.swing.JTextField jtfopcionc1;
    private javax.swing.JTextField jtfopciond;
    private javax.swing.JTextField jtfopciond1;
    private javax.swing.JTextField jtfrespuestacorrecta;
    private javax.swing.JTextField jtfrespuestacorrecta1;
    private javax.swing.JLabel labelPreguntaPuntos;
    private javax.swing.JTextField preguntaPuntos;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader tabbedPaneHeader1;
    // End of variables declaration//GEN-END:variables
}
