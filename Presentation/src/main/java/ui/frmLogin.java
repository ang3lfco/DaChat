/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import auth.AuthService;
import javax.swing.JOptionPane;

/**
 * frmLogin class represents the login UI for DaChat app.
 * It manages essential components like user phone number and password.
 * 
 * @version 1.0
 * @since 2024-05-22
 * 
 * @author martinez
 */
public class frmLogin extends javax.swing.JFrame {

    /**
     * Creates new form frmLogin
     */
    public frmLogin() {
        initComponents();
        setLocationRelativeTo(null);
        
        /**
         * FocusListener added to the text fields containing the user phone number and password.
         * It shows a support message inside the fields when they are not focused.
         */
        txfPhone.addFocusListener(new FocusListener(){
            /**
             * When this text field gains the focus it clears the default text
             * to allow the user to type a phone number.
             * 
             * @param e the focus event
             */
            @Override
            public void focusGained(FocusEvent e){
                if(txfPhone.getText().equals("Enter your phone number")){
                    txfPhone.setText("");
                }
            }
            /**
             * When this text field loses focus it restores the default support messages if the field is empty
             * 
             * @param e the focus event
             */
            @Override
            public void focusLost(FocusEvent e){
                if(txfPhone.getText().isEmpty()){
                    txfPhone.setText("Enter your phone number");
                }
            }
        });
        
        pwfPassword.addFocusListener(new FocusListener(){
            /**
             * When this text field gains the focus it clears the default text
             * to allow the user to type a password.
             * 
             * @param e the focus event
             */
            @Override
            public void focusGained(FocusEvent e){
                if(pwfPassword.getText().equals("Type your password")){
                    pwfPassword.setText("");
                }
            }
            /**
             * When this text field loses focus it restores the default support messages if the field is empty
             * 
             * @param e the focus event
             */
            @Override
            public void focusLost(FocusEvent e){
                if(pwfPassword.getText().isEmpty()){
                    pwfPassword.setText("Type your password");
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        lblDaChatIcon = new javax.swing.JLabel();
        pnlPhone = new javax.swing.JPanel();
        lblPhone = new javax.swing.JLabel();
        txfPhone = new javax.swing.JTextField();
        pnlPassword = new javax.swing.JPanel();
        lblPassword = new javax.swing.JLabel();
        pwfPassword = new javax.swing.JPasswordField();
        lblLogin = new javax.swing.JLabel();
        lblCreateAccount = new javax.swing.JLabel();
        lblRecoveryPass = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLogin.setBackground(new java.awt.Color(255, 255, 255));

        lblDaChatIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DaChat.png"))); // NOI18N

        pnlPhone.setBackground(new java.awt.Color(53, 110, 242));

        lblPhone.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPhone.setForeground(new java.awt.Color(255, 255, 255));
        lblPhone.setText("Phone number:");

        txfPhone.setBackground(new java.awt.Color(51, 102, 255));
        txfPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txfPhone.setForeground(new java.awt.Color(255, 255, 255));
        txfPhone.setText("Enter your phone number");
        txfPhone.setBorder(null);

        javax.swing.GroupLayout pnlPhoneLayout = new javax.swing.GroupLayout(pnlPhone);
        pnlPhone.setLayout(pnlPhoneLayout);
        pnlPhoneLayout.setHorizontalGroup(
            pnlPhoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhoneLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlPhoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPhone)
                    .addComponent(txfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        pnlPhoneLayout.setVerticalGroup(
            pnlPhoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhoneLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblPhone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlPassword.setBackground(new java.awt.Color(53, 110, 242));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password:");

        pwfPassword.setBackground(new java.awt.Color(51, 102, 255));
        pwfPassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pwfPassword.setForeground(new java.awt.Color(255, 255, 255));
        pwfPassword.setText("Type your password");
        pwfPassword.setBorder(null);

        javax.swing.GroupLayout pnlPasswordLayout = new javax.swing.GroupLayout(pnlPassword);
        pnlPassword.setLayout(pnlPasswordLayout);
        pnlPasswordLayout.setHorizontalGroup(
            pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasswordLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(pwfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPasswordLayout.setVerticalGroup(
            pnlPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPasswordLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pwfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        lblLogin.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogin.setText("Login");
        lblLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLoginMouseClicked(evt);
            }
        });

        lblCreateAccount.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblCreateAccount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCreateAccount.setText("Create new account");
        lblCreateAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCreateAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCreateAccountMouseClicked(evt);
            }
        });

        lblRecoveryPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRecoveryPass.setText("Forgot password?");
        lblRecoveryPass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCreateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRecoveryPass, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pnlPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pnlPhone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31))))))
            .addComponent(lblLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDaChatIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addComponent(lblDaChatIcon)
                .addGap(18, 18, 18)
                .addComponent(pnlPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCreateAccount)
                .addGap(18, 18, 18)
                .addComponent(lblRecoveryPass)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLoginMouseClicked
        // TODO add your handling code here:
        String phone = txfPhone.getText();
        String password = pwfPassword.getText();
        
        AuthService auth = new AuthService();
        if(auth.login(phone, password)){
            JOptionPane.showMessageDialog(rootPane, "Welcome.");
            frmChatting chatting = new frmChatting(phone);
            chatting.setVisible(true);
            this.dispose();
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "Try Again.");
        }
    }//GEN-LAST:event_lblLoginMouseClicked

    private void lblCreateAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCreateAccountMouseClicked
        // TODO add your handling code here:
        frmSignUp signup = new frmSignUp();
        signup.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblCreateAccountMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmLogin().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCreateAccount;
    private javax.swing.JLabel lblDaChatIcon;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblRecoveryPass;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPanel pnlPassword;
    private javax.swing.JPanel pnlPhone;
    private javax.swing.JPasswordField pwfPassword;
    private javax.swing.JTextField txfPhone;
    // End of variables declaration//GEN-END:variables
}
