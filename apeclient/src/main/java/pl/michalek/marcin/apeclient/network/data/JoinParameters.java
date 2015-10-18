package pl.michalek.marcin.apeclient.network.data;

/**
 * Parameters send along with JOIN command.
 *
 * @author Marcin Michałek
 */
public class JoinParameters extends BaseParameters{
  private String channels;

  public JoinParameters(String channels) {
    this.channels = channels;
  }
}
