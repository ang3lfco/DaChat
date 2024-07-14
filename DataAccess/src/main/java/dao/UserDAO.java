/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.User;
import model.Address;
import connection.MongoConnection;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoCollection;
import org.bson.Document;



/**
 *
 * @author martinez
 */
public class UserDAO {
    
    public void insert(User user){
        Document userAddress = new Document()
                .append("street", user.getAddress().getStreet())
                .append("number", user.getAddress().getNumber())
                .append("zipcode", user.getAddress().getZipcode());
        
        Document userDoc = new Document()
                .append("_id", user.getId())
                .append("name", user.getPhone())
                .append("phone", user.getPhone())
                .append("password", user.getPassword())
                .append("address", userAddress)
                .append("birthdate", user.getBirthdate())
                .append("gender", user.getGender())
                .append("profile", user.getPicture());
        
        MongoClient mongoClient = MongoClients.create(MongoConnection.getConnection());
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase())
                .getCollection(MongoConnection.getUsersCollection());
        
        collection.insertOne(userDoc);
    }
    
    public User findByPhone(String phone){
        MongoClient mongoClient = MongoClients.create(MongoConnection.getConnection());
        MongoCollection<Document> collection = mongoClient.getDatabase(MongoConnection.getDatabase())
                .getCollection(MongoConnection.getUsersCollection());
        
        Document doc = new Document("phone", phone);
        MongoCursor<Document> cursor = collection.find(doc).iterator();
        
        if(cursor.hasNext()){
            Document userDoc = cursor.next();
            User user = new User(
                    userDoc.getObjectId("_id"),
                    userDoc.getString("name"),
                    userDoc.getString("phone"),
                    userDoc.getString("password"),
                    getAddress(userDoc),
                    userDoc.getDate("birthdate"),
                    userDoc.getString("gender"),
                    userDoc.getString("profile")
            );
            cursor.close();
            mongoClient.close();
            return user;
        }
        else{
            cursor.close();
            mongoClient.close();
            return null;
        }
    }
    
    public Address getAddress(Document userDoc){
        Document addressDoc = userDoc.get("address", Document.class);
        String street = addressDoc.getString("street");
        String number = addressDoc.getString("number");
        String zipcode = addressDoc.getString("zipcode");
        
        return new Address(street, number, zipcode);
    }
}
