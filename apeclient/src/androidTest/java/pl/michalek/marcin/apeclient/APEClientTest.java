package pl.michalek.marcin.apeclient;

import android.test.AndroidTestCase;
import de.greenrobot.event.EventBus;
import org.junit.After;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import pl.michalek.marcin.apeclient.event.*;
import pl.michalek.marcin.apeclient.exception.client.initialization.NoConfigurationProvidedException;
import pl.michalek.marcin.apeclient.exception.server.NullResponseFromServerException;
import pl.michalek.marcin.apeclient.interfaces.ConfigurationInterface;
import pl.michalek.marcin.apeclient.network.data.provider.ServerResponseDataProvider;
import retrofit.RetrofitError;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Class with tests for basic methods of ApeClient
 *
 * @author Marcin Michałek
 */
public class APEClientTest extends AndroidTestCase {
  private EventBus eventBus;
  private APEClient apeClientSpy;
  private APEClientTest apeClientTestSpy;

  @Before
  public void setUp() throws Exception {
    System.setProperty("dexmaker.dexcache", getContext().getCacheDir().toString());
    apeClientTestSpy = spy(this);
    setUpApeClientSpy();
  }

  private void setUpApeClientSpy() {
    eventBus = new EventBus();
    eventBus.register(apeClientTestSpy);

    apeClientSpy = spy(new APEClient(eventBus, new ConfigurationInterface() {
      @Override
      public String getRootChatURL() {
        return "http://ape-project.org:6969";
      }

      @Override
      public int getBaseChatCheckRequestInterval() {
        return 5000;
      }
    }));

  }

  @After
  public void tearDown() throws Exception {

  }

  /**
   * Method intercepting all events which ApeClient sends to the user.
   * Used for verification if correct event is generated based on response from server.
   *
   * @param baseEvent any type of event send to the bus
   */
  public void onEvent(BaseEvent baseEvent) {

  }

  public void testApeClientSendsCorrectEvents_shouldPostCorrectEvents() throws Exception {
    InOrder testCorrectEvent = inOrder(apeClientTestSpy);
    ArgumentCaptor<BaseEvent> argumentCaptor = ArgumentCaptor.forClass(BaseEvent.class);

    apeClientSpy.sendEventToClient(ServerResponseDataProvider.provideCorrectLoginResponse());
    testCorrectEvent.verify(apeClientTestSpy).onEvent(argumentCaptor.capture());
    assertEquals(LoginEvent.class.getName(), argumentCaptor.getValue().getClass().getName());

    apeClientSpy.sendEventToClient(ServerResponseDataProvider.provideCorrectIdentificationResponse());
    testCorrectEvent.verify(apeClientTestSpy).onEvent(argumentCaptor.capture());
    assertEquals(IdentificationEvent.class.getName(), argumentCaptor.getValue().getClass().getName());

    apeClientSpy.sendEventToClient(ServerResponseDataProvider.provideCorrectCloseResponse());
    testCorrectEvent.verify(apeClientTestSpy).onEvent(argumentCaptor.capture());
    assertEquals(CloseEvent.class.getName(), argumentCaptor.getValue().getClass().getName());

    apeClientSpy.sendEventToClient(ServerResponseDataProvider.provideCorrectErrorResponse());
    testCorrectEvent.verify(apeClientTestSpy).onEvent(argumentCaptor.capture());
    assertEquals(ErrorEvent.class.getName(), argumentCaptor.getValue().getClass().getName());

    apeClientSpy.sendEventToClient(ServerResponseDataProvider.provideCorrectNewMessageResponse());
    testCorrectEvent.verify(apeClientTestSpy).onEvent(argumentCaptor.capture());
    assertEquals(NewMessageEvent.class.getName(), argumentCaptor.getValue().getClass().getName());
  }

  public void testApeClientWithNullServerResponse_shouldThrowNullServerResponseException() throws Exception {
    try {
      apeClientSpy.sendEventToClient(ServerResponseDataProvider.provideNullServerResponse());
    } catch (Exception exception) {
      assertThat(exception instanceof NullResponseFromServerException);
    }
  }

  public void testConnectionError_shouldPostConnectionErrorEvent() throws Exception {
    apeClientSpy.failure(RetrofitError.networkError("test-url", new ConnectException()));
    apeClientSpy.failure(RetrofitError.networkError("test-url", new SocketTimeoutException()));
    verify(apeClientTestSpy, times(2)).onEvent(any(ConnectionErrorEvent.class));
  }

  public void testCheckParametersWithNullEventBus_shouldThrowNoConfigurationProvidedException() throws Exception {
    try {
      apeClientSpy.verifyParameters(null, null);
    } catch (Exception exception) {
      assertThat(exception instanceof NoConfigurationProvidedException);
    }
  }
}