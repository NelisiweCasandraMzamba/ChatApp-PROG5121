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
        //Closes the input scanner to prevent memory loss.
        input.close();
    }
//PART 2
        //Program banner is printed when the program runs.
        System.out.println("\n === Quick Chat ===");
        System.out.println("\n Welcome To QuickChat");

        //The boolean flag controls the menu loop.
        boolean running = true;

        //When the while loop is running the flag is true.
        //The loop displays the Message Menu to users and allows users to select an option.
        //When the user chooses the third option, the running flag becomes false and the while
        //loop ends.
        while (running) {
            System.out.println("\n--- Message Menu --- ");
            System.out.println("1.) Send Message");
            System.out.println("2.) Show Recently Sent Message");
            System.out.println("3.) Quit");
            
            //Asks the user to choose an option on the message menu.
            System.out.print("Choose an option: ");

            //Read's the user's menu choice as an integer.
            //The user must enter the suggested available (1, 2,or 3) option to avoid an error.
            int chooseOption = input.nextInt();
            input.nextLine();//Clears the buffer

            //A switch statement executes the correct action based on the user's choice.
            switch (chooseOption) {
                //Calls the sendMessage() method and passes the input scanner for user input.
                case 1:
                    sendMessages(input);
                    break;
                    
                    //Informs the user that this feature is not yet available and
                    //it is still "coming soon".
                case 2:
                    System.out.println("Coming Soon.");
                    break;
                    
                    //Displays a "Goodbye!" and stops the message menu loop.
                case 3:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                    
                    //Displays an error message if the user enters an invalid option.
                default:
                    System.out.println("Invalid option. Enter 1, 2 or 3.");
            }
        }
        //Closes the input scanner after the message menu loop ends to prevent memory lose.
        input.close();
    }

    //Collects, validates and sends messages entered by the user.
    //The throw IOException if an input/output errors occurs.
    //The input scanner is used to read the keyboard input that the user enters.
    public static void sendMessages(Scanner input) throws IOException {
        
        //Asks the user how many messages they want to enter.
        System.out.print("How many messages do you wish to enter? ");
        int numOfMessages = input.nextInt();
        input.nextLine();//Clears the buffer
        

        //The for loop repeats the number of messages entered by the user
        for (int i = 1; i <= numOfMessages; i++) {
            //Displays the current number of messages.
            System.out.println("\n--- Message " + i + " ---");
            
            //Asks the user to enter the recipient's cell phone number and the number
            //must be entered with a South African international code.
            System.out.print("Enter the recipient cell number (e.g., +27718693002): ");
            String recipientCellNumber = input.nextLine();

            //Asks the user to enter a message the user wants to send towards the recipient.
            System.out.println("Enter message (maximum: 250 characters): ");
            String messageText = input.nextLine();

            //Creates a message object containing the message details.
            // Creates a new Message object by passing the message number,
            // recipient's cellphone number and message text to the constructor.
            // The object is used to access the Message class methods for
            // validating and displaying the message details
            Message obj = new Message(i, recipientCellNumber, messageText);

            //Validates the recipient's cellphone number.
            //If the cellphone number is invalid, the program will display an error message
            //and tells the user to try again.
            String recipientCheck = obj.checkRecipientCell();
            if (!recipientCheck.equals("Cell phone number successfully captured.")) {
                System.out.println(recipientCheck);
                i--; //Increments the current number of messages.
                continue;//Returns to the beginning of the loop.
            }

            //Validates the message characters so that it does not exceeds the character limit.
            //If the message length is invalid, the program will display and error message and 
            //tells the user to try again.
            String lengthCheck = obj.checkMessageLength();
            if (!lengthCheck.equals("Message ready to send.")) {
                System.out.println(lengthCheck);
                i--;//Increments the current number of messages.
                continue;//Returns to the beginning of the loop.
            }

            //Display the message details if all the details entered by the user are validated,
            //are correctly formatted.
            System.out.println("\n--- Message Details ---");
            System.out.println(obj.printMessages());

            //Calls the SentMessage() method to let the user choose what to do
            //with the message. The input scanner is passed as an argument to read input.
            String actionResult = obj.SentMessage(input);
            
            //Displays the result returned by the SentMessage() method.
            System.out.println(actionResult);

            //Checks whether the message was successfully sent by comparing the message flag with "Sent".
            if ("Sent".equals(obj.getFlag())) {
                obj.incrementTotalMessages();// Increases the total number of messages sent by one.
            }
        }
        //Displays the total number of messages successfully sent by the user.
        System.out.println("\nTotal messages sent: " + Message.getTotalMessages());
    }
}
//Farell J., 2024. Programming Logic and Design. 10th ed. Boston: Cengage Learning
