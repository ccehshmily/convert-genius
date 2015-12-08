package exceptions;

public class ConverterNotFoundException extends ConverterException {
  private static int response_code = 404;
  private String exception_message = "We currently don't support the conversion between"
      + "these two types.";

  public ConverterNotFoundException(String inputType, String outputType) {
    exception_message =
        "We currently don't support converting from " + inputType + " to " + outputType + ".";
  }

  @Override
  public int getResponseCode() {
    return response_code;
  }

  @Override
  public String getExceptionMessage() {
    return exception_message;
  }
}
