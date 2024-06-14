/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author martinez
 */
public class Chat {
    private ObjectId id;
    private String name;
    private String miniature;
    private List<String> participants;
    
    public Chat(ObjectId id, String name, String miniature, List<String> participants){
        this.id = id;
        this.name = name;
        this.miniature = miniature;
        this.participants = participants;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiniature() {
        return miniature;
    }

    public void setMiniature(String miniature) {
        this.miniature = miniature;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Chat{" + "id=" + id + ", name=" + name + ", miniature=" + miniature + ", participants=" + participants + '}';
    }
}
