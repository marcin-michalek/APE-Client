package pl.michalek.marcin.apeclient.exception.client.initialization;

/**
 * Exception thrown when user does not provide configuration class implementing configuration interface.
 *
 * @author Marcin Michałek
 */
public class NoConfigurationInterfaceProvidedException extends NoConfigurationProvidedException {
  @Override
  public String getMessage() {
    return "You must provide non null configuration to APE Client. Configuration passed to APE Client must implement pl.michalek.marcin.apeclient.config.ConfigurationInterface";
  }
}
