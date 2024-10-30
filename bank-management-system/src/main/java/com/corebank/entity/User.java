
package com.corebank.entity;
import javax.persistence.*;

@Entity
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 101, allocationSize = 1)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private int userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

// ===================Constructor >> non parameterised or default constructor=================

    public User(){}

    // ========================== Paraneterised constructor=============================
    public User(String userName, String email, String password){
        super();

    this.userName = userName;
    this.email = email;
    this.password = password;    
    }

    // getter
    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // setters

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
                + "]";
    }
    
}
