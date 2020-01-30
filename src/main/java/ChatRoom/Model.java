package ChatRoom;

import com.google.auth.oauth2.GoogleCredentials;
import com.mrjaffesclass.apcs.messenger.*;
import java.util.ArrayList;
import com.google.firebase.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final Messenger mvcMessaging;

  // Model's data variables
  private int variable1;
  private int variable2;
  private String username;
  private boolean loggedIn;

  public void setLoggedIn(boolean loggedIn) {
    this.loggedIn = loggedIn;
  }

  public boolean isLoggedIn() {
    return loggedIn;
  }
  private ArrayList<Chat> chatLog;

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
    try {
      initFirebase();    
    }
    catch (FileNotFoundException e) {
      System.out.println("Firebase configuration file not found");
    }
    catch (IOException e) {
      System.out.println("I/O Exception when authenticating");
    }
  }
  
  private void initFirebase() throws FileNotFoundException, IOException {
    FileInputStream serviceAccount =
      new FileInputStream("./chatroom.json");

    FirebaseOptions options = new FirebaseOptions.Builder()
      .setCredentials(GoogleCredentials.fromStream(serviceAccount))
      .setDatabaseUrl("https://chatroom-a6b84.firebaseio.com")
      .build();

    FirebaseApp.initializeApp(options);    
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("view:login", this);
    mvcMessaging.subscribe("view:sendChatItem", this);
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messageName.equals("view:sendChatItem")) {
      Chat newChat = (Chat)messagePayload;
      final FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference ref = database.getReference("chatlog-csa").push();
      ref.setValue(newChat, null);
    }
    
    if (messageName.equals("view:login")) {
      // Get a reference to our posts
      final FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference ref = database.getReference("chatlog-csa");

      // Attach a listener to read the data at our posts reference
      ref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
          ArrayList<Chat> chatList = new ArrayList<>();
          TreeMap list = new TreeMap((HashMap)dataSnapshot.getValue());
          Set keys = list.keySet();
          Iterator<String> itr = keys.iterator();
          while (itr.hasNext()) {
            String key = itr.next();
            HashMap hmItem = (HashMap)list.get(key);
            chatList.add(new Chat((String)hmItem.get("username"), (String)hmItem.get("message")));
          }
          mvcMessaging.notify("model:chatLogChanged", chatList, true);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
          System.out.println("The read failed: " + databaseError.getCode());
        }
      });      
    }
//    MessagePayload payload = (MessagePayload)messagePayload;
//    int field = payload.getField();
//    int direction = payload.getDirection();
//    
//    if (direction == Constants.UP) {
//      if (field == 1) {
//        setVariable1(getVariable1()+Constants.FIELD_1_INCREMENT);
//      } else {
//        setVariable2(getVariable2()+Constants.FIELD_2_INCREMENT);
//      }
//    } else {
//      if (field == 1) {
//        setVariable1(getVariable1()-Constants.FIELD_1_INCREMENT);
//      } else {
//        setVariable2(getVariable2()-Constants.FIELD_2_INCREMENT);
//      }      
//    }
  }

  /**
   * Getter function for variable 1
   * @return Value of variable1
   */
  public int getVariable1() {
    return variable1;
  }

  /**
   * Setter function for variable 1
   * @param v New value of variable1
   */
  public void setVariable1(int v) {
    variable1 = v;
    // When we set a new value to variable 1 we need to also send a
    // message to let other modules know that the variable value
    // was changed
    mvcMessaging.notify("model:variable1Changed", variable1, true);
  }
  
  /**
   * Getter function for variable 1
   * @return Value of variable2
   */
  public int getVariable2() {
    return variable2;
  }
  
  /**
   * Setter function for variable 2
   * @param v New value of variable 2
   */
  public void setVariable2(int v) {
    variable2 = v;
    // When we set a new value to variable 2 we need to also send a
    // message to let other modules know that the variable value
    // was changed
    mvcMessaging.notify("model:variable2Changed", variable2, true);
  }

}
