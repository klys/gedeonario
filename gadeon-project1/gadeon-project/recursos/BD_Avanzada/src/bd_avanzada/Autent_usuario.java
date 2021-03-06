/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bd_avanzada;

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultEditorKit;

/**
 *
 * @author BERLIN COELLO
 */
public class Autent_usuario extends javax.swing.JFrame {

    ManejadorSQL m;
    Archivos a;
    String[] lineas;

    /**
     * Creates new form Autent_usuario
     */
    public Autent_usuario() {
        try {
            initComponents();
            this.setLocationRelativeTo(null);
            a = new Archivos();
            lineas = new String[9];
            lineas = a.Leer();
            m = new ManejadorSQL(lineas[2], lineas[1], lineas[3], lineas[4], lineas[5], lineas[7], lineas[6]);
            m.setLineas(lineas);
        } catch (Exception ex) {
            Logger.getLogger(Autent_usuario.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usua = new javax.swing.JTextField();
        cont = new javax.swing.JTextField();
        most_e = new javax.swing.JLabel();
        ing_usu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(102, 255, 102));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Usuario");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contraseña");

        usua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usuaKeyTyped(evt);
            }
        });

        ing_usu.setText("Ingresar");
        ing_usu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ing_usuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usua, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(201, 201, 201)
                        .addComponent(ing_usu)))
                .addContainerGap(135, Short.MAX_VALUE))
            .addComponent(most_e, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(most_e, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(ing_usu)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ing_usuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ing_usuActionPerformed
        String usu, con;
        usu = usua.getText();
        con = cont.getText();
        // si estan vacios uno de los campos
        if ((usu.equals("")) || con.equals("")) {

            mostrar_e("Debe llenar todos los Campos");


        } else {
            //si usuario existe
            boolean resul = m.Select3("usuario", "p_usuario", "tx_login='" + usu + "'");
            if (resul == false) {
                mostrar_e("El Usuario solicitado no esta Registrado en base de Datos ");

            } else {
                //con = m.getStringMessageDigest(con, "MD5");//encriptamos clave
                boolean cla = m.Select3("usuario", "id_usuario", "tx_clave='" + con + "' and tx_login='" + usu + "'");// verificamos su existencia
                if (cla == false) {
                    try {
                        //verificamos si bloquea, 1 si bloquea y 0 si no.
                        String con_int = m.SelectUltimo2("config_clave").getString("in_bloq");
                        if (con_int.equals("1")) {// si bloquea
                            int inten = m.SelectUltimo2("config_clave").getInt("num_intentos");//tomamos valor 
                            String id_usu = m.Select("usuario", "tx_login='" + usu + "'").getString("id_usuario");//Seleccionamos id para comparar el historial de clave
                            int hist = m.SelectUltimo("historial_clave", "id_usuario=" + id_usu + "").getInt("tx_errado");//obtenemos numero de errores
                            hist = hist + 1;
                            if (hist >= inten) {// si alcanzó intentos de errores
                                mostrar_e("Su contraseña ha sido bloqueada, comuníquese con el administrador general");

                                String ver_est = m.Select("status", "nom_status='Bloqueado' and tx_status='Usuario'").getString("id_status");//verificamos estatus bloqueado de usuario;
                                String ver_est_cont = m.Select("status", "nom_status='Bloqueada' and tx_status='Clave'").getString("id_status");//verificamos estatus bloqueada de contraseña;
                                //actualizamos estatus de usuario por el bloqueo
                                m.Update("usuario", "id_status=" + ver_est + "", "id_usuario=" + id_usu);


                                int id_hist = m.SelectUltimo("historial_clave", "id_usuario=" + id_usu + "").getInt("id_hist_clave");//obtenemos numero de errores
                                //actualizamos el estatus de clave por el bloqueo
                                m.Update("historial_clave", "id_status=" + ver_est_cont + "", "id_hist_clave=" + id_hist);
                            } else {
                                //mostramos intentos restantes
                                mostrar_e("Su contraseña su contraseña bloquea en: " + (inten - hist) + " intentos");

                                int id_hist = m.SelectUltimo("historial_clave", "id_usuario=" + id_usu + "").getInt("id_hist_clave");//obtenemos numero de errores
                                //actualizamos el estatus de clave por el bloqueo
                                m.Update("historial_clave", "tx_errados=" + hist + "", "id_hist_clave=" + id_hist);//actualizamos numero de intentos

                            }
                        } else {
                            mostrar_e("Clave Incorrecta");

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Autent_usuario.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    try {
                      String id_usu = m.Select("usuario", "tx_login='" + usu + "'").getString("id_status");//Seleccionamos id para comparar el historial de clave
                        int d = Integer.parseInt(id_usu);//lo tranforma a intero
                        System.out.print(d);
                        /*String status = m.Select("status", "id_status='" + id_usu + "'").getString("nom_status");//Seleccionamos id para comparar el estatus
                        if (status.equals("Bloqueado")) {
                            mostrar_e("Usuario Bloqueado");

                        }
                        if (status.equals("Eliminado")) {
                            mostrar_e("Usuario Inválido");

                        }
                        if (status.equals("Inactivo") || status.equals("Vencido")) {
                            //OJO: llamar a cambio de clave
                        }
                        if (status.equals("Activo")) {
                            //verificamos si clave vence o no 
                            String con_int = m.SelectUltimo2("config_clave").getString("in_vence");
                            if (con_int.equals("1")) {//si vence
                                //obtenemos la fecha del sistema (actual)
                                String f_sis = m.fecha_sistema();
                                // verificamos fecha de vencimiento en la historial de clave
                                int hist = m.SelectUltimo("historial_clave", "id_usuario=" + id_usu + "").getInt("fech_registro");
                                //convertir  fecha a formato fecha y validar
                            }
                        }/*/

                    } catch (SQLException ex) {
                        Logger.getLogger(Autent_usuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }


            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_ing_usuActionPerformed

    private void usuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuaKeyTyped
       if(usua.getText().length()>=60){
          evt.consume();
          getToolkit().beep();
      }  // TODO add your handling code here:
    }//GEN-LAST:event_usuaKeyTyped
    public void mostrar_e(String e) {
        most_e.setAlignmentY(CENTER_ALIGNMENT);
        most_e.setHorizontalAlignment(WIDTH / 2);
        most_e.setAlignmentX(CENTER_ALIGNMENT);
        most_e.setText(e);
        most_e.setForeground(Color.red);
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
            java.util.logging.Logger.getLogger(Autent_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Autent_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Autent_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Autent_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Autent_usuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cont;
    private javax.swing.JButton ing_usu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel most_e;
    private javax.swing.JTextField usua;
    // End of variables declaration//GEN-END:variables
}
