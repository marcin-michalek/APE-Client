package pl.michalek.marcin.apeclient;

/**
 * Class for the library to keep current connection parameters
 *
 * @author Marcin Michałek
 */
public class CurrentConnectionParameters {
  private String currentSessionId;
  private String currentPipe;

  public String getCurrentSessionId() {
    return currentSessionId;
  }

  public void setCurrentSessionId(String currentSessionId) {
    this.currentSessionId = currentSessionId;
  }

  public String getCurrentPipe() {
    return currentPipe;
  }

  public void setCurrentPipe(String currentPipe) {
    this.currentPipe = currentPipe;
  }
}
