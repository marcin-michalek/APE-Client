package pl.michalek.marcin.apeclient.event;

import pl.michalek.marcin.apeclient.network.data.User;

import java.util.List;

/**
 * Event informing that server returned CHANNEL response.
 * It contains all necessary data from server.
 *
 * @author Marcin Michałek
 */
public class ChannelEvent extends BaseEvent {
  private List<User> usersList;
  private User pipe;

  public ChannelEvent(List<User> usersList, User pipe) {
    this.usersList = usersList;
    this.pipe = pipe;
  }

  /**
   *
   * @return Users list present at specified channel.
   */
  public List<User> getUsersList() {
    return usersList;
  }

  /**
   *
   * @return Pipe for that channel
   */
  public User getPipe() {
    return pipe;
  }
}
