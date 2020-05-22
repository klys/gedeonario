/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gedeon1;

import ahorcado.inicioJuego;
import base_dato.ManejadorSQL;
import javax.swing.ImageIcon;
import millonario.gedeonario;

/**
 *
 * @author usuario
 */
public class menu_juego extends javax.swing.JFrame {
ManejadorSQL m;
 
    /**
     * Creates new form menu_juego
     */
    public menu_juego(ManejadorSQL m2) {
        m = m2;
        initComponents();
         ImageIcon img = new ImageIcon(getClass().getResource(Gedeon1.icono));
        this.setIconImage(img.getImage());
   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelImage1 = new org.edisoncor.gui.panel.PanelImage();
        buttonAqua1 = new org.edisoncor.gui.button.ButtonAqua();
        buttonAqua4 = new org.edisoncor.gui.button.ButtonAqua();
        buttonAqua5 = new org.edisoncor.gui.button.ButtonAqua();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gedeon.jpg"))); // NOI18N

        buttonAqua1.setBackground(new java.awt.Color(0, 0, 0));
        buttonAqua1.setForeground(new java.awt.Color(204, 204, 0));
        buttonAqua1.setText("JUGAR Pala___tras");
        buttonAqua1.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        buttonAqua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAqua1ActionPerformed(evt);
            }
        });

        buttonAqua4.setBackground(new java.awt.Color(0, 0, 0));
        buttonAqua4.setForeground(new java.awt.Color(204, 204, 0));
        buttonAqua4.setText("Atras");
        buttonAqua4.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        buttonAqua4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAqua4ActionPerformed(evt);
            }
        });

        buttonAqua5.setBackground(new java.awt.Color(0, 0, 0));
        buttonAqua5.setForeground(new java.awt.Color(204, 204, 0));
        buttonAqua5.setText("JUGAR Gedeonario");
        buttonAqua5.setFont(new java.awt.Font("Georgia", 3, 18)); // NOI18N
        buttonAqua5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAqua5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(buttonAqua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196)
                .addComponent(buttonAqua5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAqua4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(490, 490, 490))
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImage1Layout.createSequentialGroup()
                .addGap(25, 382, Short.MAX_VALUE)
                .addGroup(panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAqua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAqua5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImage1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonAqua4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelImage1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAqua5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAqua5ActionPerformed
        // jugar gedeonario
        gedeonario ven = new gedeonario();
        ven.setLocationRelativeTo(null);
        ven.setVisible(true);
    }//GEN-LAST:event_buttonAqua5ActionPerformed

    private void buttonAqua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAqua1ActionPerformed
        // nuevo juego Pala___Letras
        inicioJuego ven = new inicioJuego();
        ven.setLocationRelativeTo(null);
        ven.setVisible(true);
    }//GEN-LAST:event_buttonAqua1ActionPerformed

    private void buttonAqua4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAqua4ActionPerformed
        // volver a inicio
        this.dispose();
        Gedeon1.iniciar_sistema();
    }//GEN-LAST:event_buttonAqua4ActionPerformed

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
            java.util.logging.Logger.getLogger(menu_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_juego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_juego(m).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAqua buttonAqua1;
    private org.edisoncor.gui.button.ButtonAqua buttonAqua4;
    private org.edisoncor.gui.button.ButtonAqua buttonAqua5;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    // End of variables declaration//GEN-END:variables
}