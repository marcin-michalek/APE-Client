package pl.michalek.marcin.apeclient.network.data;

/**
 * Properties received along with APE User.
 *
 * @author Marcin Michałek
 */
public class Properties {
  public String name;

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return other != null
        && this.getClass() == other.getClass()
        && name.equals(((Properties) other).name);
  }
}
