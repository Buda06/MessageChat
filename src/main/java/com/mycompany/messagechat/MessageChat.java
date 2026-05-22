/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.messagechat;

/**
 *
 * @author Student
 */
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class MessageChat {
    
    private static final Scanner keyboard = new Scanner(System.in);
    private static String firstName, lastName, username, password, cellNumber;
    
    // Completely independent state counter
    private static int sentCount = 0;

    public static void main(String[] args) {
        System.out.println("REGISTRATION");
        System.out.println("-------------");
        
        getUserPersonalInfo();
        username = validateUsername();
        password = validatePassword();
        cellNumber = validatePhone();
        
        LoginClass myAccount = new LoginClass(username, password, firstName, lastName);
        
        System.out.println("\nLOGIN");
        System.out.println("-------------");
        performLogin(myAccount);
        
        keyboard.close();
    }
    
    private static void getUserPersonalInfo() {
        System.out.print("Enter First Name: ");
        firstName = keyboard.nextLine();
        System.out.print("Enter Last Name: ");
        lastName = keyboard.nextLine();
    }
    
    private static String validateUsername() {
        System.out.print("Enter Username : ");
        String input = keyboard.nextLine();
        if (input.contains("_") && input.length() <= 5) {
            System.out.println("Username successfully captured.");
            return input;
        } else {
            System.out.println("Username is not correctly formatted...");
            return input;
        }
    }
    
    private static String validatePassword() {
        System.out.print("Enter Password : ");
        String input = keyboard.nextLine();
        if (isPasswordStrong(input)) {
            System.out.println("Password successfully captured.");
            return input;
        } else {
            System.out.println("Password is not correctly formatted...");
            return input;
        }
    }
    
    private static String validatePhone() {
        System.out.print("Enter Cell Number (+27): ");
        String input = keyboard.nextLine();
        if (input != null && input.matches("^\\+27[0-9]{9}$")) {
            System.out.println("Cell phone number successfully added.");
            return input;
        } else {
            System.out.println("Cell phone number incorrectly formatted...");
            return input;
        }
    }
    
    private static boolean isPasswordStrong(String pwd) {
        if (pwd.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : pwd.toCharArray()) {
            if (c >= 'A' && c <= 'Z') hasUpper = true;
            else if (c >= '0' && c <= '9') hasDigit = true;
            else if (!(c >= 'a' && c <= 'z')) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }
    
    private static void performLogin(LoginClass account) {
        System.out.print("Enter username: ");
        String enteredUser = keyboard.nextLine();
        System.out.print("Enter password: ");
        String enteredPass = keyboard.nextLine();
        
        boolean success = account.loginUser(enteredUser, enteredPass);
        System.out.println(account.returnLoginStatus(success));
        
        if (success) {
            System.out.println("\nWelcome to QuickChat.");
            displayUserDashboard();
        }
    }
    
    private static void displayUserDashboard() {
        int menuSelection;
        do {
            System.out.println("\n=== QUICKCHAT MENU ===");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Logout");
            System.out.print("Choose option: ");
            menuSelection = keyboard.nextInt();
            keyboard.nextLine(); 

            if (menuSelection == 1) {
                processMessaging();
            } else if (menuSelection == 2) {
                System.out.println("Coming Soon");
            }
        } while (menuSelection != 3);
    }
    
    private static void processMessaging() {
        sentCount = 0; 
        
        System.out.print("\nHow many messages do you want to send? ");
        int totalIterations = keyboard.nextInt();
        keyboard.nextLine(); 
        
        for (int i = 1; i <= totalIterations; i++) {
            System.out.println("\n--- Message " + i + " ---");
            System.out.print("Enter recipient(+27): ");
            String recCell = keyboard.nextLine();
            System.out.print("Enter message(max 250): ");
            String txt = keyboard.nextLine();
            
            if (txt == null) txt = "";
            
            // Standard object initialization
            Message msg = new Message(i, recCell, txt);
            
            // Evaluates using localized standard utility logic mapping
            if (recCell != null && recCell.matches("^\\+27[0-9]{9}$")) {
                System.out.println("Cell phone number successfully captured.");
                System.out.println("\nSelect an action:");
                System.out.println("1. Send");
                System.out.println("2. Disregard");
                System.out.println("3. Store");
                System.out.print("Choice: ");
                
                int action = keyboard.nextInt();
                keyboard.nextLine(); 
                
                if (action == 1) {
                    sentCount++;
                    System.out.println("Message Sent");
                    System.out.println("ID: " + msg.getMessageId());
                    System.out.println("Hash: " + msg.createMessageHash());
                    System.out.println("Recipient: " + msg.getRecCell());
                    System.out.println("Message: " + msg.getTxt());
                } else if (action == 2) {
                    System.out.println("Press 0 to delete the message.");
                } else if (action == 3) {
                    sentCount++;
                    
                }
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }
        System.out.println("\nTotal messages sent in this session: " + sentCount);
    }
    private static void messageToJsonFile(String jsonContent) {
        try (FileWriter fileOut = new FileWriter("messages.json", true)) {
            fileOut.write(jsonContent + "\n");
            System.out.println("Message successfully stored.");
        } catch (IOException error) {
            System.out.println("Error storing JSON: " + error.getMessage());
        }
    }
    
}
