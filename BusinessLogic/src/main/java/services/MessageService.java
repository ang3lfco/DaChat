/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dao.MessageDAO;
import java.util.List;
import model.Message;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class MessageService {
    
    public void SendMessage(Message message){
        MessageDAO msgDAO = new MessageDAO();
        msgDAO.Insert(message);
    }
    
    public List<Message> LoadMessages(String phone1, String phone2){
        MessageDAO msgDAO = new MessageDAO();
        return msgDAO.getMessagesBetween(phone1, phone2);
    }
    
    public List<Message> LoadChatMessages(ObjectId chatId){
        MessageDAO msgDAO = new MessageDAO();
        return msgDAO.getMessagesByChatId(chatId);
    }
}
