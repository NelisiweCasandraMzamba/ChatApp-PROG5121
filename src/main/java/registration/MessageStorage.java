/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registration;
import java.util.ArrayList;

/**
 *
 * @author cassa
 */
//PART 3
import java.util.ArrayList; //Imports the ArrayList class to store and manage multiple message objects

public class MessageStorage {  
//W3School.(n.d). Java ArrayList [online] Available at: https://www.w3schools.com/java/java_arraylist.asp [Accessed 17 November 2026].
   
    public ArrayList<Message> sentMessages = new ArrayList<>(); //Stores all successfully sent messages.
    public ArrayList<Message> disregardedMessages = new ArrayList<>(); //Stores messages that were disregarded or not sent.
    public ArrayList<Message> storedMessages = new ArrayList<>(); //Stores messages saved for later viewing or processing.
    public ArrayList<String> messageHashes = new ArrayList<>(); //Stores the message hash values of the stored messages.
    public ArrayList<String> messageIDs = new ArrayList<>(); //Stores the message IDs assigned to the stored messages

    
    //Adds the messages to its appropriate list based on its status 
    //and stored its message hash and message ID for future retrieval.
    public void addMessage(Message msg) {
        
        //Adds sent messages to the sent list
        if (msg.getFlag().equals("Sent")) {
            sentMessages.add(msg);
            
            //Adds stored messages to the stored list
        } else if (msg.getFlag().equals("Stored")) {
            storedMessages.add(msg);
            
            //Adds disregarded messages to the disregarded list
        } else if (msg.getFlag().equals("Disregarded")) {
            disregardedMessages.add(msg);
        }
        
        messageHashes.add(msg.getMessageHash()); //Stores the message hash for searching and deletion
        messageIDs.add(msg.getMessageID()); //Stores the message ID for searching purposes
    }
    
    //Displays the sender and recipient details 
    //and returns a message if there are no stored messages available
    public String displaySenderAndRecipient() {
        String result = "";
        
        //Loops through the stored messages
        for (Message msg : storedMessages) {
            //Adds the sender and recipient details to the result string
            result += "Sender: " + msg.getSender() + " | Recipient: " + msg.getRecipient() + "\n";
        }
        //Returns a message if there are no stored messages
        return result.isEmpty() ? "No stored messages." : result;
    }
    
    //Finds and returns the longest stored message
    //and displays a message if there no stored messages available
    public String displayLongestMessage() {
        Message longest = null;
        
        //Loops through the stored messages
        for (Message msg : storedMessages) {
            
            //Checks if the current message is longer than the current longest message
            if (longest == null || msg.getMessageText().length() > longest.getMessageText().length()) {
                longest = msg;
            }
        }
        //Returns the longest message text or a message to tell the user that there are no stored messages
        return longest != null ? longest.getMessageText() : "No stored messages.";
    }
    
    //Searches for a stored message using its message ID
    //and returns the recipient and message content if found
    public String searchByMessageID(String id) {
        
        //Loops through the stored messages
        for (Message msg : storedMessages) {
            
            //Checks if the entered Message ID matches an existing Message ID
            if (msg.getMessageID().equals(id)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessageText();
            }
        }
         //Returns a message if no matching Message ID is found
        return "Message ID not found.";
    }

    //Searches for all stored messages that belong to a specific recipient
   //and returns the message contents if matches are found
    public String searchByRecipient(String recipient) {
        String result = "";
        
        //Loops through the stored messages
        for (Message msg : storedMessages) {
            
            //Checks if the recipient matches the entered recipient number
            if (msg.getRecipient().equals(recipient)) {
                result += msg.getMessageText() + "\n";
            }
        }
         //Returns the matching messages or a message telling the user that no messages where found
        return result.isEmpty() ? "No messages found for this recipient." : result;
    }
    //Searches for a stored message using its message hash
   //and deletes the message if a matching hash is found
    public String deleteMessageByHash(String hash) {
        
        //Loops through the stored messages
        for (int i = 0; i < storedMessages.size(); i++) {
            
             //Checks if the entered hash matches the message hash
            if (storedMessages.get(i).getMessageHash().equals(hash)) {
                
                //Stores the message text before deleting the message
                String text = storedMessages.get(i).getMessageText();
                
                storedMessages.remove(i);//Removes the message from the stored messages list
                
                //Confirms that the message was successfully deleted
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        //Returns a message telling the user if the hash cannot be found
        return "Message hash not found.";
    }

    //Generates and displays a report containing the details
    //of all stored messages, including the hash, recipient,
    //and message content
    public String displayReport() {
        String result = "--- Stored Messages Report ---\n";
        
         //Loops through the stored messages
        for (Message msg : storedMessages) {
            
            //Adds the message details to the report
            result += "Hash: " + msg.getMessageHash() + "\n" +
                      "Recipient: " + msg.getRecipient() + "\n" +
                      "Message: " + msg.getMessageText() + "\n\n";
        }
        //Returns the report or a message if no stored messages exist
        return result.equals("--- Stored Messages Report ---\n") ? "No stored messages to report." : result;
    }
}