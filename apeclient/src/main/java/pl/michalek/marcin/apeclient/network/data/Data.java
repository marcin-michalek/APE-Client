package pl.michalek.marcin.apeclient.network.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Data from APE chat server response
 *
 * @author Marcin Michałek
 */
public class Data {
  @SerializedName("sessid")
  public String sessionId;

  public User user;

  public List<User> users;

  public User pipe;

  //error code
  public String code;

  //error value
  public String value;

  public User from;

  @SerializedName("msg")
  public String message;
}
