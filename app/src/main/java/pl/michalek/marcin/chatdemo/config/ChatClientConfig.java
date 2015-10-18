package pl.michalek.marcin.chatdemo.config;

import pl.michalek.marcin.apeclient.interfaces.ConfigurationInterface;

/**
 * Class containing initial setup for APE Client.
 * Contains chat root url and base request interval;
 *
 * @author Marcin Michałek
 */
public class ChatClientConfig implements ConfigurationInterface{
  @Override
  public String getRootChatURL() {
    return Constants.CHAT_ROOT_URL;
  }

  @Override
  public int getBaseChatCheckRequestInterval() {
    return Constants.CHAT_BASE_INTERVAL;
  }
}
