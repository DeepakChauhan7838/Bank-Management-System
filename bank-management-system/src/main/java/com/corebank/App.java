package com.corebank;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

import org.hibernate.type.LocalDateType;

import com.corebank.entity.Transactions;

import com.corebank.entity.Account;
import com.corebank.entity.Address;
import com.corebank.entity.Customer;
import com.corebank.entity.Nominee;
import com.corebank.entity.Admin;
import com.corebank.services.AccountService;
import com.corebank.services.AdminService;
import com.corebank.services.CustomerService;
import com.corebank.services.TransactionService;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminService adminService = new AdminService();
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        boolean isAdminLoggedIn = false;
        int adminId = -1;

        while (true) {
            if (!isAdminLoggedIn) {
                System.out.println("===================================");
                System.out.println("           Admin Home Page         ");
                System.out.println("===================================");
                System.out.println();

                System.out.println("---------------------------------------");
                System.out.println("             1. Admin Sign Up");
                System.out.println("             2. Admin Login");
                System.out.println("             3. Exit");
                System.out.println("---------------------------------------");
                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        adminService.signup();
                        System.out.println("Admin registered successfully");
                        break;
                    case 2:
                        adminId = adminService.login();
                        isAdminLoggedIn = (adminId != -1);
                        if (isAdminLoggedIn) {
                            System.out.println("Admin logged in successfully");
                            System.out.println("The logged-in admin ID is: " + adminId);
                        }
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            } else {
                Admin admin = adminService.getAdminById(adminId);
                System.out.println("========================================");
                System.out.println("          Welcome, " + admin.getName());
                System.out.println("========================================");

                System.out.println();
                System.out.println("---------------------------------------");
                System.out.println("       1. Manage Customers");
                System.out.println("       2. View All Admins");
                System.out.println("       3. Manage Account");
                System.out.println("       4. Logout");
                System.out.println("---------------------------------------");
                System.out.print("Enter Choice: ");
                int adminChoice = sc.nextInt();
                sc.nextLine();
                System.out.println();

                switch (adminChoice) {
                    case 1:
                        manageCustomers(sc, customerService);
                        break;
                    case 2:
                        System.out.println("=====================================");
                        for (Admin employee : adminService.getAllAdmins()) {
                            System.out.println(" name: " + employee.getName() + " email: " + employee.getEmail());
                        }
                        System.out.println("=====================2================");
                        break;
                    case 3:
                        manageAccount(sc, accountService);
                        break;
                    case 4:
                        isAdminLoggedIn = false;
                        adminId = -1;
                        System.out.println("Logged out successfully.");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }
        }
    }

    private static void manageCustomers(Scanner sc, CustomerService customerService) {
        boolean isCustomerManagementActive = true;

        while (isCustomerManagementActive) {
            System.out.println("========================================");
            System.out.println("         Customer Management Menu       ");
            System.out.println("========================================");
            System.out.println();
            System.out.println("1. Add New Customer");
            System.out.println("2. View All Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            // System.out.println("5. Manage the Account of Customer");
            System.out.println("0. Back");
            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.println();

            switch (choice) {
                case 1:
                    addNewCustomer(sc, customerService);
                    break;
                case 2:
                    customerService.getAllCustomers().forEach(System.out::println);
                    break;
                case 3:
                    updateCustomer(sc, customerService);
                    break;
                case 4:
                    deleteCustomer(sc, customerService);
                    break;
                // case 5:
                // manageAccount(sc, accountService);
                // break;
                case 0:
                    isCustomerManagementActive = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }
        }
    }

    private static void addNewCustomer(Scanner sc, CustomerService customerService) {
        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = sc.nextLine();

        System.out.print("Enter address ( street,city,state, zip): ");
        String[] addressParts = sc.nextLine().split(", ");
        Address address = new Address(addressParts[0], addressParts[1], addressParts[2], addressParts[3]);

        System.out.print("Enter nominee name: ");
        String nomineeName = sc.nextLine();
        System.out.print("Enter nominee relation: ");
        String nomineeRelation = sc.nextLine();
        System.out.print("Enter nominee DOB: ");
        String nomineeDob = sc.nextLine();
        System.out.print("Enter nominee phone number: ");
        String nomineePhoneNumber = sc.nextLine();

        Account account = new Account("saving", BigDecimal.ZERO, new Date()); // Implement actual creation date logic
        Nominee nominee = new Nominee(nomineeName, nomineeRelation, nomineeDob, nomineePhoneNumber, account);

        Customer customer = new Customer(name, email, address, phoneNumber, nominee, new HashSet<>());
        customer.addAccount(account);
        nominee.setAccount(account);
        account.setCustomer(customer);
        customerService.addCustomer(customer);
        account.setAccountNo("" + 230020 + account.getAccountId());
        AccountService accountService = new AccountService();
        accountService.updateAccount(account);
        System.out.println("Customer added successfully!");
    }

    private static void updateCustomer(Scanner sc, CustomerService customerService) {
        System.out.print("Enter customer ID to update: ");
        long customerId = sc.nextLong();
        sc.nextLine(); // Consume the newline

        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter new name (or press Enter to keep unchanged): ");
        String newName = sc.nextLine();
        if (!newName.isEmpty()) {
            customer.setName(newName);
        }

        System.out.print("Enter new email (or press Enter to keep unchanged): ");
        String newEmail = sc.nextLine();
        if (!newEmail.isEmpty()) {
            customer.setEmail(newEmail);
        }

        customerService.updateCustomer(customer);
        System.out.println("Customer updated successfully!");
    }

    private static void deleteCustomer(Scanner sc, CustomerService customerService) {
        System.out.print("Enter customer ID to delete: ");
        long customerId = sc.nextLong();
        sc.nextLine();
        customerService.deleteCustomer(customerId);
        System.out.println("Customer deleted successfully!");
    }

    private static void manageAccount(Scanner sc, AccountService accountService) {

        TransactionService transactionService = new TransactionService();
        boolean isAccountManagementActive = true;
        System.out.println();
        System.out.print("Enter a Account Number: ");
        String accountNumber = sc.nextLine();
        Account account = accountService.getAccountByAccountNo(accountNumber);
        if (account == null) {
            System.out.println("=======================");
            System.out.println("    NO ACCOUNT FOUND!  ");
            System.out.println("=======================");
        } else {
            while (isAccountManagementActive) {
                System.out.println("========================================");
                System.out.println("    Customer Account Management Menu    ");
                System.out.println("========================================");
                System.out.println();
                // System.out.println("1. Open New Account");
                System.out.println("1. View Account Details");
                System.out.println("2. Update Account");
                System.out.println("3. Delete Account");
                System.out.println("4. Deposit");
                System.out.println("5. Withdraw");
                System.out.println("6. Transfer");
                System.out.println("0. Logout");
                System.out.print("Enter Choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println();

                switch (choice) {
                    case 1:
                        // addNewCustomer(sc, customerService);
                        System.out.println("==================================");
                        System.out.println("        Account Details: ");
                        System.out.println("==================================");
                        System.out.println();
                        System.out.println("   Account Number: " + account.getAccountNo());
                        System.out.println("   Account Type: " + account.getAccountType());
                        System.out.println("   Balance: " + account.getBalance());
                        System.out.println("   Account Holder Name: " + account.getCustomer().getName());
                        System.out.println();
                        System.out.println("==================================");
                        break;
                    case 2:
                        // customerService.getAllCustomers().forEach(System.out::println);
                        break;
                    case 3:
                        // updateCustomer(sc, customerService);
                        break;
                    case 4:
                        System.out.println("============================");
                        System.out.println();
                        System.out.println("Enter the Amount to be Deposit: ");
                        BigDecimal depositAmount = sc.nextBigDecimal();
                        account.setBalance(account.getBalance().add(depositAmount));
                        accountService.updateAccount(account);
                        Account fromAccount = new Account();
                        fromAccount.setAccountNo("cash");
                        Transactions transactions = new Transactions("cash", depositAmount, new Date(), fromAccount,
                                account);
                        transactionService.saveTransaction(transactions);
                        System.out.println("Amount Deposited Succesfully! ");
                        System.out.println("Current Balance is: " + account.getBalance());
                        System.out.println();
                        System.out.println("============================");
                        break;
                    case 5:
                        System.out.println("============================");
                        System.out.println();
                        System.out.println("Enter the Amount to be Withdraw: ");
                        BigDecimal withdrawAmount = sc.nextBigDecimal();
                        account.setBalance(account.getBalance().subtract(withdrawAmount));
                        Account toAccount = new Account();
                        account.setAccountNo("cash");
                        accountService.updateAccount(account);
                        Transactions withdrawTransactions = new Transactions("cash", withdrawAmount, new Date(), account,
                                toAccount);
                        transactionService.saveTransaction(withdrawTransactions);
                        System.out.println("Amount Withdraw Succesfully! ");
                        System.out.println("Current Balance is: " + account.getBalance());
                        System.out.println();
                        System.out.println("============================");
                        break;
                    case 6:
                        System.out.println("============================");
                        System.out.println();
                        System.out.println("Enter the Receiver Amount Number : ");
                        String receiverAccountNo = sc.nextLine();
                        Account receiverAccount = accountService.getAccountByAccountNo(receiverAccountNo);

                        if (receiverAccount == null) {
                            System.out.println("=========================");
                            System.out.println("     INVALID ACCOUNT!    ");
                            System.out.println("=========================");
                        } else {

                            System.out.println("Enter the Amount to be Withdraw: ");
                            BigDecimal transferAmount = sc.nextBigDecimal();
                            account.setBalance(account.getBalance().subtract(transferAmount));
                            accountService.updateAccount(account);
                            receiverAccount.setBalance(account.getBalance().add(transferAmount));
                            accountService.updateAccount(receiverAccount);
                            Transactions transferTransactions = new Transactions("Bank Transfer", transferAmount, new Date(),
                                    account, receiverAccount);
                            transactionService.saveTransaction(transferTransactions);
                            System.out.println("Amount Transferr Succesfully! ");
                            System.out.println("Current Balance is: " + account.getBalance());
                            System.out.println();
                            System.out.println("============================");
                        }

                        break;
                    case 0:
                        isAccountManagementActive = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }
        }
    }

}
