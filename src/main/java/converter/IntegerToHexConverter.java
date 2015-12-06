package converter;

public final class IntegerToHexConverter implements Converter {
  public String convert(String inputString) {
    return "hexed" + inputString;
  }
}
