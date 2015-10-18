package pl.michalek.marcin.apeclient.exception.server;

/**
 * Thrown when servers returns null
 *
 * @author Marcin Michałek
 */
public class NullResponseFromServerException extends Exception{
  @Override
  public String getMessage() {
    return "Server returned null response";
  }
}
