/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 *
 * @author cassa
 */
//PART 2
//Declares the Message class, which stores information about individual messages.
public class Message {
    private String messageID; //Stores the unique 10-character ID generated for each message.
    private int messageNumber; //Stores the number assigned to the message.
    private String recipient; //Stores the recipient's cellphone number.
    private String messageText; //Stores the actual text content of the message.
    private String messageHash; //Stores the message has that was created from the message details.
    private String flag;  //Stores the current status of the message, such as Sent, Stored, or Disregarded.

    //Declares a static variable to keep track of the total number of messages sent.
    //The variable is shared by all objects created from the Message class.
    //It starts at 0 because no messages have been sent yet.
    private static int totalMessagesSent = 0;

    //This method returns the current total number of messages sent.
    //It can be called using the class name without creating an object.
    public static int getTotalMessages() {
        return totalMessagesSent;// Returns the current value of totalMessagesSent.
    }
    //This method increases the total message count by 1.
    public void incrementTotalMessages() {
        totalMessagesSent++; //Adds 1 to totalMessagesSent whenever a message is successfully sent.
    }
    //This method returns the current status of the message.
    //Returns the value stored in the flag variable.
    //The flag indicates whether the message was sent, stored, or disregarded.
    public String getFlag() {
        return flag;
    }
    
