package pl.michalek.marcin.apeclient;

import android.os.Handler;
import android.util.Log;
import de.greenrobot.event.EventBus;
import pl.michalek.marcin.apeclient.ape.Action;
import pl.michalek.marcin.apeclient.ape.protocol.Command;
import pl.michalek.marcin.apeclient.config.Constants;
import pl.michalek.marcin.apeclient.event.*;
import pl.michalek.marcin.apeclient.exception.client.initialization.NoConfigurationInterfaceProvidedException;
import pl.michalek.marcin.apeclient.exception.client.initialization.NoConfigurationProvidedException;
import pl.michalek.marcin.apeclient.exception.client.initialization.NoEventBusProvidedException;
import pl.michalek.marcin.apeclient.exception.server.NullResponseFromServerException;
import pl.michalek.marcin.apeclient.interfaces.APELibraryInterface;
import pl.michalek.marcin.apeclient.interfaces.ConfigurationInterface;
import pl.michalek.marcin.apeclient.network.RestAdapterFactory;
import pl.michalek.marcin.apeclient.network.data.*;
import pl.michalek.marcin.apeclient.network.request.RequestInterface;
import pl.michalek.marcin.apeclient.network.response.BaseResponse;
import pl.michalek.marcin.apeclient.network.response.MultiResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Main library class, implementing APELibraryInterface.
 *
 * @author Marcin Michałek
 * @see pl.michalek.marcin.apeclient.interfaces.APELibraryInterface
 */
public class APEClient implements APELibraryInterface, Callback<MultiResponse> {
  private EventBus eventBus;
  private ConfigurationInterface configuration;
  private int challenge;
  private RequestInterface requestInterface;
  private Handler checkHandler;
  private Runnable checkRunnable;
  private CurrentConnectionParameters currentConnectionParameters;

  public APEClient(EventBus eventBus, ConfigurationInterface configuration) {
    try {
      verifyParameters(eventBus, configuration);
    } catch (NoConfigurationProvidedException exception) {
      Log.e(Constants.LOGTAG, exception.getMessage());
    }

    initParameters(eventBus, configuration);
  }

  public void setParameters(EventBus eventBus, ConfigurationInterface configuration) {
    try {
      verifyParameters(eventBus, configuration);
    } catch (NoConfigurationProvidedException exception) {
      Log.e(Constants.LOGTAG, exception.getMessage());
    }

    this.eventBus = eventBus;
    this.configuration = configuration;
  }

  private void initParameters(EventBus eventBus, ConfigurationInterface configuration) {
    this.eventBus = eventBus;
    this.configuration = configuration;
    this.requestInterface = RestAdapterFactory.getInstance(configuration.getRootChatURL()).create(RequestInterface.class);
    this.challenge = 0;
    currentConnectionParameters = new CurrentConnectionParameters();

    checkHandler = new Handler();
    checkRunnable = new Runnable() {
      @Override
      public void run() {
        check();
      }
    };
  }

  public void verifyParameters(EventBus eventBus, ConfigurationInterface configuration) throws NoConfigurationProvidedException {
    if (configuration == null) {
      throw new NoConfigurationInterfaceProvidedException();
    }

    if (eventBus == null) {
      throw new NoEventBusProvidedException();
    }
  }


  @Override
  public void success(MultiResponse multiResponse, Response response) {
    for (BaseResponse baseResponse : multiResponse) {
      try {
        sendEventToClient(baseResponse);
      } catch (NullResponseFromServerException exception) {
        Log.e(Constants.LOGTAG, exception.getMessage(), exception);
      }
    }
  }

  @Override
  public void failure(RetrofitError error) {
    eventBus.post(new ConnectionErrorEvent(error.getResponse()));
  }

