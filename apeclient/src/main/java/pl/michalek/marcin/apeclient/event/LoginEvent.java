package pl.michalek.marcin.apeclient.event;

/**
 * Informs that server returned LOGIN RAW.
 *
 * @author Marcin Michałek
 */
public class LoginEvent extends BaseEvent {
  private String sessionId;

  public LoginEvent(String sessionId) {
    this.sessionId = sessionId;
  }

  /**
   * Returns users session id.
   *
   * @return session id
   */
  public String getSessionId() {
    return sessionId;
  }
}