   //This is the constructor for the Message class.
   //It is called whenever a new Message object is created.
   //It receives the message number, recipient's cellphone number, and message text.
    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;  //Assigns the received message number to the object's messageNumber variable.
        this.recipient = recipient; //Assigns the recipient's cellphone number to the object's recipient variable.
        this.messageText = messageText;   //Assigns the message content to the object's messageText variable.
        this.messageID = generateMessageID();  //Generates a random 10-digit message ID and store it in messageID.
        this.messageHash = createMessageHash(); //Generates a message hash using the message details and store it in messageHash.
    }
   //Generates and returns a unique-looking 10-digit message ID.
    private String generateMessageID() {
        //Creates a Random object to generate a random number.
        Random rand = new Random();
        
        //Generates a random decimal value between 0.0 (inclusive) and 1.0 (exclusive).
       //Multiplies it by 10 billion to produce a number within the range 0 to 9,999,999,999.
       //and converts the decimal result to a long to remove the decimal part.
        long num = (long) (rand.nextDouble() * 10_000_000_000L);   
        String id = String.valueOf(num);// Convert the generated number into a String so it can be formatted.
        
        //Checks whether the ID contains fewer than 10 digits and
       //if it does, add zeros to the beginning until it contains 10 characters.
        while (id.length() < 10) {
            id = "0" + id;
        }
        //Returns the first 10 characters of the ID and
        //this ensures that the returned message ID has exactly 10 characters
        return id.substring(0, 10);
    }
    //Checks whether the recipient's cellphone number follows the required format.
    public String checkRecipientCell() {
        //Checks that the recipient is not null and matches the South African
        //international cellphone format: +27 followed by exactly nine digits.
        if (recipient != null && recipient.matches("^\\+27\\d{9}$")) {
            
             //Returns a success message if the cellphone number is correctly formatted.
            return "Cell phone number successfully captured."; 
        } else {
            //Returns an error message if the number is missing or incorrectly formatted.
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    
    //Checks whether the message exceeds the maximum allowed length of 250 characters.
    public String checkMessageLength() {
        if (messageText.length() > 250) {
            int exceeds = messageText.length() - 250; //Calculates how many characters the message exceeds the limit by.
            
            //Returns an error message showing how many characters must be removed.
            return "Message exceeds 250 characters by " + exceeds + "; please reduce the size.";
        } else {
            //Returns a confirmation message if the message contains 250 characters or fewer.
            return "Message ready to send.";
        }
    }
    
    //Creates and returns a unique hash for the message using its ID,
    //message number, first word and last word
    public String createMessageHash() {
        
    //Extracts the first characters of the message ID.
    //These characters are used as part of the message hash.
    String idPart = messageID.substring(0, 2);
    
    String[] words = messageText.split(" "); //Splits the message text into an array of words using spaces
    //as the separators


    String firstWord = words[0]; //Retrieves the first word from the words array.
    
    //Retrieves the last word from the words array by using the
    //array length minus one, because array indexes start at 0.
    String lastWord = words[words.length - 1];
    
    //Combines the first characters of the message ID,
    //the message number and the first and last words in uppercase.
    //Colons separate the three parts of the hash.
    return idPart + ":" + messageNumber + ":" + (firstWord + lastWord).toUpperCase();
    }

    //Allows users to choose whether to send, disregard or store messagess.
    //Returns a confirmation message and throws IOException if a file error occurs.
    //The scanner object is used to read the user's menu selection.
    //The throws IOException if an input/output error occurs while storing the message.
    public String SentMessage(Scanner scanner) throws IOException {
        //Displays the banner and the available options the user must choose.
        System.out.println("\n Choose an option: " );
        System.out.println("1) Send Message");
        System.out.println("2) Disregard Message");
        System.out.println("3) Store Message");
        
        //Prompts the user to choose an option 
        System.out.print("Enter choice: ");
        
       //Read's the user's menu choice as an integer.
        int choice = scanner.nextInt();
        scanner.nextLine(); //Clears the buffer
        
      //A switch statement executes the correct action based on the user's choice.
        switch (choice) {
            //Updates the message status to indicate that it has been sent.
            //Returns the confirmation message that the message was sent successfully sent.
            case 1:
                flag = "Sent";
                return "Message successfully sent.";
                
            //Sets the message status to Disregarded.   
            //Inform the user how to proceed with deleting the message.
            case 2:
                flag = "Disregarded";
                return "Press 0 to delete the message.";
                
            //Sets the message status to Stored and
            //calls the storeMessage method to save the message in the JSON file.
            //It returns a confirmation message was stored successfully.
            case 3:
                flag = "Stored";
                storeMessage();
                return "Message successfully stored.";
                
            //Displays an error message if the user enters an invalid option.  
            default:
                return "Invalid option. Message not processed.";
        }
    }
    //Returns the message details as a formatted string
    public String printMessages() {
        //Combines the message ID, message hash, recipient and message text
        //into one string, with each detail displayed ona separate line.
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }
    //Stores the message details in a JSON file.
    //the method can throw an IOException if a file input/output error occurs.
    //(JavaScriptRoom, 2026.)
    public void storeMessage() throws IOException {
     //Creates a File object representing the messages.json file.
    File file = new File("storeMessages.json");
    String oldContent = "";//Initialises a string to hold existing JSON content.

    //Checks whether the file exists and contains data.
    if (file.exists() && file.length() > 0) {
        //Opens the file to read its existing content.
        Scanner input = new Scanner(file);
       //Reads each line and adds it to the old content.
        while (input.hasNextLine()) {
            oldContent += input.nextLine() + "\n";
        }
        input.close(); //Closes the Scanner after reading the file.

         //Removes the opening square bracket of the JSON array.
        oldContent = oldContent.trim();
        if (oldContent.startsWith("[")) {
            oldContent = oldContent.substring(1);
        }
         //Removes the closing square bracket of the JSON array.
        if (oldContent.endsWith("]")) {
            oldContent = oldContent.substring(0, oldContent.length() - 1);
        }
        //Removes any remaining unnecessary whitespace.
        oldContent = oldContent.trim();
    }
    //Builds a JSON object containing the current message details.
    String newObject = "  {\n" +
            "    \"messageID\": \"" + escape(messageID) + "\",\n" +
            "    \"messageNumber\": " + messageNumber + ",\n" +
            "    \"recipient\": \"" + escape(recipient) + "\",\n" +
            "    \"messageText\": \"" + escape(messageText) + "\",\n" +
            "    \"messageHash\": \"" + escape(messageHash) + "\"\n" +
            "  }";

    //Creates a new JSON array to hold the existing and new messages.
    //Adds the existing messages if the file already contains messages.
    String newContent = "[\n";
    if (!oldContent.isEmpty()) {
        newContent += oldContent + ",\n";
    }
    //Adds the new message object and closes the JSON array
    newContent += newObject + "\n]\n";
    
    //Opens the file for writing, replacing its previous contents.
    PrintWriter writer = new PrintWriter(new FileWriter(file));
    writer.print(newContent);//Writes the updated JSON content to the file.
    writer.close();//Closes the writer to finish writing and release the resource.

     //Confirms that the message has been stored.
    System.out.println("Message successfully stored.");
    //JavaScriptRoom, 2026. How to Import JSON Library into NetBeans Project: Fixing 'Package Doesn't Exist' Error [blog].
    //Available at:<https://www.javascriptroom.com/blog/importing-json-into-an-netbeans-project/> [Accessed 17 October 2026].
 }
    //Converts special numbers in a string so they can be safely stored in the JSON File.
    private String escape(String text) {
      
    //Checks whether the input is null and returns an empty string if it is empty.
    if (text == null) return "";
    //Adds excape characters to backslashes and double quotation marks.
    return text.replace("\\", "\\\\").replace("\"", "\\\"");
 }
}