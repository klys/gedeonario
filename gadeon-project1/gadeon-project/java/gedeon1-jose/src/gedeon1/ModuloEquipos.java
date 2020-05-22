/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gedeon1;


import base_dato.ManejadorSQL;
import base_dato.imagen;
import base_dato.validaciones;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Personal
 */
public class ModuloEquipos extends javax.swing.JFrame {
    
   
    /**
     * Creates new form ModuloEquipos
     */
    
    String ruta,name;
    String cedula,nombre,apellido,cargo,grado,direccion,telefono;
 
    int mfoto=0;
    String codigoModFoto;
    ManejadorSQL m;
    
    public ModuloEquipos() {
        initComponents();
        this.setLocationRelativeTo(null);
         validaciones v = new validaciones();
         m = new ManejadorSQL("postgres", "localhost", "freddy", "1234", "org.postgresql.Driver", "5432", "jdbc:postgresql", "2", v);
       //cargar imagenes
         
         imagen img = new imagen();
        img=m.cargarFotocrucifijo("1");
        
        
        Icon icon = new ImageIcon(img.getArchivo());
        pifoto.setIcon(icon);
       
    }
    
  
     
    
  
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        panelCurves1 = new org.edisoncor.gui.panel.PanelCurves();
        btnFoto = new org.edisoncor.gui.button.ButtonIpod();
        pifoto = new org.edisoncor.gui.panel.PanelImage();
        jt1 = new org.edisoncor.gui.textField.TextField();
        labelMetric3 = new org.edisoncor.gui.label.LabelMetric();
        labelMetric4 = new org.edisoncor.gui.label.LabelMetric();
        jt2 = new org.edisoncor.gui.textField.TextField();
        buttonAction1 = new org.edisoncor.gui.button.ButtonAction();

        setTitle("Módulo de Administración de Equipos");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gedeon.jpg"))); // NOI18N

        btnFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo3.png"))); // NOI18N
        btnFoto.setText("Buscar Foto");
        btnFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFotoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFotoMouseExited(evt);
            }
        });
        btnFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFotoActionPerformed(evt);
            }
        });

        pifoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        pifoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Imagen2.png"))); // NOI18N

        javax.swing.GroupLayout pifotoLayout = new javax.swing.GroupLayout(pifoto);
        pifoto.setLayout(pifotoLayout);
        pifotoLayout.setHorizontalGroup(
            pifotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 169, Short.MAX_VALUE)
        );
        pifotoLayout.setVerticalGroup(
            pifotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );

        jt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt1ActionPerformed(evt);
            }
        });

        labelMetric3.setBackground(new java.awt.Color(255, 255, 255));
        labelMetric3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric3.setText("Marca:");

        labelMetric4.setBackground(new java.awt.Color(255, 255, 255));
        labelMetric4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelMetric4.setText("Modelo:");

        buttonAction1.setText("buttonAction1");
        buttonAction1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAction1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCurves1Layout = new javax.swing.GroupLayout(panelCurves1);
        panelCurves1.setLayout(panelCurves1Layout);
        panelCurves1Layout.setHorizontalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jt1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addComponent(labelMetric4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
            .addGroup(panelCurves1Layout.createSequentialGroup()
                .addGap(667, 667, 667)
                .addComponent(pifoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
        );
        panelCurves1Layout.setVerticalGroup(
            panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurves1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(pifoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCurves1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMetric3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCurves1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelMetric4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnFoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(86, 86, 86)
                .addComponent(buttonAction1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addComponent(panelCurves1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFotoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFotoMouseEntered
        // TODO add your handling code here:
       

    }//GEN-LAST:event_btnFotoMouseEntered

    private void btnFotoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFotoMouseExited
        // TODO add your handling code here:
    

    }//GEN-LAST:event_btnFotoMouseExited

    private void btnFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFotoActionPerformed
        // TODO add your handling code here:

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter( new FileNameExtensionFilter("Archivo de Imagen","jpg") );
        fileChooser.setCurrentDirectory(new java.io.File("e:/imagenes_tmp/"));
        int result = fileChooser.showOpenDialog(null);
        if ( result == JFileChooser.APPROVE_OPTION ){
            ruta = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println(ruta);
            name = fileChooser.getSelectedFile().getName();
            System.out.println(name);
            ImageIcon imagen=new ImageIcon(ruta);
            pifoto.setIcon(imagen);
            if(btnFoto.getText().equals("Buscar Foto")){
                mfoto=1;
            }
            else{
                mfoto=3;
            }

        }

    }//GEN-LAST:event_btnFotoActionPerformed

    private void jt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

         if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente cerrar esta ventana?",
                "Salir del sistema", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
             this.setVisible(false);
        }// TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void buttonAction1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAction1ActionPerformed
        // TODO add your handling code here:
       
       
        System.out.println("n: "+name+" ruta: "+ruta+" gfhfgh"+jt1.getText()+" hg"+jt2.getText());
        m.guardarfotocrucifijo(name, ruta, jt1.getText(), jt2.getText());
    }//GEN-LAST:event_buttonAction1ActionPerformed

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
            java.util.logging.Logger.getLogger(ModuloEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModuloEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModuloEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModuloEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModuloEquipos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonIpod btnFoto;
    private org.edisoncor.gui.button.ButtonAction buttonAction1;
    private javax.swing.JPanel jPanel1;
    private org.edisoncor.gui.textField.TextField jt1;
    private org.edisoncor.gui.textField.TextField jt2;
    private org.edisoncor.gui.label.LabelMetric labelMetric3;
    private org.edisoncor.gui.label.LabelMetric labelMetric4;
    private org.edisoncor.gui.panel.PanelCurves panelCurves1;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private org.edisoncor.gui.panel.PanelImage pifoto;
    // End of variables declaration//GEN-END:variables
}
