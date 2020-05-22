/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gedeon1;

import base_dato.ManejadorSQL;
import base_dato.ObtenerFecha;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class admin_usuario extends javax.swing.JFrame {

    int inicio = 0;
    ManejadorSQL m;
    int listo = 0;

    /**
     * Creates new form admin_usuario
     */
    public admin_usuario(ManejadorSQL m2) {
        m = m2;
        int rol = m.id_rol;
       
        if (rol != 3) {
            initComponents();
             fecha();
            jtfnombre.setEditable(false);
            jtfapellido.setEditable(false);
            jcbsexo.setEnabled(false);
            jdcnacimiento.setEnabled(false);
            jtfcorreo.setEditable(false);
            //jcbestado.setEnabled(false);
            jcbmunicipio.setEnabled(false);
            jcbciudad.setEnabled(false);
            jtadireccion.setEnabled(false);
          
            tabbed.setEnabledAt(1, false);
            escolar();
            jtfclave.setEditable(false);
            jtfrepetirclave.setEditable(false);
            jcbps.setEnabled(false);
            jtfrs.setEditable(false);

            btnregistrar.setVisible(false);
            btneliminar.setVisible(false);
            btnmodificar.setVisible(false);
            mensajeci.setVisible(false);
            rellenar_estados();
            inicio = 1;
 mensajeci.setVisible(true);
            mensajeci.setText("Ingrese su Numero de cedula y Precione Enter");
            mensajeci.setForeground(Color.WHITE);
            rellenar_roles();
            btnlimpiar.setVisible(false);
        } else {
            initComponents();
             fecha();
            rellenar_estados();
            inicio = 1;
           btneliminar.setVisible(false);
           btnlimpiar.setVisible(false);
           btnmodificar.setVisible(false);
        }

    }

    public void fecha() {
        try {
            ObtenerFecha f = new ObtenerFecha();
            String fecha = f.ObtenerFecha();

            SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

            Date fechan = null;
            fechan = formatoDelTexto.parse(fecha);

            jdcnacimiento.setDate(fechan);
        } catch (ParseException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void rellenar_estados() {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_estado");
            jcbestado.removeAllItems();
            modeloCombo.addElement("Seleccione un Estado");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_estado"));
            }
            rs.close();
            jcbestado.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rellenar_roles() {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_rol_usr");
            jcbrol.removeAllItems();
            modeloCombo.addElement("Seleccione un Rol de Usuario");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_rol_usr"));
            }
            rs.close();
            jcbrol.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escolar() {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_grado");
            jcbescolar.removeAllItems();
            modeloCombo.addElement("Seleccione un Grado Escolar");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_grado"));
            }
            rs.close();
            jcbescolar.setModel(modeloCombo);

        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rellenar_seccion(String id_escolar) {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_seccion WHERE id_grado=" + id_escolar + "");
            jcbseccion.removeAllItems();
            modeloCombo.addElement("Seleccione una Sección");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_seccion"));
            }
            rs.close();
            jcbseccion.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void rellenar_municipios(String id_e) {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_municipio WHERE id_estado=" + id_e + "");
            jcbmunicipio.removeAllItems();
            modeloCombo.addElement("Seleccione un Municipio");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_municipio"));
            }
            rs.close();
            jcbmunicipio.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void rellenar_ciudades(String id_m) {
        try {

            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            Class.forName("org.postgresql.Driver");
            Connection conexion = m.devolverconexion();//DriverManager.getConnection("jdbc:postgresql://localhost/dbsitxian", "importadoraxian", "sitvenpfi");
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbl_ciudad WHERE id_municipio=" + id_m + "");
            jcbciudad.removeAllItems();
            modeloCombo.addElement("Seleccione una Ciudad");
            while (rs.next()) {
                modeloCombo.addElement(rs.getObject("nb_ciudad"));
            }
            rs.close();
            jcbciudad.setModel(modeloCombo);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
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
        tabbed = new org.edisoncor.gui.tabbedPane.TabbedPaneHeader();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jcbmunicipio = new javax.swing.JComboBox();
        jcbestado = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtadireccion = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jtfapellido = new javax.swing.JTextField();
        jtfcedula = new javax.swing.JTextField();
        jcbsexo = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jtfnombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfcorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jcbciudad = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        mensajeci = new javax.swing.JLabel();
        jdcnacimiento = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jcbrol = new javax.swing.JComboBox();
        jtfrs = new javax.swing.JTextField();
        jcbps = new javax.swing.JComboBox();
        jcbseccion = new javax.swing.JComboBox();
        jcbescolar = new javax.swing.JComboBox();
        jtfrepetirclave = new javax.swing.JPasswordField();
        jtfclave = new javax.swing.JPasswordField();
        jtfusuario = new javax.swing.JTextField();
        btnregistrar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnlimpiar = new javax.swing.JButton();
        btnatras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 0, 204));

        jPanel5.setBackground(new java.awt.Color(51, 0, 204));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Estado:");

        jcbmunicipio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbmunicipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbmunicipioItemStateChanged(evt);
            }
        });

        jcbestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbestado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbestadoItemStateChanged(evt);
            }
        });
        jcbestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbestadoActionPerformed(evt);
            }
        });

        jtadireccion.setColumns(20);
        jtadireccion.setRows(5);
        jScrollPane1.setViewportView(jtadireccion);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Municipio:");

        jtfcedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfcedulaKeyTyped(evt);
            }
        });

        jcbsexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Masculino", "Femenino" }));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fecha de nacimiento:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("sexo:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Correo Electrónico:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Ciudad:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Dirección:");

        jcbciudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbciudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbciudadItemStateChanged(evt);
            }
        });
        jcbciudad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbciudadActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cedula:");

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Imagen2.png"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 187, Short.MAX_VALUE)
        );

        mensajeci.setText("jLabel1");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel9))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jtfcedula, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jtfnombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mensajeci, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(293, 293, 293))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel14)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(26, 26, 26)
                                .addComponent(jcbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtfcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jdcnacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 725, Short.MAX_VALUE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcbciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(923, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfcedula)
                            .addComponent(mensajeci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jtfnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jtfapellido, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jcbsexo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jtfcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jdcnacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13)
                        .addComponent(jcbmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(jcbciudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(159, 159, 159))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel4)
                    .addGap(172, 172, 172)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(256, Short.MAX_VALUE)))
        );

        tabbed.addTab("Datos Personales", jPanel5);

        jPanel3.setBackground(new java.awt.Color(51, 0, 204));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre de usuario:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Contraseña:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Repetir Contraseña:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Año Escolar:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Sección:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Pregunta de Seguridad:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Respuesta de Seguridad:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Rol de Usuario:");

        jcbrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbseccion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jcbescolar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbescolar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbescolarItemStateChanged(evt);
            }
        });

        jtfusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtfusuarioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbps, 0, 266, Short.MAX_VALUE)
                            .addComponent(jtfrs)
                            .addComponent(jcbrol, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbescolar, 0, 197, Short.MAX_VALUE)
                            .addComponent(jcbseccion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jtfusuario)
                            .addComponent(jtfclave)
                            .addComponent(jtfrepetirclave))))
                .addContainerGap(613, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfrepetirclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbescolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbseccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbrol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(249, Short.MAX_VALUE))
        );

        tabbed.addTab("Datos de Usuario", jPanel3);

        btnregistrar.setText("Registrar");
        btnregistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnregistrarMouseClicked(evt);
            }
        });
        btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrarActionPerformed(evt);
            }
        });

        btnmodificar.setText("Modificar");
        btnmodificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnmodificarMouseClicked(evt);
            }
        });
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnlimpiar.setText("Limpiar");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });

        btnatras.setText("Atras");
        btnatras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnatrasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(495, Short.MAX_VALUE)
                .addComponent(btnregistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnmodificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btneliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnlimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnatras, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(321, 321, 321))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(tabbed, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnlimpiar)
                    .addComponent(btneliminar)
                    .addComponent(btnmodificar)
                    .addComponent(btnregistrar)
                    .addComponent(btnatras))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnregistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnregistrarMouseClicked
        int rolg = m.id_rol;
        if (rolg != 3) {// TODO add your handling code here:
            String rol = jcbrol.getSelectedItem().toString();
            if (rol.equals("Seleccione un Rol de Usuario")) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un rol de usuario", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rol.equals("Estudiante")) {
                    String usuario, clave, grado, seccion;
                    usuario = jtfusuario.getText();
                    clave = jtfclave.getText();
                    grado = jcbescolar.getSelectedItem().toString();
                    seccion = jcbseccion.getSelectedItem().toString();
                    int id_rol;

                    id_rol = 3;

                    if (usuario.equals("") || grado.equals("Seleccione un Grado Escolar") || seccion.equals("Seleccione una Sección")) {
                        JOptionPane.showMessageDialog(panelImage1, "Debe rellenar todos los campos", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {

                            int id_seccion = m.Select2("tbl_seccion", "*", "nb_seccion='" + seccion + "'").getInt("id_seccion");

                            boolean hecho = m.Insert("usuarios", "clave,tx_login,id_rol_usr,id_seccion", "'" + clave + "','" + usuario + "'," + id_rol + "," + id_seccion + "");
                            if (hecho == true) {
                                JOptionPane.showMessageDialog(null, "Registro realizado exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                                //colocar la pantalla con las condiciones iniciales
                            } else {
                                JOptionPane.showMessageDialog(null, "El registro no se pudo realizar", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    }
                
                if (rol.equals("Administrador")) {
                    String usuario, clave;
                    usuario = jtfusuario.getText();
                    clave = jtfclave.getText();
                    if (usuario.equals("")) {
                        JOptionPane.showMessageDialog(null, "Debe ingresar un nombre de usiario", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                    } else {
                       try{
                            int id_rol = m.Select2("tbl_rol_usr", "*", "nb_rol_usr='" + rol + "'").getInt("id_rol_usr");
                            boolean existe = m.Select3("usuarios", "*", "id_rol_usr=" + id_rol + "");
                            if (existe == true) {
                                JOptionPane.showMessageDialog(null, "Registro no realizado solo puede haber un administrador", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                                //colocar la pantalla con las condiciones iniciales
                            } else {
                                boolean ya=m.Insert("usuarios", "clave,tx_login,id_rol_usr,id_seccion", "'" + clave + "','" + usuario + "'," + id_rol + ",");
                                if(ya==true){
                                JOptionPane.showMessageDialog(null, "El registro no se pudo realizar", "Acción no Ejecutada", JOptionPane.INFORMATION_MESSAGE);    
                                }else{
                                 JOptionPane.showMessageDialog(null, "Registro realizado exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                               
                                }
                                
                                
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   
                    }
                }
     
                if (rol.equals("Maestro")) {
                    String usuario, clave;
                    usuario = jtfusuario.getText();
                    clave = jtfclave.getText();

                    int id_rol;

                    id_rol = 2;
                    if (usuario.equals("")) {
                        JOptionPane.showMessageDialog(panelImage1, "Debe ingresar un nombre de usiario", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                    } else {
                        boolean hecho = m.Insert("usuarios", "clave,tx_login,id_rol_usr", "'" + clave + "','" + usuario + "'," + id_rol + "");
                        if (hecho == true) {
                            JOptionPane.showMessageDialog(null, "Registro realizado exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                            //colocar la pantalla con las condiciones iniciales
                        } else {
                            JOptionPane.showMessageDialog(null, "El registro no se pudo realizar", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        
        }else{
            String direccion, cedula, nombre, apellido, sexo,correo, estado;
            cedula=jtfcedula.getText();
            nombre=jtfnombre.getText();
            apellido=jtfapellido.getText();
            sexo=(String) jcbsexo.getSelectedItem();
            correo=jtfcorreo.getText();
            estado=jcbestado.getSelectedItem().toString();
            Date fecha = jdcnacimiento.getDate();
            direccion=jtadireccion.getText();
            
            boolean esta=m.Select3("usuarios", "*", "cedula='"+cedula+"'");
            if (esta==true){
                JOptionPane.showMessageDialog(null, "numero de cedula ingresado ya completo su fase de registro.... VERIFIQUE SU NUMERO DE CEDULA ", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
            }
            else {
                if (nombre.equals("") || apellido.equals("") || sexo.equals("") || correo.equals("") || estado.equals("Selecione un estado") || jcbmunicipio.getSelectedItem().equals("Seleccionar municipio") || jcbciudad.getSelectedItem().equals("Selecione una ciudad") || jtfusuario.getText().equals("") || jtfclave.getText().equals("") || jtfrepetirclave.getText().equals("") || jcbps.getSelectedItem().equals("Selecione una pregunta")|| jtfrs.getText().equals("") ){
                JOptionPane.showMessageDialog(null, "todos los campos deben estar llenos ", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);          
                }
                   else{
                 try{
                 String  id_ciudad =m.Select2("tbl_ciudad", "*", "nb_ciudad ='" +jcbciudad.getSelectedItem().toString()+ "'").getString("id_ciudad");
      
                 
                boolean listo=m.Update("usuarios", "cedula='"+cedula+ "',nombre='"+nombre+"', apellido='"+apellido+"', sexo='"+sexo+"', fe_nacim='"+fecha+"', clave='"+jtfclave.getText()+"', tx_direccion='"+direccion+"',tx_login='"+jtfusuario.getText()+"',id_ciudad='"+id_ciudad+"',email='"+correo+"'", "id_usuario='"+m.id_usuario+"'");
                if(listo==true){
                 JOptionPane.showMessageDialog(null, "Registro realizado exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                               
                }    else{
                     JOptionPane.showMessageDialog(null, "El registro no se pudo realizar", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                        
                }
                
                }catch (SQLException ex) {
                            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }
           
            
            
            
            
       
            
        }

       
                


    }//GEN-LAST:event_btnregistrarMouseClicked

    private void btnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnregistrarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
   
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnatrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnatrasMouseClicked
        menu_principal mp = new menu_principal(m);
        mp.setVisible(true);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnatrasMouseClicked

    private void jtfusuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfusuarioKeyTyped
        // TODO add your handling code here:
        int rol = m.id_rol;
        if (rol != 3) {
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                String n_usuario = jtfusuario.getText();
                boolean existe = m.Select3("usuarios", "tx_login", "tx_login='" + n_usuario + "'");
                if (existe == true) {
                    JOptionPane.showMessageDialog(panelImage1, "Este nombre de usuario ya existe", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);

                } else {
                    btnregistrar.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jtfusuarioKeyTyped

    private void jcbescolarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbescolarItemStateChanged
        try {
            // TODO add your handling code here:
            if (inicio == 1) {
                Object escolar = jcbescolar.getSelectedItem();
                String escolar2 = escolar.toString();
                if (!escolar2.equals("Seleccione un Año Escolar")) {
                    String id_escolar = m.Select2("tbl_grado", "id_grado", "nb_grado='" + escolar2 + "'").getString("id_grado");
                    rellenar_seccion(id_escolar);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jcbescolarItemStateChanged

    private void jcbciudadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbciudadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbciudadActionPerformed

    private void jtfcedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfcedulaKeyTyped
        int rol = m.id_rol;
        if (rol != 3) { // TODO add your handling code here:
            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
                String cedula = jtfcedula.getText();
                
                boolean existe = m.Select3("usuarios", "*", "cedula='" + cedula + "'");
                if (existe == true) {
                    try {
                        jtfnombre.setText(m.Select2("usuarios", "*", "cedula='" + cedula + "'").getString("nombre"));
                        jtfapellido.setText(m.Select2("usuarios", "*", "cedula='" + cedula + "'").getString("apellido"));
                        jcbsexo.setSelectedItem(m.Select2("usuarios", "*", "cedula='" + cedula + "'").getString("sexo"));

                        //   SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                        Date fecha2;
                        String fechan = m.Select2("usuarios", "*", "cedula='" + cedula + "'").getString("fe_nacim");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        fecha2 = sdf.parse(fechan);

                        jdcnacimiento.setDate(fecha2);
                        jtfcorreo.setText(m.Select2("usuarios", "*", "cedula='" + cedula + "'").getString("email"));

                        String id_ciudad = m.Select2("usuarios", "*", "cedula='" + cedula + "'").getString("id_ciudad");
                        //jcbciudad.setSelectedItem(m.Select2("tbl_ciudad", "*", "id_ciudad=" + id_ciudad + "").getString("nb_ciudad"));

                        String id_municipio = m.Select2("tbl_ciudad", "*", "id_ciudad=" + id_ciudad + "").getString("id_municipio");
                        //jcbmunicipio.setSelectedItem(m.Select2("tbl_municipio", "*", "id_municipio=" + id_municipio + "").getString("nb_municipio"));

                        String id_estado = m.Select2("tbl_municipio", "*", "id_municipio=" + id_municipio + "").getString("id_estado");
                        String estado = m.Select2("tbl_estado", "nb_estado", "id_estado=" + id_estado + "").getString("nb_estado");
                        
                        //rellenar_estados();
                        //inicio=1;
                        jcbestado.setSelectedItem(estado);
                        jcbmunicipio.setSelectedItem(m.Select2("tbl_municipio", "*", "id_municipio=" + id_municipio + "").getString("nb_municipio"));
                        jcbciudad.setSelectedItem(m.Select2("tbl_ciudad", "*", "id_ciudad=" + id_ciudad + "").getString("nb_ciudad"));
                        jtadireccion.setText(m.Select2("usuarios", "*", "cedula='" + cedula + "'").getString("tx_direccion"));
                      /*  btnmodificar.setVisible(true);
                        jtfcedula.setEditable(false);
                        jtfnombre.setEditable(true);
                        jtfapellido.setEditable(true);
            jcbsexo.setEnabled(true);
            jdcnacimiento.setEnabled(true);
            jtfcorreo.setEditable(true);
            jcbmunicipio.setEnabled(true);
            jcbciudad.setEnabled(true);
            jtadireccion.setEnabled(true);
            jtfclave.setEditable(false);
            jtfrepetirclave.setEditable(false);
            jcbps.setEnabled(false);
            jtfrs.setEditable(false);
            btnregistrar.setVisible(false);
            btneliminar.setVisible(false);
            mensajeci.setVisible(true); 
            mensajeci.setText("Para obserbar el resto de los datos, seleccione datos de usuario");*/
            tabbed.setEnabledAt(1, true);
            m.cedulai=cedula;
            String nombre = m.Select2("usuarios", "*" ,"cedula='" + m.cedulai + "'").getString("tx_login");
            jtfusuario.setText(nombre);
             //jtfusuario.setEditable(false);
            rellenar_roles();
            String id_rol= m.Select2("usuarios", "*", "cedula='" + m.cedulai + "'").getString("id_rol_usr");
            jcbrol.setSelectedItem(m.Select2("tbl_rol_usr", "*", "id_rol_usr='" + id_rol + "'").getString("nb_rol_usr"));
            
                        //falta validar si los datos traidos son de un jugador, de un docente, o de un administrador para llenar sus respectivos
                        //campos en la pestaña de datos de usuario
                        //falta bloquear y desbloquear los campos que se pueden modificar
                        //  fechan = formatoDelTexto.parse(fechan);
                    } catch (SQLException ex) {
                        Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {//no esta registrado
                    tabbed.setSelectedIndex(1);
                     tabbed.setEnabledAt(1, true);
                    tabbed.setEnabledAt(0, false);

                    jtfclave.setText("12345");

                    jcbescolar.setEnabled(true);
                    jcbseccion.setEnabled(true);
                    jcbrol.setEnabled(true);

                    inicio = 1;
                    rellenar_estados();

                }

            }
        } 
    }//GEN-LAST:event_jtfcedulaKeyTyped

    private void jcbestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbestadoActionPerformed

    private void jcbestadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbestadoItemStateChanged

        try {
            if (inicio == 2) {
                inicio = 1;
                jcbciudad.removeAllItems();
            }
            if (inicio == 1) {

                Object estado = jcbestado.getSelectedItem();
                String estado2 = estado.toString();
                if (!estado2.equals("Seleccione un Estado")) {
                    String id_e = m.Select2("tbl_estado", "id_estado", "nb_estado='" + estado2 + "'").getString("id_estado");
                    rellenar_municipios(id_e);
                    inicio = 2;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jcbestadoItemStateChanged

    private void jcbciudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbciudadItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbciudadItemStateChanged

    private void jcbmunicipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbmunicipioItemStateChanged

        try {

            if (inicio == 2) {

                Object municipio = jcbmunicipio.getSelectedItem();
                String municipio2 = municipio.toString();

                if (!municipio2.equals("Seleccione un Municipio")) {
                    String id_m = m.Select2("tbl_municipio", "id_municipio", "nb_municipio='" + municipio2 + "'").getString("id_municipio");
                    rellenar_ciudades(id_m);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }    // TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_jcbmunicipioItemStateChanged

    private void btnmodificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnmodificarMouseClicked
     String rol = jcbrol.getSelectedItem().toString();
            if (rol.equals("Seleccione un Rol de Usuario")) {
                JOptionPane.showMessageDialog(panelImage1, "Debe seleccionar un rol de usuario", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
            } else {
                if (rol.equals("Estudiante")) {
                    String usuario, clave, grado, seccion,nombre,apellido,sexo, correo, direccion, estado, municipio, ciudad;
                    usuario = jtfusuario.getText();
                    clave = jtfclave.getText();
                    grado = jcbescolar.getSelectedItem().toString();
                    seccion = jcbseccion.getSelectedItem().toString();
                    nombre = jtfnombre.getText();
                    apellido = jtfapellido.getText();
                    sexo=jcbsexo.getSelectedItem().toString();
                    correo=jtfcorreo.getText();
                    direccion=jtadireccion.getText();
                    estado=jcbestado.getSelectedItem().toString();
                    municipio=jcbmunicipio.getSelectedItem().toString();
                    ciudad=jcbciudad.getSelectedItem().toString();
                    Date fecha2;
                        fecha2 = jdcnacimiento.getDate();
                    
                    int id_rol=3;
                    
                if(grado.equals("Seleccione un grado")|| seccion.equals("seleccione una seccion")){
                JOptionPane.showMessageDialog(panelImage1, "Debe seleccionar quiere cambiar el rol de este usuario a estudiante, debe ingresar el grado y seccion al q pertence", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);    
                }

                    if (usuario.equals("") || nombre.equals("") || seccion.equals("")|| apellido.equals("") || sexo.equals("") || direccion.equals("") || correo.equals("") || estado.equals("Seleccione un estado") || municipio.equals("Selecione un municipio") || ciudad.equals("") || seccion.equals("Seleccione una ciudad")) {
                        JOptionPane.showMessageDialog(panelImage1, "Debe rellenar todos los campos", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            String id_ciudad = m.Select2("usuarios", "*", "cedula='" + m.cedulai + "'").getString("id_ciudad");

                            int id_seccion = m.Select2("tbl_seccion", "*", "nb_seccion='" + seccion + "'").getInt("id_seccion");

                            boolean hecho = m.Update("usuarios", "nombre='"+nombre+"', apellido='"+apellido+"', sexo='"+sexo+"', apellido='"+apellido+"', id_rol_usr="+id_rol+", id_ciudad='"+id_ciudad+"',id_seccion='"+id_seccion+"email='"+correo+"'", "tx_login='"+usuario+"'");
                            if (hecho == true) {
                                JOptionPane.showMessageDialog(panelImage1, "Registro realizado exitosamente", "Acción Ejecutada", JOptionPane.INFORMATION_MESSAGE);
                                //colocar la pantalla con las condiciones iniciales
                            } else {
                                JOptionPane.showMessageDialog(panelImage1, "El registro no se pudo realizar", "Acción no Ejecutada", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(admin_usuario.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } 
                }
                }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnmodificarMouseClicked

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
            java.util.logging.Logger.getLogger(admin_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new admin_usuario(m).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnatras;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnregistrar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcbciudad;
    private javax.swing.JComboBox jcbescolar;
    private javax.swing.JComboBox jcbestado;
    private javax.swing.JComboBox jcbmunicipio;
    private javax.swing.JComboBox jcbps;
    private javax.swing.JComboBox jcbrol;
    private javax.swing.JComboBox jcbseccion;
    private javax.swing.JComboBox jcbsexo;
    private com.toedter.calendar.JDateChooser jdcnacimiento;
    private javax.swing.JTextArea jtadireccion;
    private javax.swing.JTextField jtfapellido;
    private javax.swing.JTextField jtfcedula;
    private javax.swing.JPasswordField jtfclave;
    private javax.swing.JTextField jtfcorreo;
    private javax.swing.JTextField jtfnombre;
    private javax.swing.JPasswordField jtfrepetirclave;
    private javax.swing.JTextField jtfrs;
    private javax.swing.JTextField jtfusuario;
    private javax.swing.JLabel mensajeci;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.tabbedPane.TabbedPaneHeader tabbed;
    // End of variables declaration//GEN-END:variables
}
