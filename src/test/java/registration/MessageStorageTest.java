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
public class MessageStorageTest {

    // The method is used to set the value of a private flag field in a Message object using reflection
    private void setPrivateFlag(Message msg, String flagValue) {
        try {
             //Gets access to the private field named "flag" from the Message class
            java.lang.reflect.Field field = Message.class.getDeclaredField("flag");
            
            //Accesses the private field
            field.setAccessible(true);
            
            //Sets the value of the flag field for the specified Message object
            field.set(msg, flagValue);
        } catch (Exception e) {
            //Displays an error message if the field cannot be accessed or updated
            System.out.println("Error setting flag: " + e.getMessage());
        }
    }

    private MessageStorage loadTestData() {
        MessageStorage manager = new MessageStorage();

        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        setPrivateFlag(msg1, "Sent"); 
        manager.addMessage(msg1);

        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        setPrivateFlag(msg2, "Stored"); 
        manager.addMessage(msg2);

        Message msg3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.", "3456789012");
        setPrivateFlag(msg3, "Disregarded"); 
        manager.addMessage(msg3);

        Message msg4 = new Message(4, "0838884567", "It is dinner time !", "0838884567", "0838884567");
        setPrivateFlag(msg4, "Sent"); 
        manager.addMessage(msg4);

        Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.");
        setPrivateFlag(msg5, "Stored"); 
        manager.addMessage(msg5);

        return manager;
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        MessageStorage manager = loadTestData();
        
        assertEquals(2, manager.sentMessages.size());
        assertEquals("Did you get the cake?", manager.sentMessages.get(0).getMessageText());
        assertEquals("It is dinner time !", manager.sentMessages.get(1).getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        MessageStorage manager = loadTestData();
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, manager.displayLongestMessage());
    }

    @Test
    public void testSearchForMessageID() {
        MessageStorage manager = loadTestData();
        
        manager.storedMessages.add(manager.sentMessages.get(1)); 
        
        String expected = "Recipient: 0838884567\nMessage: It is dinner time !";
        assertEquals(expected, manager.searchByMessageID("0838884567"));
    }

    @Test
    public void testSearchAllMessagesSentOrStoredRegardingParticularRecipient() {
        MessageStorage manager = loadTestData();
      
        String expected = "Where are you? You are late! I have asked you to be on time.\nOk, I am leaving without you.\n";
        assertEquals(expected, manager.searchByRecipient("+27838884567"));
    }

    @Test
    public void testDeleteMessageByHash() {
        MessageStorage manager = loadTestData();
      
        String hashToDelete = manager.storedMessages.get(0).getMessageHash();

        String expected = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        assertEquals(expected, manager.deleteMessageByHash(hashToDelete));
        
        assertEquals(1, manager.storedMessages.size());
    }

    @Test
    public void testDisplayReport() {
        MessageStorage manager = loadTestData();
        String report = manager.displayReport();
        
        assertTrue(report.contains("Hash: ")); 
        assertTrue(report.contains("Recipient: "));
        assertTrue(report.contains("Message: "));
    }
}
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