  public void sendEventToClient(BaseResponse baseResponse) throws NullResponseFromServerException {
    if (baseResponse != null) {
      switch (baseResponse.raw) {
        case LOGIN:
          currentConnectionParameters.setCurrentSessionId(baseResponse.data.sessionId);
          eventBus.post(new LoginEvent(baseResponse.data.sessionId));
          break;
        case IDENTIFICATION:
          eventBus.post(new IdentificationEvent());
          break;
        case CLOSE:
          eventBus.post(new CloseEvent());
          checkAfterInterval();
          break;
        case CHANNEL:
          currentConnectionParameters.setCurrentPipe(baseResponse.data.pipe.publicId);
          eventBus.post(new ChannelEvent(baseResponse.data.users, baseResponse.data.pipe));
          check();
          checkAfterInterval();
          break;
        case ERROR:
          eventBus.post(new ErrorEvent(baseResponse.data.code));
          break;
        case DATA:
          eventBus.post(new NewMessageEvent(baseResponse.data.from, URLDecoder.decode(baseResponse.data.message), baseResponse.time, baseResponse.data.pipe));
          check();
          break;
        case JOIN:
          eventBus.post(new JoinEvent(baseResponse.data.user));
          check();
          break;
        case LEFT:
          eventBus.post(new LeftEvent(baseResponse.data.user));
          check();
      }
    } else {
      throw new NullResponseFromServerException();
    }
  }


  @Override
  public void leave() {
    //TODO
  }


  public void checkAfterInterval() {
    checkHandler.postDelayed(checkRunnable, configuration.getBaseChatCheckRequestInterval());
  }

  /* Below there are chat method located. They correspond to the user actions. */

  /**
   * Sends all requests to server.
   *
   * @param baseAPERequestData data send in the request
   */
  private void sendRequestToServer(BaseAPERequestData baseAPERequestData) {
    requestInterface.sendRequestData(
        RequestDataFactory.createRequestDataList(baseAPERequestData),
        this);
  }

  /**
   * Connects to the server with specified nickname.
   *
   * @param nickName nickname
   */

  @Override
  public void connect(String nickName) {
    sendRequestToServer(
        new BaseAPERequestData(Command.CONNECT, ++challenge, new ConnectParameters(nickName))
    );
  }

  /**
   * Joins specified channels.
   *
   * @param channels channels names separated with comma
   */
  @Override
  public void joinChannel(String channels) {
    sendRequestToServer(
        new AuthorizedAPERequestData(Command.JOIN, ++challenge, currentConnectionParameters.getCurrentSessionId(),
            new JoinParameters(channels))
    );
  }

  /**
   * Send a message to the current pipe.
   *
   * @param message message to send
   */
  @Override
  public void sendMessage(String message) {
    sendRequestToServer(
        new AuthorizedAPERequestData(Command.SEND, ++challenge, currentConnectionParameters.getCurrentSessionId(),
            new SendParameters(URLEncoder.encode(message), currentConnectionParameters.getCurrentPipe()))
    );
  }

  /**
   * Sends a message to the specified pipe
   *
   * @param message message to send
   * @param pipe    pipe to send message
   */
  @Override
  public void sendMessage(String message, String pipe) {
    sendRequestToServer(
        new AuthorizedAPERequestData(Command.SEND, ++challenge, currentConnectionParameters.getCurrentSessionId(),
            new SendParameters(message, pipe))
    );
  }

  /**
   * Check request with the current session id.
   */
  @Override
  public void check() {
    sendRequestToServer(
        new AuthorizedAPERequestData(Command.CHECK, ++challenge, currentConnectionParameters.getCurrentSessionId(),
            new CheckParameters())
    );
  }

  /**
   * Sets the current pipe.
   *
   * @param currentPipe current pipe
   */
  @Override
  public void setCurrentPipe(String currentPipe) {
    sendRequestToServer(
        new AuthorizedAPERequestData(Command.SESSION, ++challenge, currentConnectionParameters.getCurrentSessionId(),
            new SessionParameters(Action.SET, new Values(currentPipe)))
    );
  }
}
