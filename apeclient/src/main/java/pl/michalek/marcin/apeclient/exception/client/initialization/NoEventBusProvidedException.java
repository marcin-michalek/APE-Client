package pl.michalek.marcin.apeclient.exception.client.initialization;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class NoEventBusProvidedException extends NoConfigurationProvidedException {
  @Override
  public String getMessage() {
    return "You must provide non null event bus.";
  }
}
