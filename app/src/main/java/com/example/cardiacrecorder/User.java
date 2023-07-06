package com.example.cardiacrecorder;
import java.util.ArrayList;

/**
 * Represents the user with necessary information for login and data storing
 */
public class User {
    String id;
    String name, email;
    String password;

    /**
     * Empty constructor for reading data from firebase
     */
    public User() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public String getPassword() {return password;}
    public void setPassword(String ps) {password = ps;}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
