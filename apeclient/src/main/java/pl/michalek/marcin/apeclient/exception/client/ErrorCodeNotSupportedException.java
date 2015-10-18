package pl.michalek.marcin.apeclient.exception.client;

/**
 * Thrown if provided error code does not correspond to any error code in error repository.
 * @see pl.michalek.marcin.apeclient.ape.error.ErrorRepository
 *
 * @author Marcin Michałek
 */
public class ErrorCodeNotSupportedException extends Exception{
  @Override
  public String getMessage() {
    return "Error code is invalid or error is not in the error repository.";
  }
}
