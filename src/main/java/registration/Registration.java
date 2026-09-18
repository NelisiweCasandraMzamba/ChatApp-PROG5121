/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package registration;
import java.util.Scanner;
/**
 *
 * @author cassa
 */
public class Registration {

    public static void main(String[] args) {

       Scanner input = new Scanner(System.in);
      
        System.out.println("==========REGISTRATION=============");
        //Prompts the user to enter their first name
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        
        //Prompts the user to enter their last name
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        
        //Prompts the user to enter their username
        //The username must must contain underscore '_' and must be 5 characters or less.
        System.out.print("Enter username (must contain '_' and be 5 characters or less): ");
        String username = input.nextLine();
        
        //Prompts the user to enter their password
        //The password must contain 8+ charachers, a special character,
        //a capital letter and a number.
        System.out.print("Enter password (8+ characters, a capital letter "
                + "a number, and a special character): ");
        String password = input.nextLine();
        
        //Prompts the user to enter their cellphone number
        //The users cellphone number must be a valid South African number and
        //it must contain the country's international code (+27...).
        System.out.print("Enter South Africn cellphone number (+27...): ");
        String cellPhoneNumber = input.nextLine();
        
        //Create a new Login object.
        //The information entered by the user is passed to the Login constructor. 
        Login obj = new Login (firstName, lastName, username, password, cellPhoneNumber);
        
        //Calls the registerUser() method to register the user.
        //The username and password are passed to the method,
        //then it displays a messsage of the outcome of the registration.
        System.out.println("\n" + obj.registerUser(username, password));
        
        //Checks if all the registration critera's are meet
        //Checks if the username is formatted correctly
        //Checks if the password follows all the the password complexity rules, 
        //and checks if the phone number is valid.
        //(Farrel, 2018).
        if (obj.checkUserName(username) 
                && obj.checkPasswordComplexity(password)
                && obj.checkCellPhoneNumber(cellPhoneNumber)){
            
          
            System.out.println("=======LOGIN==============");
            //Prompts the user to re-enter their username 
            System.out.print("Enter username: ");
            String enteredUsername =input.nextLine();
            
            //Prompts the user to re-enter their username
            System.out.print("Enter password: ");
            String enteredPassword = input.nextLine();
            
            //Evaluates the entered username and password against
            //the registered username and password, 
            //and the method will return a message on whether the login was successful or not(Farrel, 2018).
            System.out.println(obj.returnLoginStatus(enteredUsername, enteredPassword));
        }
        //Closes the input scanner to prevent memory lose.
        input.close();
    }

}
//Farell J., 2024. Programming Logic and Design. 10th ed. Boston: Cengage Learning