/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd_avanzada;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Usuario
 */
public class Usuarios extends javax.swing.JFrame {

    Archivos a;
    ManejadorSQL m;
    String[] lineas;
    private String usu;
    String[] muni = new String[2];
    String[] cuid = new String[2];

    /**
     * Creates new form Usuarios
     */
    public Usuarios() {
        try {
            initComponents();
            String c_est, c_mun, c_ciu, id1;
            this.setLocationRelativeTo(null);
            String[] datos = new String[2];
            a = new Archivos();
            lineas = new String[9];
            lineas = a.Leer();
            m = new ManejadorSQL(lineas[2], lineas[1], lineas[3], lineas[4], lineas[5], lineas[7], lineas[6]);
            m.setLineas(lineas);
            ResultSet b_esta = m.Select("tblsit_estado order by nb_estado", "id_estado,nb_estado");//Seleccionamos los estados para el combo box
            while (b_esta.next()) {
                datos[0] = b_esta.getString(1);
                datos[1] = b_esta.getString(2);
                u_c_estado.addItem(datos[1]); // agregamos los estados
                //System.out.println(datos[0]);
            }
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();// TODO add your handling code here:
            modeloCombo.addElement("Seleccionar");
            u_ciudad.setModel(modeloCombo);
            u_c_municipio.setModel(modeloCombo);
            
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
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
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        u_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        u_apellido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        u_longi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        u_clave = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        u_cedula = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        u_telefono = new javax.swing.JComboBox();
        u_telefonos = new javax.swing.JTextField();
        u_rol = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        u_parroquia = new javax.swing.JTextField();
        u_urbanizacion = new javax.swing.JComboBox();
        u_nombre__sector_urbanizacion = new javax.swing.JTextField();
        u_calle_avenida = new javax.swing.JTextField();
        u_c_municipio = new javax.swing.JComboBox();
        u_c_estado = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        u_aceptar = new org.edisoncor.gui.button.ButtonTask();
        u_confirmar = new javax.swing.JTextField();
        u_cancelar = new org.edisoncor.gui.button.ButtonTask();
        most_e = new javax.swing.JLabel();
        u_ciudad = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 50;
        gridBagConstraints.ipadx = 849;
        gridBagConstraints.ipady = 48;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(panelCurves1, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Registro de Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 128, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(36, 29, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 112;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(34, 31, 0, 0);
        jPanel1.add(u_nombre, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Apellido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 29, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 112;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 31, 0, 0);
        jPanel1.add(u_apellido, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Longi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 8, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 31;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 91, 0, 0);
        jPanel1.add(u_longi, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Clave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 8, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 31;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(16, 91, 0, 0);
        jPanel1.add(u_clave, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("C.I/Rif");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 29, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 112;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 31, 0, 0);
        jPanel1.add(u_cedula, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Telefono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 29, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setText("Rol de usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(162, 29, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        u_telefono.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "0416", "0426", "0424", "0412", "0269" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 31, 0, 0);
        jPanel1.add(u_telefono, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 111;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 18, 0, 0);
        jPanel1.add(u_telefonos, gridBagConstraints);

        u_rol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 27;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(160, 120, 11, 0);
        jPanel1.add(u_rol, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Ciudad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 29, 0, 0);
        jPanel1.add(jLabel10, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Parroquia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 27, 0, 0);
        jPanel1.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Sector/Urbanizacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(25, 75, 0, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 8, 0, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("Calle/Avenida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(28, 8, 0, 0);
        jPanel1.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Municipio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 29, 0, 0);
        jPanel1.add(jLabel15, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Estado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 29, 0, 0);
        jPanel1.add(jLabel16, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 112;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 31, 0, 0);
        jPanel1.add(u_parroquia, gridBagConstraints);

        u_urbanizacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Sector", "Urbanizacion" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 31;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(23, 91, 0, 0);
        jPanel1.add(u_urbanizacion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 31;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 91, 0, 0);
        jPanel1.add(u_nombre__sector_urbanizacion, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 31;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(26, 91, 0, 0);
        jPanel1.add(u_calle_avenida, gridBagConstraints);

        u_c_municipio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        u_c_municipio.setToolTipText("");
        u_c_municipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                u_c_municipioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 31, 0, 0);
        jPanel1.add(u_c_municipio, gridBagConstraints);

        u_c_estado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        u_c_estado.setToolTipText("Seleccionar");
        u_c_estado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                u_c_estadoMouseClicked(evt);
            }
        });
        u_c_estado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                u_c_estadoItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 31, 0, 0);
        jPanel1.add(u_c_estado, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setText("Confirmar Clave");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 14;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.gridwidth = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 8, 0, 0);
        jPanel1.add(jLabel17, gridBagConstraints);

        u_aceptar.setText("  ");
        u_aceptar.setDescription("Aceptar");
        u_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_aceptarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 35;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 25, 0, 0);
        jPanel1.add(u_aceptar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 31;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 105;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 91, 0, 0);
        jPanel1.add(u_confirmar, gridBagConstraints);

        u_cancelar.setText("  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 16;
        gridBagConstraints.gridy = 23;
        gridBagConstraints.gridwidth = 16;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 2, 0, 0);
        jPanel1.add(u_cancelar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridwidth = 36;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 373;
        gridBagConstraints.ipady = 33;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 21, 0, 0);
        jPanel1.add(most_e, gridBagConstraints);

        u_ciudad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        u_ciudad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                u_ciudadItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 21;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 39;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 31, 0, 0);
        jPanel1.add(u_ciudad, gridBagConstraints);

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void u_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_aceptarActionPerformed
        String u_nomb, u_ns, u_ape, u_log, u_cel, u_ced, u_cal, u_sec, u_par, u_ciu, u_mun, u_est, u_c_pos, u_con, u_res, u_pre, u_c_cel, u_r_con;
        u_nomb = u_nombre.getText();
        u_ape = u_apellido.getText();
        u_log = u_longi.getText();
        u_ced = u_cedula.getText();
        u_cel = u_telefonos.getText();
        u_c_cel = u_telefono.getSelectedItem().toString();
        u_sec = u_urbanizacion.getSelectedItem().toString();
        u_con = u_clave.getText();
        u_r_con = u_confirmar.getText();
        u_par = u_parroquia.getText();
        u_ciu = u_ciudad.getSelectedItem().toString();
        u_mun = u_c_municipio.getSelectedItem().toString();
        u_est = u_c_estado.getSelectedItem().toString();
        u_cal = u_calle_avenida.getText();
        u_ns = u_nombre__sector_urbanizacion.getText();

        if (u_nomb.equals("") || u_ape.equals("") || u_log.equals("") || u_ced.equals("") || u_cel.equals("") || u_c_cel.equals("") || u_sec.equals("") || u_par.equals("") || u_ciu.equals("") || u_mun.equals("") || u_est.equals("")) {
            mostrar_e("Por favor llenar todos los campos que se le solicitan");


    }//GEN-LAST:event_u_aceptarActionPerformed
    else {
            boolean resul = m.Select3("usuario", "tx_login", "tx_login='" + u_log + "'");
            if (resul == true) {
                mostrar_e("Error, El login solicitado no está disponible, intente con Otro ");
            } else {
                if (!u_con.equals(u_r_con)) {

                    mostrar_e("Las Contraseñas que esta ingresando no coinciden entre si. Por favor verifique");

                } else {
                    //try {
                    boolean ing_u = false;
                    boolean ing_dire = false;
                    boolean ing_tele = false;
                    // boolean ing_status = false;
                    //ing_dire = m.Insert("direccion", "tx_urbanizacion_sector,tx_calle_avenida,tx_parroquia, tx_nombre_u_s", "'" + u_sec + "','" + u_cal + "','" + u_par + "','" + u_ns + "'");
                    //ing_status = m.Insert("status", "nom_status,tx_status", "'" + u_nomb + "','" + "Activo" + "'");
                    ing_tele = m.Insert("telefono", "num_tlf,tx_tipo", "'" + u_cel + "','" + u_c_cel + "'");
//ing_tele=m.Insert("telefono", "id_tlf,id_usuario,num_tlf,tx_tipo,id_integrantes", "'"+"','"+"'"+u_cel+"','"+2+"[");
                    try {
                        //consulta de la ciudad el idt

                        String id_estado = m.Select2("tblsit_estado", "*", "nb_estado='" + u_est + "'").getString("id_estado");
                        String id_municipio = m.Select2("tblsit_municipio", "*", "nb_municipio='" + u_mun + "' and id_estado=" + id_estado + "").getString("id_municipio");
                        String id_ciudad = m.Select2("tblsit_ciudad", "*", "nb_ciudad='" + u_ciu + "' and id_municipio=" + id_municipio + "").getString("id_ciudad");
                        System.out.print(id_ciudad);
                        ing_dire = m.Insert("direccion", "tx_urbanizacion_sector,tx_calle_avenida,tx_parroquia, tx_nombre_u_s,id_ciudad", "'" + u_sec + "','" + u_cal + "','" + u_par + "','" + u_ns + "','" + id_ciudad + "'");
                        String id_dire = m.Select2("direccion", "*", "id_ciudad='" + id_ciudad + "'").getString("id_direccion");

//                        System.out.print(id_tlf);
                        String id_tlf = m.Select2("telefono", "*", "num_tlf='" + u_cel + "'").getString("id_tlf");
                        System.out.print(id_tlf);
                        String status = m.Select2("status", "*", "nom_status='Inactivo' and tx_status='Usuario'").getString("id_status");
                        ing_u = m.Insert("usuario", "tx_nombre,tx_apellido,tx_login,tx_clave,ci_rif,id_direccion,id_status", "'" + u_nomb + "','" + u_ape + "','" + u_log + "','" + u_con + "','" + u_ced + "','" + id_dire + "'," + status + "");

                        //String id_u=m.Select2("usuario", "*","tx_nombre='"+u_nomb+"'").getString("id_usuario");
                        //System.out.print(id_u);   //String id_ciu = m.Select("tblsit_ciudad", "id_ciudad","nb_ciudad='" + u_ciu + "'").getString("id_ciudad");
                        //ing_dire=m.Insert("direcion", "tx_urbanizacion_sector,tx_calle_avenida,tx_parroquia,tx_nombre_u_s",u_sec+"','"+u_cal+"'"+u_par+"'");
                        // cargar_ciudad(id_ciu);
                    } catch (SQLException ex) {
                        Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (ing_dire = true) {
                        mostrar_e("Registrado");
                    } else {
                        mostrar_e("Error");
                    }
                    //ing_u=m.Insert("usuario", "", u_res)
                    //mostrar_e("Correcto");
                    //}// catch (SQLException ex) {
                    // Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                    // }
                }
            }
        }

    }

    public void mostrar_e(String e) {
        most_e.setAlignmentY(CENTER_ALIGNMENT);
        most_e.setHorizontalAlignment(WIDTH / 2);
        most_e.setAlignmentX(CENTER_ALIGNMENT);
        most_e.setText(e);
        most_e.setForeground(Color.blue);
    }

    private void u_c_estadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_u_c_estadoMouseClicked
        // u_c_estado.removeItem("Estado");
        //u_c_municipio  // TODO add your handling code here:
    }//GEN-LAST:event_u_c_estadoMouseClicked

    private void u_c_estadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_u_c_estadoItemStateChanged

       String seleccion = u_c_estado.getSelectedItem().toString();
        if (!seleccion.equals("Seleccionar")) {
            try {
                String id = m.Select2("tblsit_estado", "id_estado", "nb_estado='" + seleccion + "'").getString("id_estado");
                
                DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();// TODO add your handling code here:
            modeloCombo.removeAllElements();
            modeloCombo.addElement("Seleccionar");
            u_ciudad.setModel(modeloCombo);
            u_c_municipio.setModel(modeloCombo);
            cargar_municipio(id);
                //u_c_municipio.addItem("Seleccionar");
            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }

           
    }//GEN-LAST:event_u_c_estadoItemStateChanged
        else {
            //u_c_municipio.setSelectedItem("Seleccione");
        }
       
    }
    
