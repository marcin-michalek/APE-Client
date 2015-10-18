package pl.michalek.marcin.apeclient.ape.error;

/**
 * Error type and description
 *
 * @author Marcin Michałek
 */
public class APEError {
  private String errorMessage;
  private APEErrorType errorType;

  public APEError(APEErrorType errorType, String errorMessage) {
    this.errorMessage = errorMessage;
    this.errorType = errorType;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public APEErrorType getErrorType() {
    return errorType;
  }
}
