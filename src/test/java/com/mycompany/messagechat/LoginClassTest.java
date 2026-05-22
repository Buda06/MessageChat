/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.messagechat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */
public class LoginClassTest {
    
    public LoginClassTest() {
    }
    
    @Test
    public void testUsernameCorrect() {
        LoginClass instance = new LoginClass("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith");
        String expected = "Welcome Kyle, Smith it is great to see you.";
        String actual = instance.registerUser("+27838968976");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testUsernameIncorrect() {
        LoginClass instance = new LoginClass("kyle!!!!!!!", "Ch&sec@ke99!", "Kyle", "Smith");
        String expected = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = instance.registerUser("+27838968976");
        assertEquals(expected, actual);
    }
    
    @Test
    public void testPasswordCorrect() {
        LoginClass instance = new LoginClass("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith");
        assertTrue(instance.checkPasswordComplexity());
    }
    
    @Test
    public void testPasswordIncorrect() {
        LoginClass instance = new LoginClass("kyl_1", "password", "Kyle", "Smith");
        assertFalse(instance.checkPasswordComplexity());
    }
    
    @Test
    public void testPhoneCorrect() {
        LoginClass instance = new LoginClass("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith");
        assertTrue(instance.checkCellPhoneNumber("+27838968976"));
    }
    
    @Test
    public void testPhoneIncorrect() {
        LoginClass instance = new LoginClass("kyl_1", "Ch&sec@ke99!", "Kyle", "Smith");
        assertFalse(instance.checkCellPhoneNumber("08966553"));
    }
    
    // ============ assertTrue/assertFalse TESTS (2 required from rubric) ============
    
    @Test
    public void testLoginSuccess() {
        LoginClass instance = new LoginClass("john_doe", "Pass123!", "John", "Doe");
        assertTrue(instance.loginUser("john_doe", "Pass123!"));
    }
    
    @Test
    public void testLoginFail() {
        LoginClass instance = new LoginClass("john_doe", "Pass123!", "John", "Doe");
        assertFalse(instance.loginUser("john_doe", "wrongpass"));
    }
}    