package pl.michalek.marcin.apeclient.network.request;

import pl.michalek.marcin.apeclient.network.data.BaseAPERequestData;
import pl.michalek.marcin.apeclient.network.response.MultiResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

import java.util.List;

/**
 * Interface for sending request to APE server.
 *
 * @author Marcin Michałek
 */
public interface RequestInterface {
      @POST("/")
      void sendRequestData(@Body List<BaseAPERequestData> requestDataList, Callback<MultiResponse> callback);
}
