package pl.michalek.marcin.chatdemo.adapter.data;

import org.joda.time.DateTime;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class MessageData {
  private MessageType messageType;
  private DateTime messageDateTime;
  private String fromWho;
  private String message;

  public MessageData(long messageTimeMillis, MessageType messageType, String fromWho, String message) {
    this.messageType = messageType;
    this.messageDateTime = new DateTime(messageTimeMillis);
    this.fromWho = fromWho;
    this.message = message;
  }

  public MessageType getMessageType() {
    return messageType;
  }

  public DateTime getMessageDateTime() {
    return messageDateTime;
  }

  public String getFromWho() {
    return fromWho;
  }

  public String getMessage() {
    return message;
  }
}
