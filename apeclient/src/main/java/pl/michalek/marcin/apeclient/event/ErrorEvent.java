package pl.michalek.marcin.apeclient.event;

import android.util.Log;
import pl.michalek.marcin.apeclient.ape.error.APEErrorType;
import pl.michalek.marcin.apeclient.ape.error.ErrorRepository;
import pl.michalek.marcin.apeclient.config.Constants;
import pl.michalek.marcin.apeclient.exception.client.ErrorCodeNotSupportedException;

/**
 * Informs that server returned error.
 * Contains error code and error message.
 *
 * @author Marcin Michałek
 */
public class ErrorEvent extends BaseEvent {
  private int code;
  private String message;
  private APEErrorType apeErrorType;

  public ErrorEvent(String code) {
    this.code = Integer.valueOf(code);
    try {
      this.message = ErrorRepository.getErrorMessage(this.code);
      this.apeErrorType = ErrorRepository.getErrorType(this.code);
    } catch (ErrorCodeNotSupportedException e) {
      Log.e(Constants.LOGTAG, "Cannot get error details: + " + e.getMessage());
    }
  }

  /**
   * @return Error code.
   */
  public int getCode() {
    return code;
  }

  /**
   * @return Error message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * @return Error type.
   */
  public APEErrorType getApeErrorType() {
    return apeErrorType;
  }
}
