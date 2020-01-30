package com.mrjaffesclass.apcs.messenger;
import java.util.*;

/**
 * This is a representative class that can be sent with the message as
 * the message payload.  You can use any class in place of this class in your
 * messages.
 * 
 * @author Roger Jaffe
 * @version 2014-09-30
 */
public class MessagePayloadTest
{
    /**
     * Constructor
     */
    public MessagePayloadTest() {

    }
    
    /**
     * Overrides the toString method to display the message payload
     * 
     * @return Simple text message identifying this as the message payload
     */
    public String toString() {
        return "This is the message payload";
    }

}
