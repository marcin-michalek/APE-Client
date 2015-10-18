package pl.michalek.marcin.apeclient.event;

import pl.michalek.marcin.apeclient.network.data.User;

/**
 * Informs that server returned LEFT RAW.
 * Contains user that left.
 *
 * @author Marcin Michałek
 */
public class LeftEvent extends BaseEvent{
  private User user;

  public LeftEvent(User user) {
    this.user = user;
  }

  /**
   *
   * @return User that left.
   */
  public User getUser() {
    return user;
  }
}
