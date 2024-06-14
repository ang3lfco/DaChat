/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Chat;
import java.util.List;
import java.util.ArrayList;
import connection.MongoConnection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class ChatDAO {
    
    public List<Chat> findByPhone(String phone){
        MongoClient mongoClient = MongoClients.create(MongoConnection.getConnection());
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase())
                .getCollection(MongoConnection.getChatsCollection());
        
        List<Chat> chats = new ArrayList<>();
        MongoCursor<Document> cursor = collection.find(Filters.eq("participants", phone)).iterator();
        
        while(cursor.hasNext()){
            Document chatDoc = cursor.next();
            
            ObjectId id = chatDoc.getObjectId("_id");
            String name = chatDoc.getString("name");
            String miniature = chatDoc.getString("miniature");
            List<String> participants = (List<String>) chatDoc.get("participants");
            
            Chat chat = new Chat(id, name, miniature, participants);
            chats.add(chat);
        }
        cursor.close();
        mongoClient.close();
        return chats;
    }
}
