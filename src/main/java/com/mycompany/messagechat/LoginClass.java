/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messagechat;

/**
 *
 * @author Student
 */
public class LoginClass {

    // User credentials storage
    private String loginUsername;
    private String loginPassword;
    private String userFirstName;
    private String userLastName;

    // Constructor
    public LoginClass(String username, String password, String firstName, String lastName) {
        this.loginUsername = username;
        this.loginPassword = password;
        this.userFirstName = firstName;
        this.userLastName = lastName;
    }

    public boolean checkUserName() {
        return loginUsername.contains("_") && loginUsername.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        boolean hasUppercase = false;
        boolean hasNumber = false;
        boolean hasSpecialChar = false;
        
        for (int index = 0; index < loginPassword.length(); index++) {
            char currentChar = loginPassword.charAt(index);
            if (Character.isUpperCase(currentChar)) hasUppercase = true;
            else if (Character.isDigit(currentChar)) hasNumber = true;
            else if (!Character.isLetterOrDigit(currentChar)) hasSpecialChar = true;
        }
        
        return loginPassword.length() >= 8 && hasUppercase && hasNumber && hasSpecialChar;
    }

    // Reference: Pattern from W3Schools Java Regex Tutorial
     // Reference: https://www.w3schools.com/java/java_regex.asp
    public boolean checkCellPhoneNumber(String mobileNumber) {
        if (mobileNumber == null) return false;
        return mobileNumber.matches("^\\+27[0-9]{9}$");
    }

    public String registerUser(String mobile) {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } 
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(mobile)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        return "Welcome " + userFirstName + ", " + userLastName + " it is great to see you.";
    }

    public boolean loginUser(String attemptedUsername, String attemptedPassword) {
        return attemptedUsername.equals(this.loginUsername) && 
               attemptedPassword.equals(this.loginPassword);
    }

    public String returnLoginStatus(boolean authenticationResult) {
        if (authenticationResult) {
            return "Welcome " + userFirstName + ", " + userLastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
