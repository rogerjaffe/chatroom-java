package com.mrjaffesclass.apcs.messenger;

/**
 * Implement this interface for the class that wants to subscribe to messages. You can 
 * subscribe your class
 * to the Messages class using the Messages.subscribe method.  Send messages to the classes
 * who have subscribed using the Messages.notify method. An optional descendant of Object 
 * can be passed with the message.
 * 
 * @author Roger Jaffe
 * @version 2014-09-30
 */
public interface MessageHandler

{
    /**
     * Fired when a message is sent through the Messages class that this
     * controller has subscribed to.
    */
    public void messageHandler(String messageName, Object messagePayload);   
}
