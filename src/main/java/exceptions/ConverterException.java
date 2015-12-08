package exceptions;

/**
 * Base class for all exceptions that will be raised and handled in the converter.
 * Child classes can override and implement their own responseCode and exceptionMessage.
 */
public class ConverterException extends Exception {
  private static int response_code = 500;
  private static String exception_message = "There's been a problem during the conversion";

  /**
   * Used for indicating the frontend whether this convert has succeeded.
   */
  public int getResponseCode() {
    return response_code;
  }

  /**
   * Used for showing an explanation message to users.
   * This will be visible to users, choose your words.
   */
  public String getExceptionMessage() {
    return exception_message;
  }
}
