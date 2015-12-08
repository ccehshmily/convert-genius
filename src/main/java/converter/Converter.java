package converter;

/**
 * The interface for all Converters to implement.
 *
 * This will be used by common.ConverterHelper to invoke function convert().
 * Upon invokation, ConvertHelper will find the correct implementation of this.
 */
public interface Converter {
  public String convert(String inputString);
}
