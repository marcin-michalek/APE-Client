package pl.michalek.marcin.apeclient.network.data.provider;

import pl.michalek.marcin.apeclient.ape.protocol.Raw;
import pl.michalek.marcin.apeclient.network.data.Data;
import pl.michalek.marcin.apeclient.network.data.Properties;
import pl.michalek.marcin.apeclient.network.data.User;
import pl.michalek.marcin.apeclient.network.response.BaseResponse;

/**
 * Class providing mock responses from server
 *
 * @author Marcin Michałek
 */
public class ServerResponseDataProvider {
  public static BaseResponse provideCorrectLoginResponse() {
    BaseResponse loginBaseResponse = new BaseResponse();
    loginBaseResponse.raw = Raw.LOGIN;
    loginBaseResponse.time = 1417774922L;

    Data data = new Data();
    data.sessionId = "6890d94de88843b3c87be9caa92e66e2";
    loginBaseResponse.data = data;

    return loginBaseResponse;
  }

  public static BaseResponse provideCorrectIdentificationResponse() {
    BaseResponse identificationBaseResponse = new BaseResponse();
    identificationBaseResponse.raw = Raw.IDENTIFICATION;
    identificationBaseResponse.time = 1417774922L;

    Properties properties = new Properties();
    properties.name = "name";

    User user = new User();
    user.castType = "uni";
    user.publicId = "0145ff915132ce7b2b8febf8fad859db";
    user.properties = properties;

    Data data = new Data();
    data.user = user;
    identificationBaseResponse.data = data;

    return identificationBaseResponse;
  }

  public static BaseResponse provideCorrectCloseResponse() {
    BaseResponse closeBaseResponse = new BaseResponse();
    closeBaseResponse.raw = Raw.CLOSE;
    closeBaseResponse.time = 1417774922L;

    Data data = new Data();
    data.value = null;
    closeBaseResponse.data = data;

    return closeBaseResponse;
  }

  public static BaseResponse provideCorrectErrorResponse() {
    BaseResponse errorBaseResponse = new BaseResponse();
    errorBaseResponse.raw = Raw.ERROR;
    errorBaseResponse.time = 1417774922L;

    Data data = new Data();
    data.value = "error string";
    data.code = "1";
    errorBaseResponse.data = data;

    return errorBaseResponse;
  }

  public static BaseResponse provideCorrectNewMessageResponse() {
    BaseResponse messageBaseResponse = new BaseResponse();
    messageBaseResponse.raw = Raw.DATA;
    messageBaseResponse.time = 1417774922L;

    Properties properties = new Properties();
    properties.name = "name";

    User user = new User();
    user.castType = "uni";
    user.publicId = "0145ff915132ce7b2b8febf8fad859db";
    user.properties = properties;

    Data data = new Data();
    data.from = user;
    data.pipe = user;
    data.message = "message body";
    messageBaseResponse.data = data;

    return messageBaseResponse;
  }

  public static BaseResponse provideNullServerResponse() {
    return null;
  }
}
