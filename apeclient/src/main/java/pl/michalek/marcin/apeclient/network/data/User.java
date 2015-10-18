package pl.michalek.marcin.apeclient.network.data;

import com.google.gson.annotations.SerializedName;

/**
 * User object received from APE server.
 *
 * @author Marcin Michałek
 */
public class User {
  @SerializedName("casttype")
  public String castType;

  @SerializedName("pubid")
  public String publicId;

  public Properties properties;

  @Override
  public boolean equals(Object other) {
    return other != null
        && this.getClass() == other.getClass()
        && castType.equals(((User) other).castType)
        && publicId.equals(((User) other).publicId)
        && properties.equals(((User) other).properties);
  }

  @Override
  public int hashCode() {
    return castType.hashCode() +
        publicId.hashCode() +
        properties.hashCode();
  }
}
