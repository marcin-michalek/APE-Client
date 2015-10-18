package pl.michalek.marcin.apeclient.event;

import pl.michalek.marcin.apeclient.network.data.User;

/**
 * Informs that server returned new message.
 * Contains all the necessary information about the message.
 *
 * @author Marcin Michałek
 */
public class NewMessageEvent extends BaseMessageEvent{
  private User from;
  private User pipe;
  private String message;
  private long time;

  public NewMessageEvent(User from, String message, long time, User pipe) {
    this.from = from;
    this.message = message;
    this.time = time;
    this.pipe = pipe;
  }

  /**
   *
   * @return user form who the message is.
   */
  public User getFrom() {
    return from;
  }

  /**
   *
   * @return Message.
   */
  public String getMessage() {
    return message;
  }

  /**
   *
   * @return Time of the message in milliseconds.
   */
  public long getTime() {
    return time;
  }

  /**
   *
   * @return Pipe of the channel from where the message came.
   */
  public User getPipe() {
    return pipe;
  }
}
