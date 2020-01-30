/*
 * Copyright 2018 Roger Jaffe
 * All rights reserved
 */

package ChatRoom;

/**
 *
 */
public class Chat {

  private String username;
  private String message;
  
  /**
   * Chat constructor
   * @param username
   * @param message 
   */
  public Chat(String username, String message) {
    this.username = username;
    this.message = message;
  }

  /**
   * Gets the username
   * @return username
   */
  public String getUsername() {
    return username;
  }

  public String getMessage() {
    return message;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  

}
