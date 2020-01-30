package com.mrjaffesclass.apcs.messenger;


/**
 * A test runner controller to test the message implementation
 * 
 * @author Roger Jaffe 
 * @version 2014-09-30
 */
public class MessageControllerTest implements MessageHandler
{
    private String ident;         // Keeps the controller number
    
    /**
     * Instantiate the controller
     * 
     * @param aNumber Controller number for identification in the test program
     */
    public MessageControllerTest (String aIdent) {
        ident = aIdent;
    }
    
    /**
     * Method that is fired when a message is sent that this controller is 
     * subscribed to.
     * 
     * @param messageName Name of the message being handled
     * @param messageData Data sent with the message
     */
    public void messageHandler(String messageName, Object messageData) {
        System.out.println("Controller "+ident+' '+messageName);
        if (messageData != null) {
            System.out.println(messageData.toString());
        }
    }
}
