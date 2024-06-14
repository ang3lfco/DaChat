/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.util.ArrayList;
import model.Message;
import org.bson.Document;
import connection.MongoConnection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.types.ObjectId;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import java.util.Date;

/**
 *
 * @author martinez
 */
public class MessageDAO {
    
    public void Insert(Message message){
        Document messageDoc = new Document()
                .append("_id", message.getId())
                .append("id_chat", message.getIdChat())
                .append("id_sender", message.getIdSender())
                .append("message", message.getText())
                .append("timestamp", message.getTimestamp());
        
        MongoClient mongoClient = MongoClients.create(MongoConnection.getConnection());
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase())
                .getCollection(MongoConnection.getMessagesCollection());
        
        collection.insertOne(messageDoc);
    }
    
    public List<Message> getMessagesByChatId(ObjectId chatId){
        List<Message> messages = new ArrayList<>();
        MongoClient mongoClient = MongoClients.create(MongoConnection.getConnection());
        MongoCollection<Document> msgsCollection = mongoClient.getDatabase(MongoConnection.getDatabase())
                .getCollection(MongoConnection.getMessagesCollection());
        
        Document filter = new Document("id_chat", chatId);
        MongoCursor<Document> cursor = msgsCollection.find(filter).iterator();
        
        while(cursor.hasNext()){
            Document msgDoc = cursor.next();
            ObjectId id = msgDoc.getObjectId("_id");
            ObjectId idChat = msgDoc.getObjectId("id_chat");
            String idSender = msgDoc.getString("id_sender");
            String text = msgDoc.getString("message");
            Date timestamp = msgDoc.getDate("timestamp");
            
            Message msg = new Message(id, idChat, idSender, text, timestamp);
            messages.add(msg);
        }
        return messages;
    }
    
    public List<Message> getMessagesBetween(String phone1, String phone2){
        List<Message> messages = new ArrayList<>();
        
        MongoClient mongoClient = MongoClients.create(MongoConnection.getConnection());
        MongoCollection<Document> chatsCollection = mongoClient.getDatabase(MongoConnection.getDatabase())
                .getCollection(MongoConnection.getChatsCollection());
        
        Document filter = new Document("participants", new Document("$all", List.of(phone1, phone2)));
        Document chatResult = chatsCollection.find(filter).first();
        
        if(chatResult != null){
            ObjectId resultId = chatResult.getObjectId("_id");
            
            MongoCollection<Document> msgsCollection = mongoClient.getDatabase(MongoConnection.getDatabase())
                    .getCollection(MongoConnection.getMessagesCollection());
            
            List<Document> msgsDocuments = msgsCollection.find(Filters.eq("id_chat", resultId))
                    .sort(Sorts.ascending("timestamp"))
                    .into(new ArrayList<>());
            
            for(Document msgs : msgsDocuments){
                ObjectId id = msgs.getObjectId("_id");
                ObjectId idChat = msgs.getObjectId("id_chat");
                String idSender = msgs.getString("id_sender");
                String text = msgs.getString("message");
                Date timestamp = msgs.getDate("timestamp");
                
                Message message = new Message(id, idChat, idSender, text, timestamp);
                messages.add(message);
            }
        }
        return messages;
    }
}
