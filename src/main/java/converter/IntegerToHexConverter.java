package converter;

public final class IntegerToHexConverter implements Converter {
  public String convert(String inputString) {
    return Long.toHexString(Long.valueOf(inputString));
  }
}
