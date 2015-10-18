package pl.michalek.marcin.chatdemo.adapter.data;

import android.util.Log;
import pl.michalek.marcin.apeclient.network.data.User;
import pl.michalek.marcin.chatdemo.config.Constants;

import java.util.*;

/**
 * Used when conversation with multiple users.
 * Contains mapping for users pipe and message from that pipe (user)
 *
 * @author Marcin Michałek
 */
public class MessageRepository {
  private Map<User, List<MessageData>> messageDataMap;

  public MessageRepository() {
    messageDataMap = new HashMap<User, List<MessageData>>();
  }

  public void addMessageFromUser(User user, MessageData messageData) {
    List<MessageData> messagesFromUser = messageDataMap.get(user);
    if (messagesFromUser == null) {
      messagesFromUser = new LinkedList<MessageData>();
    }
    messagesFromUser.add(messageData);
    messageDataMap.put(user, messagesFromUser);
  }

  public List<MessageData> getMessagesFromUser(User user) {
    List<MessageData> messagesFromUser = messageDataMap.get(user);

    if (messagesFromUser == null) {
      Log.d(Constants.LOGTAG, "Found nothing");
      return Collections.emptyList();
    }

    for(User us : messageDataMap.keySet()){
      Log.d(Constants.LOGTAG, " " + user.equals(us));
    }

    return messagesFromUser;
  }

  public void deleteMessagesFromUser(User user){
    messageDataMap.remove(user);
  }


}
