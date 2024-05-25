/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class Message {
    private ObjectId id;
    private ObjectId IdChat;
    private String idSender;
    private String text;
    private Date timestamp;
    
    public Message(ObjectId id, ObjectId idChat, String idSender, String text, Date timestamp){
        this.id = id;
        this.IdChat = idChat;
        this.idSender = idSender;
        this.text = text;
        this.timestamp = timestamp;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getIdChat() {
        return IdChat;
    }

    public void setIdChat(ObjectId IdChat) {
        this.IdChat = IdChat;
    }

    public String getIdSender() {
        return idSender;
    }

    public void setIdSender(String idSender) {
        this.idSender = idSender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
