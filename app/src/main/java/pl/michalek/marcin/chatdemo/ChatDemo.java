package pl.michalek.marcin.chatdemo;

import android.app.Application;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Main application class
 *
 * @author Marcin Michałek
 */
public class ChatDemo extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    configureImageLoader();
  }

  private void configureImageLoader() {

    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
        .build();
    ImageLoader.getInstance().init(config);
  }
}
