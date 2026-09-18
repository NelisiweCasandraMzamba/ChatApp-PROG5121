/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package registration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cassa
 */
public class LoginTest {
    @Test 
    public void testUsernameCorrectlyFormated(){
        Login obj = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@k99!", "+27838968976");
        assertTrue(obj.checkUserName("kyl_1"));
    }
    @Test
    public void testIncorrectFormatUsername(){
        Login obj = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@k99!", "+27838968976");
        assertFalse(obj.checkUserName("kyle!!!!!!!"));
    }
    @Test
    public void testPasswordComplexitySuccess(){
        Login obj = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@k99!", "+27838968976");
        assertTrue(obj.checkPasswordComplexity("“Ch&&sec@ke99!”"));
    }
    @Test 
    public void testPasswordComplexityFailure(){
         Login obj = new Login("Kyle", "Smith", "kyl_1", "passwod", "+27838968976");
         assertFalse(obj.checkPasswordComplexity("password"));
    }
    @Test 
    public void testCheckCellPhoneNumberSuccess(){
        Login obj = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@k99!", "+27838968976");
        assertTrue(obj.checkCellPhoneNumber("+27838968976"));
    }
    @Test 
    public void testCheckCellPhoneNumberFailure(){
        Login obj = new Login("Kyle", "Smith", "kyl_1", "Ch&&sec@k99!", "08966553");
          assertFalse(obj.checkCellPhoneNumber("08966553"));
    }
    @Test
    public void testRegisterUserSuccessValidUsername(){
       Login obj = new Login("Kyle", "Smith","kyl_1" , "Ch&&sec@ke99!", "+27838968976");
       String result = obj.registerUser("kyl_1", "Ch&&sec@ke99!");
       assertEquals("Username and password successfully captured", result);
    } 
    @Test
    public void testRegisterUserFailureInvalidUsername(){
        Login obj = new Login("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976"); 
        String result = obj.registerUser("kyle!!!!!!", "Ch&&sec@k99!");
        assertEquals("Username is not correctly formatted; "
                   + "please ensure that your username contains an underscore "
                   + "and is no more than five characters in length.", result);
    }
    @Test
    public void testRegisterUserInValidPassword(){
        Login obj = new Login("Kyle", "Smith","kyl_1", "password", "+27838968976");
        String result = obj.registerUser("kyl_1", "password");
        assertEquals ("Password is not correctly formatted; "
                  + "please ensure that the password contains at least eight characters,"
                  + " a capital letter, a number, and a special character.", result);
    }
    @Test
    public void testRegisterUserValidCellPhoneNumber(){
        Login obj = new Login("Kyle", "Smith","kyl_1","Ch&&sec@ke99!", "+27838968976");
        String result = obj.registerCellPhoneNumber("+27838968976");
        assertEquals("Cell number is successfully captured", result);
    }
    @Test 
    public void testRegisterUserInvalidCellPhoneNumber(){
          Login obj = new Login("Kyle", "Smith","kyl_1", "Ch&&sec@ke99!", "08966553");
          String result = obj.registerCellPhoneNumber("08966553");
          assertEquals("Cell number is incorrectly formatted oo does not contain an international code;"
                    + " please correct the number and try again.", result);
    }
    @Test
    public void testLoginSuccess(){
         Login obj = new Login("Kyle", "Smith","kyl_1","Ch&&sec@ke99!", "+27838968976");
          assertTrue(obj.loginUser("kyl_1","Ch&&sec@ke99!"));
    }
    @Test
    public void testLoginFailure(){
         Login obj = new Login("Kyle", "Smith","kyl_1","Ch&&sec@ke99!", "+27838968976");
         assertFalse(obj.loginUser( "kyle!!!!!!!", "password"));
    }
    @Test
    public void testReturnLoginStatusSuccess(){
         Login obj = new Login("Kyle", "Smith","kyl_1","Ch&&sec@ke99!", "+27838968976");
         String expected = "Welcome Kyle Smith, it is great to see you.";
         assertEquals(expected, obj.returnLoginStatus("kyl_1","Ch&&sec@ke99!"));
    }
    @Test
    public void testReturnLoginStatusFailure(){
         Login obj = new Login("Kyle", "Smith","kyl_1","Ch&&sec@ke99!", "+27838968976");
         String expected = "Username or password is incorrect, please try again";
         assertEquals(expected, obj.returnLoginStatus("kyle!!!!!!!", "password"));
    }
}