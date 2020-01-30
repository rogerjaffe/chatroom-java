package com.mrjaffesclass.apcs.messenger;

import java.util.*;
/**
 * Contains a message name and a list of controllers that subscribe to the message
 * 
 * @author Roger Jaffe
 * @version 1.0
 */
public class MessageObject
{
    // Message name and
    // List of controllers subscribing to this message
    private String messageName;
    private ArrayList<MessageHandler> list = new ArrayList<MessageHandler>();
    
    /**
     * Constructor
     * 
     * @param aMessageName Name of the message (duh...)
     */
    public MessageObject(String aMessageName)
    {
        messageName = aMessageName;
    }
    
    /**
     * Getter function for the message name
     * 
     * @return The message name
     */
    public String getMessageName() {
        return messageName;
    }

    /**
     * Subscribes a controller to this message
     * 
     * Input:
     *   MessageController mc:              Controller that is subscribing to this message
     */
    public void addObject(MessageHandler mc) {
        list.add(mc);
    }
    
    /**
     * Fires the notify method of the controllers that subscribed
     * 
     * @param Data that is sent with the message
     */
    public void notify(Object data) {
        for (MessageHandler anObject : list) {
            anObject.messageHandler(messageName, data);
        }
    }
    
}
