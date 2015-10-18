package pl.michalek.marcin.apeclient.ape.error;

import android.util.SparseArray;
import pl.michalek.marcin.apeclient.exception.client.ErrorCodeNotSupportedException;

/**
 * Class containing list of all error codes with descriptions.
 *
 * @author Marcin Michałek
 */
public class ErrorRepository {
  /**
   * Array with mapping errorCode -> errorMessage
   */
  private static SparseArray<APEError> errorCodes;

  static {
    errorCodes = new SparseArray<>();
    errorCodes.put(1, new APEError(APEErrorType.BAD_PARAMETERS, "Bad parameters - A needed parameter is missing."));
    errorCodes.put(2, new APEError(APEErrorType.BAD_CMD, "Bad cmd - Unknown command has been sent."));
    errorCodes.put(3, new APEError(APEErrorType.NO_CMD, "No cmd - Did not gave a cmd. Maybe you forgot\n" +
        "  to pass your command in an array."));
    errorCodes.put(4, new APEError(APEErrorType.BAD_SESSION_ID, "Bad session id - Unknown session id has been sent."));
    errorCodes.put(5, new APEError(APEErrorType.BAD_JSON, "Bad json - The JSON is not well formatted."));
    errorCodes.put(6, new APEError(APEErrorType.BAD_NICK, "Bad nick - Nick names can not be\n" +
        "  more than 16 characters and has to be\n" +
        "  only alpha numeric."));
    errorCodes.put(7, new APEError(APEErrorType.NICK_USED, "Nick used - The JSON is not well formatted."));
    errorCodes.put(100, new APEError(APEErrorType.ALREADY_ON_CHANNEL, "Already on channel."));
    errorCodes.put(103, new APEError(APEErrorType.UNKNOWN_CHANNEL, "Unknown channel."));
    errorCodes.put(101, new APEError(APEErrorType.NOT_IN_CHANNEL, "Not in channel."));
    errorCodes.put(109, new APEError(APEErrorType.UNKNOWN_PIPE, "Unknown pipe."));
    errorCodes.put(110, new APEError(APEErrorType.SET_LEVEL_ERROR, "Set level error."));
    errorCodes.put(200, new APEError(APEErrorType.UNKNOWN_CONNECTION_ERROR, "Unknown connection error."));
    errorCodes.put(201, new APEError(APEErrorType.NO_DOMAIN, "No domain."));
    errorCodes.put(202, new APEError(APEErrorType.CANNOT_JOIN_CHANNEL, "Cannot join channel."));
    errorCodes.put(203, new APEError(APEErrorType.SESSION_ERROR, "Session error."));
    errorCodes.put(250, new APEError(APEErrorType.BAD_CHALLENGE, "Bad challenge (chl)."));

  }

  /**
   * Returns error message string for specified error code.
   *
   * @param code Error code
   * @return Message for specified code
   * @throws pl.michalek.marcin.apeclient.exception.client.ErrorCodeNotSupportedException when error code is not known
   */
  public static String getErrorMessage(int code) throws ErrorCodeNotSupportedException {
    if (errorCodes.get(code) != null) {
      return errorCodes.get(code).getErrorMessage();
    } else {
      throw new ErrorCodeNotSupportedException();
    }
  }

  /**
   * Returns error type enum
   *
   * @param code error code
   * @return error type enum
   * @throws pl.michalek.marcin.apeclient.exception.client.ErrorCodeNotSupportedException when error code is not known
   */
  public static APEErrorType getErrorType(int code) throws ErrorCodeNotSupportedException {
    if (errorCodes.get(code) != null) {
      return errorCodes.get(code).getErrorType();
    } else {
      throw new ErrorCodeNotSupportedException();
    }
  }
}
