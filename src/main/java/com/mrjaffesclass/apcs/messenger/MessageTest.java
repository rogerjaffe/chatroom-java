package com.mrjaffesclass.apcs.messenger;


/**
 * Test runner for Messages
 * Instantiating the class will create three test controllers.
 * The controllers are subscribed to a subset of three messages
 * Executing the sendMessageX method will send that message to the 
 * controllers.  Those that subscribe to that message will fire that
 * controller's notify method
 * 
 * @author Roger Jaffe 
 * @version 2014-09-30
 */
public class MessageTest
{
    /**
     * Instance variables for the MessageTest class are the
     * three controllers.  They will be constructed along with
     * a Messages object which will serve as our message center
     */
    private MessageControllerTest controllerA = new MessageControllerTest("A");
    private MessageControllerTest controllerB = new MessageControllerTest("B");
    private MessageControllerTest controllerC = new MessageControllerTest("C");
    private Messenger m = new Messenger();
    /**
     * Constructor for the MessageTest class
     * which will subscribe the three controllers to a subset of the 
     * three messages
     */
    public MessageTest()
    {
        m.subscribe("Message1", controllerA);
        m.subscribe("Message1", controllerB);
        m.subscribe("Message1", controllerC);
        
        m.subscribe("Message2", controllerA);
        m.subscribe("Message2", controllerB);
        
        m.subscribe("Message3", controllerB);
        m.subscribe("Message3", controllerC);
        
        m.subscribe("Message4", controllerC);        
    }
    
    /**
     * Send message 1 to the controllers that have subscribed to it
     */
    public void sendMessage1() {
        m.notify("Message1");
    }

    /**
     * Send message 2 to the controllers that have subscribed to it
     */
    public void sendMessage2() {
        m.notify("Message2");
    }
    
    /**
     * Send message 3 to the controllers that have subscribed to it
     */
    public void sendMessage3() {
        m.notify("Message3");
    }

    /**
     * Send message 4 to the controllers that have subscribed to it
     */
    public void sendMessage4() {
        m.notify("Message4");
    }
    
    /**
     * Send message 2 to the controllers that have subscribed to it
     * along with a data packet of class MessageData.
     */
    public void setMessageWithData() {
        MessagePayloadTest messagePayloadTest = new MessagePayloadTest();
        m.notify("Message3", 100);
    }

}
