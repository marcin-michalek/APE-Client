package pl.michalek.marcin.apeclient.ape.error;

/**
 * Enums for errors
 *
 * @author Marcin Michałek
 */
public enum APEErrorType {
  BAD_PARAMETERS,
  BAD_CMD,
  NO_CMD,
  BAD_SESSION_ID,
  BAD_JSON,
  BAD_NICK,
  NICK_USED,
  ALREADY_ON_CHANNEL,
  UNKNOWN_CHANNEL,
  NOT_IN_CHANNEL,
  UNKNOWN_PIPE,
  SET_LEVEL_ERROR,
  UNKNOWN_CONNECTION_ERROR,
  NO_DOMAIN,
  CANNOT_JOIN_CHANNEL,
  SESSION_ERROR,
  BAD_CHALLENGE
}