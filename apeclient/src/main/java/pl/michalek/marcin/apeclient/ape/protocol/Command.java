package pl.michalek.marcin.apeclient.ape.protocol;

import com.google.gson.annotations.SerializedName;

/**
 * Enum containing all APE protocol commands (CMDs).
 */
public enum Command {
  @SerializedName("JOIN")
  JOIN,

  @SerializedName("CONNECT")
  CONNECT,

  @SerializedName("SEND")
  SEND,

  @SerializedName("CHECK")
  CHECK,

  @SerializedName("SESSION")
  SESSION,

  @SerializedName("LEFT")
  LEFT
}
