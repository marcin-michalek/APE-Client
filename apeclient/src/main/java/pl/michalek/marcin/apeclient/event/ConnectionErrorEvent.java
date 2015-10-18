package pl.michalek.marcin.apeclient.event;

import retrofit.client.Response;

/**
 * Error in connection reserved for Retrofit
 *
 * @author Marcin Michałek
 */
public class ConnectionErrorEvent extends BaseEvent {
  private Response response;

  public ConnectionErrorEvent(Response response) {
    this.response = response;
  }

  public int getStatusCode() {
    return response.getStatus();
  }

  public String getReason() {
    return response.getReason();
  }
}
