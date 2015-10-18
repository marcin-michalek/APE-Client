package pl.michalek.marcin.apeclient.network.data;

import com.google.gson.annotations.SerializedName;
import pl.michalek.marcin.apeclient.ape.protocol.Command;

/**
 * APERequest data with session authentication added.
 *
 * @author Marcin Michałek
 */
public class AuthorizedAPERequestData extends BaseAPERequestData {
  @SerializedName("sessid")
  private String sessionId;

  public AuthorizedAPERequestData(Command command, int challenge, String sessionId, BaseParameters baseParameters) {
    super(command, challenge, baseParameters);
    this.sessionId = sessionId;
  }
}
