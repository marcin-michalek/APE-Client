package pl.michalek.marcin.apeclient.network.data;

import pl.michalek.marcin.apeclient.ape.Action;

/**
 * TODO Add class description...
 *
 * @author Marcin Michałek
 */
public class SessionParameters extends BaseParameters {
  private Action action;
  private Values values;

  public SessionParameters(Action action, Values values) {
    this.action = action;
    this.values = values;
  }
}
