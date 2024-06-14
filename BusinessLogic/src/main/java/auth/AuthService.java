/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package auth;

import dao.UserDAO;
import model.User;

/**
 *
 * @author martinez
 */
public class AuthService {
    
    public boolean login(String phone, String password){
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByPhone(phone);
        return user != null && user.getPassword().equals(password);
    }
}
