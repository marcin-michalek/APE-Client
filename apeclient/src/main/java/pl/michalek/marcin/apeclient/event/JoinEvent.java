package pl.michalek.marcin.apeclient.event;

import pl.michalek.marcin.apeclient.network.data.User;

/**
 * Informs that server returned JOIN RAW.
 * Contains user that joined.
 *
 * @author Marcin Michałek
 */
public class JoinEvent extends BaseEvent {
  private User user;

  public JoinEvent(User user) {
    this.user = user;
  }

  /**
   *
   * @return User that joined.
   */
  public User getUser() {
    return user;
  }
}
