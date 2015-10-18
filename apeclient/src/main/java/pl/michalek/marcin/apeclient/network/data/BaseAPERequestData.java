package pl.michalek.marcin.apeclient.network.data;

import com.google.gson.annotations.SerializedName;
import pl.michalek.marcin.apeclient.ape.protocol.Command;

/**
 * Base commands send to the APE server in every request.
 *
 * @author Marcin Michałek
 */
public class BaseAPERequestData {
  @SerializedName("cmd")
  private Command command;

  @SerializedName("chl")
  private int challenge;

  @SerializedName("params")
  private BaseParameters parameters;

  public BaseAPERequestData(Command command, int challenge, BaseParameters baseParameters) {
    this.command = command;
    this.challenge = challenge;
    this.parameters = baseParameters;
  }
}
