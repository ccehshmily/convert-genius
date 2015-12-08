package exceptions;

/**
 * Exception for invalid query params.
 */
public final class InvalidQueryParamsException extends ConverterException {
  private static int response_code = 400;
  private static String exception_message = "The query params for conversion are not correct.";

  @Override
  protected int getResponseCode() {
    return response_code;
  }

  @Override
  protected String getExceptionMessage() {
    return exception_message;
  }
}
