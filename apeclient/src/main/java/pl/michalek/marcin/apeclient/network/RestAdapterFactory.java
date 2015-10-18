package pl.michalek.marcin.apeclient.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.converter.GsonConverter;

/**
 * Factory for rest adapter.
 *
 * @author Marcin Michałek
 */
public class RestAdapterFactory {
  public static retrofit.RestAdapter getInstance(String chatRootURL) {
    Gson gson = new GsonBuilder()
        //type adapter ...
        .create();

    return new retrofit.RestAdapter.Builder()
        .setEndpoint(chatRootURL)
        .setConverter(new GsonConverter(gson))
        .build();
  }
}
