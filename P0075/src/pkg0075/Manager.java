package pkg0075;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;
import pkg0075.Account;
import pkg0075.validation;

public class Manager {

    private Scanner sc = new Scanner(System.in);

    public void login(ArrayList<Account> la) {
        if (la.isEmpty()) {
            System.err.println("Account empty.");
            return;
        }
        System.out.print("Enter username: ");
        String username = validation.checkInputString();
        System.out.print("Enter Password: ");
        String password = validation.checkInputString();
        Account accountLogin = findAccount(la, username, password);
        if (accountLogin != null) {
            System.out.println("Welcome");
            System.out.print("Hi " + accountLogin.getUsername()
                    + ", do you want to change password now? Y/N: ");
            changePassword(accountLogin);
        } else {
            System.err.println("Invalid username or password.");
        }
    }

    private void changePassword(Account accountLogin) {
        String choice;
        while (true) {
            choice = sc.nextLine().trim();
            if (choice.equalsIgnoreCase("Y") || choice.equalsIgnoreCase("N")) {
                break;
            } else {
                System.err.println("Re-input");
            }
        }
        if (choice.equalsIgnoreCase("Y")) {
            System.out.print("Old password: ");
            String oldPassword = validation.checkInputString();
            System.out.print("New password: ");
            String newPassword = validation.checkInputString();
            System.out.print("Renew password: ");
            String renewPassword = validation.checkInputString();
            if (!MD5Encryption(oldPassword).equalsIgnoreCase(accountLogin.getPassword())) {
                System.err.println("Old password incorrect.");
            } else if (!newPassword.equalsIgnoreCase(renewPassword)) {
                System.err.println("New password and Renew password not the same.");
            } else {
                accountLogin.setPassword(MD5Encryption(newPassword));
                System.out.println("Change password success");
            }
        }
    }

    public Account findAccount(ArrayList<Account> la, String username, String password) {
       for(Account account : la){
           if(username.equalsIgnoreCase(account.getUsername()) && MD5Encryption(password).equalsIgnoreCase(account.getPassword())){
               return account;
           }
       } return null;
       
       
       
       
       
    }
    
    
    

    public void addAccount(ArrayList<Account> la) {
        System.out.print("Enter Username: ");
        String username = validation.checkInputUsername(la);
        System.out.print("Enter Password: ");
        String password = validation.checkInputString();
        System.out.print("Enter Name: ");
        String name = validation.checkInputString();
        System.out.print("Enter Phone: ");
        String phone = validation.checkInputPhone();
        System.out.print("Enter email: ");
        String email = validation.checkInputEmail();
        System.out.print("Enter address: ");
        String address = validation.checkInputString();
        System.out.print("Enter Date Of Birth: ");
        String dateOfBirth = validation.checkInputDate();
        la.add(new Account(username, MD5Encryption(password), name, phone, email, address, dateOfBirth));
        System.out.println("Add success!!!");
    }

    private String MD5Encryption(String password) {
        try {
           MessageDigest m = MessageDigest.getInstance("MD5");
           
           m.update(password.getBytes());
           
           return DatatypeConverter.printHexBinary(m.digest()).toLowerCase();
            
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
}
