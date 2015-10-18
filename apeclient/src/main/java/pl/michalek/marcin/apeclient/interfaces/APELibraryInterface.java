package pl.michalek.marcin.apeclient.interfaces;

/**
 * Interface for the library.
 * It implements all the methods, which are basically CMDs for the server.
 *
 * @author Marcin Michałek
 */
public interface APELibraryInterface {
  void connect(String nickName);
  void joinChannel(String channels);
  void leave();
  void check();
  void sendMessage(String message);
  void sendMessage(String message, String pipe);
  void setCurrentPipe(String currentPipe);
}
