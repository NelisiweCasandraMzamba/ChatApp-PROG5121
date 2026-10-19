/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package registration;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Scanner;

/**
 * Unit tests for Message class
 * @author cassa
 */
//PART2
public class MessageTest {

    public MessageTest() {
    }

    @Test
    public void testMessageLengthSuccess() {
        Message obj = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", obj.checkMessageLength());
    }

    @Test
    public void testMessageFailure() {
        String longMessage = "a".repeat(250) + "a";
        Message obj = new Message(1, "+27718693002", longMessage);
        assertEquals("Message exceeds 250 characters by 1; please reduce the size.", obj.checkMessageLength());
    }

    @Test
    public void testRecipientCellSuccess() {
        Message obj = new Message(1, "+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", obj.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message obj = new Message(1, "08575975889", "Hello");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", obj.checkRecipientCell());
    }

    @Test
    public void testMessageIDGenerated() {
        Message obj = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(obj.printMessages().contains("Message ID: "));
    }

    @Test
    public void testMessageSentOption() throws Exception {
        Message obj = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", obj.SentMessage(new Scanner("1\n")));
    }

    @Test
    public void testMessageDisregardOption() throws Exception {
        Message obj = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Press 0 to delete the message.", obj.SentMessage(new Scanner("2\n")));
    }

    @Test
    public void testMessageStoreOption() throws Exception {
        Message obj = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", obj.SentMessage(new Scanner("3\n")));
    }

    @Test
    public void testTotalNumberMessagesSent() {
        int initialTotal = Message.getTotalMessages();
        Message obj = new Message(1, "+27718693002", "Hello");
        obj.incrementTotalMessages();
        assertEquals(initialTotal + 1, Message.getTotalMessages(), "Total messages sent counter did not increment correctly.");
    }

    @Test
    public void testMessageHash() {
        Message obj = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(obj.printMessages().contains(":0:"));
    }
}   
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}

    

