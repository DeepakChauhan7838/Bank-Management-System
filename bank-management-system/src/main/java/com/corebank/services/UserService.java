package com.corebank.services;

import java.util.Scanner;

import com.corebank.dao.UserDao;
import com.corebank.entity.User;

public class UserService {

    private UserDao userDao;
    private Scanner sc;

    public UserService(){
        this.userDao = new UserDao();
        this.sc = new  Scanner(System.in);
    }
    
public void signup(){


    System.out.println("============================================================");
    System.out.println("                      SignUp                           ");
    System.out.println("============================================================");

    System.out.println();
    System.out.print("Enter username: ");
    String username = sc.nextLine();

    System.out.println("Enter Your email: ");
    String email = sc.nextLine();

    System.out.println("Create Your Password: ");
    String password = sc.nextLine();

    User user = new User(username, email, password);
    userDao.saveUser(user);
}


public int login(){
    System.out.println("============================================================");
    System.out.println("                      Login                           ");
    System.out.println("============================================================");

    System.out.println();
    System.out.println("Enter Your email: ");
    String email = sc.nextLine();

    System.out.println("Create Your Password: ");
    String password = sc.nextLine();

    User user = userDao.getUserByEmailAndPassword(email, password);
    if(user==null){
        return -1;
    }
    System.out.println("You are logged in");
    return user.getUserId();
}

public User getUserById(int id){
    return userDao.getUserByUserId(id);
}

}
