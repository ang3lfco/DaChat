/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import model.Chat;
import model.Message;
import services.ChatService;

import java.util.List;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import services.UserService;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle;
import org.bson.types.ObjectId;
import services.MessageService;

/**
 * frmChatting represents the Chatting UI for DaChat app.
 * It manages the main functionalities of messaging and chatting all over this application.
 * 
 * @version 1.0
 * @since 2024-05-22
 * 
 * @author martinez
 */
public class frmChatting extends javax.swing.JFrame {
    private String phone;
    private List<Chat> chats;
    private ChatService chatService;
    private List<JPanel> panels;
    private ObjectId selectedChatId;
    /**
     * Creates new form frmChatting
     * @param phone
     */
    public frmChatting(String phone) {
        initComponents();
        setLocationRelativeTo(null);
        
        this.chatService = new ChatService();
        this.phone = phone;
        this.chats = chatService.LoadChats(phone);
        panels = new ArrayList<>();
        jspMessages.getVerticalScrollBar().setUnitIncrement(16);
        
        if(!chats.isEmpty()){
            for(Chat chat : chats){
                
                JPanel chatPanel = new JPanel();
                chatPanel.setBackground(new Color(0, 51, 102));
                chatPanel.setPreferredSize(new Dimension(211,70));
                
                String friendPhone = "";
                for(String participant : chat.getParticipants()){
                    if(!participant.equals(phone)){
                        friendPhone = participant;
                    }
                }
                UserService userService = new UserService();
                
                JLabel chatText1 = new JLabel(userService.LoadUser(friendPhone).getName());
                JLabel chatText2 = new JLabel(chat.getParticipants().toString());
                Font sogoeBold12 = new Font("Segoe UI", Font.BOLD, 12);
                Font sogoePlain12 = new Font("Sogoe UI", Font.PLAIN, 12);
                chatText1.setFont(sogoeBold12);
                chatText1.setForeground(Color.WHITE);
                chatText2.setFont(sogoePlain12);
                chatText2.setForeground(Color.WHITE);
                
                GroupLayout layout = new GroupLayout(chatPanel);
                chatPanel.setLayout(layout);
                
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
//                                .addComponent(null) //icon
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(chatText1)
                                        .addComponent(chatText2)))
                );
                layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
//                                        .addComponent(null) //icon
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(chatText1)
                                                .addComponent(chatText2)))
                                .addGap(0,0, Short.MAX_VALUE))
                );
                
                chatPanel.addMouseListener(new MouseAdapter(){
                    @Override
                    public void mouseClicked(MouseEvent e){
                        selectedChatId = chat.getId();
                        String phone1 = chat.getParticipants().get(0);
                        String phone2 = chat.getParticipants().get(1);
                        
                        MessageService msgService = new MessageService();
                        List<Message> messages = msgService.LoadMessages(phone1, phone2);
                        showMessages(messages);
                    }
                });
                panel.add(chatPanel);
                panels.add(chatPanel);
            }
            panel.updateUI();
        }
        
        txfSearch.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String searchedText = txfSearch.getText().trim();
                if(!searchedText.isEmpty()){
                    boolean found = false;
                    for(JPanel panel : panels){
                        Component[] components = panel.getComponents();
                        JLabel chatText1 = null;
                        JLabel chatText2 = null;
                        if(components.length >= 3){
                            chatText1 = (JLabel) components[1];
                            chatText2 = (JLabel) components[2];
                        }
                        if(chatText1 != null && chatText2 != null){
                            String title = chatText1.getText().trim();
                            String description = chatText2.getText().trim();
                            if(title.toLowerCase().contains(searchedText.toLowerCase()) || description.toLowerCase().contains(searchedText.toLowerCase())){
                                panel.setVisible(true);
                                found = true;
                            }
                            else{
                                panel.setVisible(false);
                            }
                        }
                    }
                    if(!found){
                            JOptionPane.showMessageDialog(rootPane, "No matches found.");
                    }
                }
                else{
                    for(JPanel panel : panels){
                        panel.setVisible(true);
                    }
                }
            }
        });
    }
    
    
    private void showMessages(List<Message> messages){
        messagesPanel.removeAll();
        messagesPanel.setForeground(Color.WHITE);
//        txaMessages.setText("");
//        txaMessages.setForeground(Color.WHITE);
        Font sogoeBold12 = new Font("Sogoe UI", Font.BOLD, 12);
//        txaMessages.setFont(sogoeBold12);
        
        UserService userService = new UserService();
        for(Message message : messages){
            String senderName = userService.LoadUser(message.getIdSender()).getName();
            String timestamp = message.getTimestamp().toString().substring(0,20) + message.getTimestamp().toString().substring(24);
            JPanel messagePanel = createMessagePanel(timestamp, senderName, message.getText());
            messagesPanel.add(messagePanel);
//            txaMessages.add(messagePanel);
//            txaMessages.append("(" + timestamp + ") " + senderName + " says: " + message.getText() + "\n");
        }
        messagesPanel.revalidate();
        messagesPanel.repaint();
    }
    
    private JPanel createMessagePanel(String timestamp, String senderName, String messageText) {
        JPanel messagePanel = new JPanel();
        messagePanel.setBackground(new Color(53, 110, 242));
        messagePanel.setPreferredSize(new Dimension(400, 100));
        messagePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel lblTimestamp = new JLabel(timestamp);
        lblTimestamp.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTimestamp.setForeground(Color.WHITE);

        JLabel lblSender = new JLabel(senderName + " says:");
        lblSender.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSender.setForeground(Color.WHITE);

        JLabel lblMessage = new JLabel("<html>" + messageText.replace("\n", "<br>") + "</html>");
        lblMessage.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblMessage.setForeground(Color.WHITE);

        GroupLayout layout = new GroupLayout(messagePanel);
        messagePanel.setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblTimestamp)
                .addComponent(lblSender)
                .addComponent(lblMessage)
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTimestamp)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSender)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage)
        );

        return messagePanel;
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
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblProfile = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txfSearch = new javax.swing.JTextField();
        jspChats = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblSend = new javax.swing.JLabel();
        jspMessages = new javax.swing.JScrollPane();
        messagesPanel = new javax.swing.JPanel();
        jspMessage = new javax.swing.JScrollPane();
        txfMessage = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(53, 110, 242));

        lblProfile.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profile.png"))); // NOI18N
        lblProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProfileMouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chatting.png"))); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/settings.png"))); // NOI18N

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left-arrow.png"))); // NOI18N
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new-message.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblBack)
                .addContainerGap())
            .addComponent(lblProfile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBack)
                .addGap(158, 158, 158)
                .addComponent(lblProfile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txfSearch.setBackground(new java.awt.Color(53, 110, 242));
        txfSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txfSearch.setForeground(new java.awt.Color(255, 255, 255));
        txfSearch.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txfSearch.setText("Search");

        panel.setBackground(new java.awt.Color(53, 110, 242));
        panel.setForeground(new java.awt.Color(255, 255, 255));
        panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        panel.setLayout(new java.awt.GridLayout(1000, 1));
        jspChats.setViewportView(panel);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jspChats)
                    .addComponent(txfSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jspChats)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblSend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/send4.png"))); // NOI18N
        lblSend.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSendMouseClicked(evt);
            }
        });

        messagesPanel.setLayout(new java.awt.GridLayout(1000, 1));
        jspMessages.setViewportView(messagesPanel);

        txfMessage.setBackground(new java.awt.Color(53, 110, 242));
        txfMessage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txfMessage.setForeground(new java.awt.Color(255, 255, 255));
        txfMessage.setBorder(null);
        jspMessage.setViewportView(txfMessage);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspMessages)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jspMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblSend)
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jspMessages, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jspMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblSend)
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        frmLogin login = new frmLogin();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblBackMouseClicked

    private void lblProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProfileMouseClicked
        // TODO add your handling code here:
        frmProfile profile = new frmProfile(phone);
        profile.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_lblProfileMouseClicked

    private void lblSendMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSendMouseClicked
        // TODO add your handling code here:
        String message = txfMessage.getText();
        if(message != null){
            Message newMessage = new Message(new ObjectId(), selectedChatId, phone, message, new Date());
            MessageService messageService = new MessageService();
            messageService.SendMessage(newMessage);
            showMessages(messageService.LoadChatMessages(selectedChatId));
            txfMessage.setText("");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "The message could not be sent. Make sure it isn't empty.");
        }
    }//GEN-LAST:event_lblSendMouseClicked

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
//            java.util.logging.Logger.getLogger(frmChatting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(frmChatting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(frmChatting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(frmChatting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new frmChatting().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jspChats;
    private javax.swing.JScrollPane jspMessage;
    private javax.swing.JScrollPane jspMessages;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblSend;
    private javax.swing.JPanel messagesPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txfMessage;
    private javax.swing.JTextField txfSearch;
    // End of variables declaration//GEN-END:variables
}
