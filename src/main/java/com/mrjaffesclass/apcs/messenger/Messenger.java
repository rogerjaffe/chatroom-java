package com.mrjaffesclass.apcs.messenger;

import java.util.*;

/**
 * Implements messaging between controllers
 * 
 * @author Roger Jaffe
 * @version 2014-09-30
 */

public class Messenger
{
    // Keeps our message list
    private List<MessageObject> messageList = new ArrayList<MessageObject>();
    private boolean debug = false;

    /**
     * Constructor for the messaging system
     */
    public Messenger() {

    }

    /**
     * Constructor for the messaging system
     */
    public Messenger(boolean _debug) {
        debug = _debug;
    }

    /**
     * Finds the controller list for a specified message name
     * 
     * @param messageName Name of message we're looking for
     * @return The MessageObject if found, or null otherwise
     */
    private MessageObject findMessage(String messageName) {
        MessageObject found = null;

        for (MessageObject m : messageList) {
            if ((found == null) && messageName.equals(m.getMessageName())) {
                found = m;
            }
        }
        return found;
    }

    /**
     * Subscribe an object so its notify method fires when the message is sent
     * 
     * @param messageName Name of the message
     * @param mc The object that is notified when messageName is published
     */
    public void subscribe(String messageName, MessageHandler mh) {
        // Log the subscription if debug is on
        if (debug) {
            System.out.println("SUB: +"+messageName+" "+mh.getClass());
        }
        
        // See if we already have this message in the list
        MessageObject mo = findMessage(messageName);

        // If the message was found...
        if (mo != null) {
            // Add it to the list
            mo.addObject(mh);
        } else {
            // Otherwise make a new MessageObject, add the controller to it, then add
            // the new MessageObject to the message objects in our list
            MessageObject newMessage = new MessageObject(messageName);
            newMessage.addObject(mh);
            messageList.add(newMessage);
        }
    }

    /**
     * Call to send a message with no data to the controllers that have subscribed
     * 
     * @param messageName Name of the message to send
     * @return True if MessageObject was found
     */
    public boolean notify(String messageName) {
        return notify(messageName, null, debug);
    }

    /**
     * Call to send a message to the controllers that have subscribed
     * 
     * @param messageName Name of the message to send
     * @param payload Data sent with the message.  
     * @return True if MessageObject was found
     */
    public boolean notify(String messageName, Object payload) {
        return notify(messageName, payload, debug);
    }

    /**
     * Call to send a message to the controllers that have subscribed
     * and log the message and data if present to the console when debug is true
     * 
     * @param messageName Name of the message to send
     * @param payload Data sent with the message.  
     * @param debug Set true for log messages to console
     * @return True if MessageObject was found
     */
    public boolean notify(String messageName, Object payload, boolean localDebug) {
        if (localDebug) {
            logMessage(messageName, payload);
        }
        MessageObject mo = findMessage(messageName);
        if (mo != null) {
            mo.notify(payload);
        }
        return (mo != null);
    }

    /**
     * Log message and data to the console
     * @param messageName   Message name
     * @param payload       Payload data
     */
    public void logMessage(String messageName, Object payload) {
        if (payload != null) {
            System.out.println("MSG: "+messageName+" | "+payload.toString());
        } else {
            System.out.println("MSG: "+messageName+" | no data sent");
        }
    }
}
