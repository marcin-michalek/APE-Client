package pl.michalek.marcin.apeclient.interfaces;

/**
 * Activities containing chat must implement this interface.
 * After every server response an event is dispatched to methods from the interface.
 * Every event contains relevant data from the server.
 * @see pl.michalek.marcin.apeclient.event.LoginEvent
 * @see pl.michalek.marcin.apeclient.event.IdentificationEvent
 * @see pl.michalek.marcin.apeclient.event.CloseEvent
 * @see pl.michalek.marcin.apeclient.event.NewMessageEvent
 * @see pl.michalek.marcin.apeclient.event.ErrorEvent
 * @see pl.michalek.marcin.apeclient.event.ConnectionErrorEvent
 * @see pl.michalek.marcin.apeclient.event.ChannelEvent
 * @see pl.michalek.marcin.apeclient.event.JoinEvent
 * @see pl.michalek.marcin.apeclient.event.LeftEvent
 *
 * @author Marcin Michałek
 */
import pl.michalek.marcin.apeclient.event.*;

public interface APEChatCommunicationInterface {
  void onEvent(LoginEvent loginEvent);
  void onEvent(IdentificationEvent identificationEvent);
  void onEvent(CloseEvent identificationEvent);
  void onEvent(NewMessageEvent newMessageEvent);
  void onEvent(ErrorEvent errorEvent);
  void onEvent(ChannelEvent channelEvent);
  void onEvent(JoinEvent channelEvent);
  void onEvent(ConnectionErrorEvent connectionErrorEvent);
  void onEvent(LeftEvent leftEvent);
}
