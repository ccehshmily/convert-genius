package common;

/**
 * Enum storing all possible types within our converter.
 * Each ConvertableType value consists of:
 *   1. int value, which is the identifier of the type, will be used in
 *     frontend as the form return value.
 *   2. String name, which is a short name for this type, this will be used to
 *     construct Converter names.
 *   3. String desc, which is a more detailed description of the type, will
 *     be used to show in the options list in frontend, describing the meaning
 *     of this type.
 */
public enum ConvertableType {
  // Add new types here, and implement according Converters based on the
  // name given to the types.
  // For example, for TYPE1(1, Name1, "") and TYPE2(2, Name2, ""), implement
  // Name1ToName2Converter in package converter.
  INTEGERS(1, "Integer", "Number: decimal integer"),
  HEX(2, "Hex", "Number: hex integer");

  private int value;
  private String name;
  private String desc;

  private ConvertableType(int value, String name, String desc) {
    this.value = value;
    this.name = name;
    this.desc = desc;
  }

  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }
}
