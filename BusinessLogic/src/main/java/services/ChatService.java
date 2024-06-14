/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import java.util.List;
import model.Chat;
import dao.ChatDAO;

/**
 *
 * @author martinez
 */
public class ChatService {
    
    public List<Chat> LoadChats(String phone){
        ChatDAO chats = new ChatDAO();
        return chats.findByPhone(phone);
    }
}
