package converter;

/**
 * Implementation to convert Integer to Hex String.
 */
public final class IntegerToHexConverter implements Converter {
  public String convert(String inputString) {
    return Long.toHexString(Long.valueOf(inputString));
  }
}
