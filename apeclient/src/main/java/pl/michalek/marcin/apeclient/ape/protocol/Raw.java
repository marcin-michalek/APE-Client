package pl.michalek.marcin.apeclient.ape.protocol;

import com.google.gson.annotations.SerializedName;

/**
 * Enum containing all APE protocol server responses (RAWs).
 */
public enum Raw {
  @SerializedName("LOGIN")
  LOGIN,

  @SerializedName("IDENT")
  IDENTIFICATION,

  @SerializedName("CLOSE")
  CLOSE,

  @SerializedName("CHANNEL")
  CHANNEL,

  @SerializedName("ERR")
  ERROR,

  @SerializedName("DATA")
  DATA,

  @SerializedName("JOIN")
  JOIN,

  @SerializedName("LEFT")
  LEFT
}
