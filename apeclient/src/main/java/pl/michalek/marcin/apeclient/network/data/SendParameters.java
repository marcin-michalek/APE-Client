package pl.michalek.marcin.apeclient.network.data;

import com.google.gson.annotations.SerializedName;

/**
 * Parameters send along with SEND command.
 *
 * @author Marcin Michałek
 */
public class SendParameters extends BaseParameters{
  @SerializedName("msg")
  private String message;

  private String pipe;

  public SendParameters(String message, String pipe) {
    this.message = message;
    this.pipe = pipe;
  }
}
