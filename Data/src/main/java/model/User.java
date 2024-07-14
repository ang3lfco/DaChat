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
public class User {
    private ObjectId id;
    private String name;
    private String phone;
    private String password;
    private Address address;
    private Date birthdate;
    private String gender;
    private String picture;
    
    public User(ObjectId id, String name, String phone, String password, Address address, Date birthdate, String gender, String picture){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.birthdate = birthdate;
        this.gender = gender;
        this.picture = picture;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", password=" + password + ", address=" + address + ", birthdate=" + birthdate + ", gender=" + gender + ", picture=" + picture + '}';
    }
}