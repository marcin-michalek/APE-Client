package pl.michalek.marcin.chatdemo.config;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Constants for demo application.
 *
 * @author Marcin Michałek
 */
public class Constants {
  private Constants() {
  }

  //LOGTAG
  public final static String LOGTAG = "CHAT_DEMO";

  //EXTRAS
  public final static String EXTRA_NICK = "EXTRA_NICK";

  //IMAGES SHAPES
  public static final DisplayImageOptions IMAGE_CIRCLE = new DisplayImageOptions.Builder()
      .resetViewBeforeLoading(false)
      .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
      .cacheInMemory(false)
      .cacheOnDisc(true)
      .displayer(new RoundedBitmapDisplayer(45))
      .build();

  public static final DisplayImageOptions IMAGE_SQUARE = new DisplayImageOptions.Builder()
      .resetViewBeforeLoading(false)
      .cacheInMemory(false)
      .cacheOnDisc(true)
      .displayer(new FadeInBitmapDisplayer(100))
      .build();

  //URI
  public static final String URI_DRAWABLE = "drawable://";

  //MESSAGES
  public static final int MESSAGE_VIEW_TYPE_OUTSIDE = 0;
  public static final int MESSAGE_VIEW_TYPE_MY = 1;
  public static final int MESSAGE_VIEW_TYPE_UNSUPPORTED = -1;


  //OTHER
  public static final String CHARACTER_SPACE = " ";

  //APE CHAT
  public static final int CHAT_BASE_INTERVAL = 5000;
  public static final String CHAT_ROOT_URL = "http://ape-project.org:6969";
}
