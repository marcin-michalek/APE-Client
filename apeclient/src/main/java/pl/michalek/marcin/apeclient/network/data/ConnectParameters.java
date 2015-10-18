package pl.michalek.marcin.apeclient.network.data;

/**
 * Parameters send along with CONNECT command.
 *
 * @author Marcin Michałek
 */
public class ConnectParameters extends BaseParameters {
  public String name;

  public ConnectParameters(String name) {
    this.name = name;
  }
}
