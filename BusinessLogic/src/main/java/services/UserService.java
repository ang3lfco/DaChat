/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import model.User;
import dao.UserDAO;
/**
 *
 * @author martinez
 */
public class UserService {
    
    public void Register(User user){
        UserDAO userDAO = new UserDAO();
        userDAO.insert(user);
    }
    
    public User LoadUser(String phone){
        UserDAO userDAO = new UserDAO();
        return userDAO.findByPhone(phone);
    }
}
