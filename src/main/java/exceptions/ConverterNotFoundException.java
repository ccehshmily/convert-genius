package exceptions;

public class ConverterNotFoundException extends ConverterException {
  private static int response_code = 404;
  private String exception_message = "We currently don't support the conversion between
      these two types.";

  public ConverterNotFoundException(String inputType, String outputType) {
    exception_message =
        "We currently don't support converting from" + inputType + " to " + outputType + ".";
  }

  protected int getResponseCode() {
    return response_code;
  }

  protected String getExceptionMessage() {
    return exception_message;
  }
}
