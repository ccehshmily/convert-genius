package exceptions;

/**
 * Base class for all exceptions that will be raised and handled in the converter.
 * Child classes can override and implement their own responseCode and exceptionMessage.
 */
public class ConverterException {
  private static int response_code = 500;
  private static String exception_message = "There's been a problem during the conversion";

  protected int getResponseCode() {
    return response_code;
  }

  protected String getExceptionMessage() {
    return exception_message;
  }
}
