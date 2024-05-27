/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

/**
 *
 * @author martinez
 */
public class MongoConnection {
    private static final String dbhost = "localhost";
    private static final int dbport = 27017;
    private static final String dbname = "dachat";
    
    private static final String usersCollection = "users";
    private static final String chatsCollection = "chats";
    private static final String messagesCollection = "messages";
    
    public static String getConnection(){
        return String.format("mongodb://%s:%d", dbhost, dbport);
    }
    
    public static String getDatabase(){
        return dbname;
    }
    
    public static String getUsersCollection(){
        return usersCollection;
    }
    
    public static String getChatsCollection(){
        return chatsCollection;
    }
    
    public static String getMessagesCollection(){
        return messagesCollection;
    }
}