    private void u_ciudadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_u_ciudadItemStateChanged
      /*  String seleccion = u_ciudad.getSelectedItem().toString();
        if (!seleccion.equals("Seleccionar")) {
            try {
                String id = m.Select2("tblsit_ciudad", "id_ciudad", "nb_ciudad='" + seleccion + "'").getString("id_ciudad"); // voy aqui
                cargar_ciudad(id);

            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }  // TODO add your handling code here:
        }
         /*/
        // TODO add your handling code here:
    }//GEN-LAST:event_u_ciudadItemStateChanged
/*/public void cargar_muni1(){
String seleccion = u_c_estado.getSelectedItem().toString();
        if (!seleccion.equals("Seleccionar")) {
            try {
                int id = m.Select2("tblsit_estado", "id_estado", "nb_estado='" + seleccion + "'").getInt("id_estado"); // voy aqui
                ResultSet b_esta = m.Select2("tblsit_municipio", "id_municipio,nb_municipio", "id_estado=" + id + "");//Seleccionamos los estados para el combo box
            while (b_esta.next()) {
                 muni[0] = b_esta.getString(1);
                 muni[1] = b_esta.getString(2);
                u_c_estado.addItem(muni[1]); // agregamos los estados
                //System.out.println(datos[0]);
            }
                //cargar_ciudad(id);

            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }  // TODO add your handling code here:
        }
        else{
        u_c_municipio.setSelectedItem("Seleccionar");
        }
}/*/
    private void u_c_municipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_u_c_municipioItemStateChanged

        String seleccion = u_c_municipio.getSelectedItem().toString();
        if (!seleccion.equals("Seleccionar")) {
            try {
                String idm = m.Select2("tblsit_municipio", "id_municipio", "nb_municipio='" + seleccion + "'").getString("id_municipio"); // voy aqui
                cargar_ciudad(idm);

            } catch (SQLException ex) {
                Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
            }  // TODO add your handling code here:
    }//GEN-LAST:event_u_c_municipioItemStateChanged
       
    }

    public void cargar_municipio(String id) {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            try (ResultSet rs = m.Select("tblsit_municipio", "*", "id_estado='" + id + "' order by nb_municipio")) {

                while (rs.next()) {
                    modeloCombo.addElement(rs.getObject("nb_municipio"));
                }
            }
            u_c_municipio.setModel(modeloCombo);
            //u_c_municipio.setSelectedItem("Seleccionar");
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void cargar_ciudad(String idm) {
        try {
            DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
            try (ResultSet rs = m.Select("tblsit_ciudad", "*", "id_municipio='" + idm + "'")) {
                while (rs.next()) {
                    modeloCombo.addElement(rs.getObject("nb_ciudad"));
                }
            }
            u_ciudad.setModel(modeloCombo);
        } catch (SQLException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel most_e;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.button.ButtonTask u_aceptar;
    private javax.swing.JTextField u_apellido;
    private javax.swing.JComboBox u_c_estado;
    private javax.swing.JComboBox u_c_municipio;
    private javax.swing.JTextField u_calle_avenida;
    private org.edisoncor.gui.button.ButtonTask u_cancelar;
    private javax.swing.JTextField u_cedula;
    private javax.swing.JComboBox u_ciudad;
    private javax.swing.JTextField u_clave;
    private javax.swing.JTextField u_confirmar;
    private javax.swing.JTextField u_longi;
    private javax.swing.JTextField u_nombre;
    private javax.swing.JTextField u_nombre__sector_urbanizacion;
    private javax.swing.JTextField u_parroquia;
    private javax.swing.JComboBox u_rol;
    private javax.swing.JComboBox u_telefono;
    private javax.swing.JTextField u_telefonos;
    private javax.swing.JComboBox u_urbanizacion;
    // End of variables declaration//GEN-END:variables
}
