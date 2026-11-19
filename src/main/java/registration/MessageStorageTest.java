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

    //Helper method to set the private 'flag' variable using reflection
    private void setPrivateFlag(Message msg, String flagValue) {
        try {
            java.lang.reflect.Field field = Message.class.getDeclaredField("flag");
            field.setAccessible(true);
            field.set(msg, flagValue);
        } catch (Exception e) {
            System.out.println("Error setting flag: " + e.getMessage());
        }
    }

    private MessageStorage loadTestData() {
        MessageStorage manager = new MessageStorage();

        // Test Data Message 1
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?");
        setPrivateFlag(msg1, "Sent"); 
        manager.addMessage(msg1);

        // Test Data Message 2
        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        setPrivateFlag(msg2, "Stored"); 
        manager.addMessage(msg2);

        // Test Data Message 3
        Message msg3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.", "3456789012");
        setPrivateFlag(msg3, "Disregarded"); 
        manager.addMessage(msg3);

        // Test Data Message 4
        // FIXED: Using the 5-arg constructor to force the messageID to be "0838884567" 
        // so the search test can successfully find it.
        Message msg4 = new Message(4, "0838884567", "It is dinner time !", "0838884567", "0838884567");
        setPrivateFlag(msg4, "Sent"); 
        manager.addMessage(msg4);

        // Test Data Message 5
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
        
        // Adds message 4 to the stored messages array so it can be searched
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
        
        // Note: I kept the exact message text from the object (with the ! and .)
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
