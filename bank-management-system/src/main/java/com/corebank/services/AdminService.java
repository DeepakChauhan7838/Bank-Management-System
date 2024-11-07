package com.corebank.services;

import java.util.List;
import java.util.Scanner;

import com.corebank.dao.AdminDao;
import com.corebank.entity.Admin;

public class AdminService {

    private AdminDao adminDao;
    private Scanner sc;

    public AdminService() {
        this.adminDao = new AdminDao();
        this.sc = new Scanner(System.in);
    }

    public void signup() {
        System.out.println("============================================================");
        System.out.println("                          SignUp                           ");
        System.out.println("============================================================");

        System.out.println();
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Create your password: ");
        String password = sc.nextLine();

        System.out.print("Enter role: ");
        String role = sc.nextLine();

        Admin admin = new Admin(name, password, email, role);
        adminDao.saveAdmin(admin);
        System.out.println("Admin created successfully!");
    }

    public int login() {
        System.out.println("============================================================");
        System.out.println("                          Login                           ");
        System.out.println("============================================================");

        System.out.println();
        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        Admin admin = adminDao.getAdminByEmailAndPassword(email, password);

        if (admin == null) {
            System.out.println("Invalid email or password. Please try again.");
            return -1;
        }

        System.out.println("You are logged in as: " + admin.getName());
        return admin.getAdminId().intValue();
    }

    public Admin getAdminById(int id) {
        return adminDao.getAdminById((long) id);
    }

    public List<Admin> getAllAdmins(){
         return adminDao.getAllAdmins();
    } 
}
