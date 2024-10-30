package com.corebank;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.corebank.entity.Account;
import com.corebank.entity.Address;
import com.corebank.entity.Customer;
import com.corebank.entity.Nominee;
import com.corebank.entity.User;
import com.corebank.services.UserService;
import com.corebank.util.HibernateUtil;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserService userService = new UserService();
        boolean isLoggedIn = false;
        int userId = -1;
        while (true) {
            if (!isLoggedIn) {
                System.out.println("===================================");
                System.out.println("           Home page");
                System.out.println("===================================");
                System.out.println();

                System.out.println("---------------------------------------");
                System.out.println("             1. signup");
                System.out.println("             2. login");
                System.out.println("             3. Exit");
                System.out.println("---------------------------------------");
                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        userService.signup();
                        System.out.println("user registered successfully");
                        break;
                    case 2:
                        userId = userService.login();
                        isLoggedIn = (userId != -1);
                        System.out.println("user loggedin successfully");
                        System.out.println("the login user id is: " + userId);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("invalid choice!");
                        break;
                }
            } else {
                User user = userService.getUserById(userId);
                System.out.println("========================================");
                System.out.println("          Welcome, " + user.getUserName());
                System.out.println("========================================");

                System.out.println();
                System.out.println("---------------------------------------");
                System.out.println("       1. Create New Account");
                System.out.println("       2. Credit Amount");
                System.out.println("       3. Debit Amount");
                System.out.println("       4. Update Account");
                System.out.println("       5. View Account Details");
                System.out.println("       6. View All Accounts");
                System.out.println("       7. Close Account");
                System.out.println("       0. Log Out");
                System.out.println("---------------------------------------");
                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println();
                switch (choice) {
                    case 1:
                        Address address = new Address("india", "up", "gzb", "gzb", "201003", "loni", "jio tower", "51");
                        Nominee nominee = new Nominee("meenu", "sister", "1997", "123456");
                        Account account = new Account(1000000.0, "16/10/2024");

                        Customer customer = new Customer("dharmander", "dharm@gmail.com", address, "9310266755",
                                "27/09/2000", nominee, account);

                        account.setCustomer(customer);

                        
                        Transaction transaction;
                        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                            transaction = session.beginTransaction();
                            session.save(customer);
                            transaction.commit();
                            session.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        userId = userService.login();
                        isLoggedIn = (userId != -1);
                        System.out.println("user loggedin successfully");
                        System.out.println("the login user id is: " + userId);
                        break;
                    case 0:
                        isLoggedIn = false;
                        userId = -1;
                        break;
                    default:
                        System.out.println("invalid choice!");
                        break;
                }
            }
        }

        // sc.close();

    }

}
