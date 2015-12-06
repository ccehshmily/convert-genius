package common;

public enum ConvertableType {
  INTEGERS(1, "integer"),
  HEX(2, "hex");

  private int mValue;
  private String mName;

  private ConvertableType(int value, String name) {
    mValue = value;
    mName = name;
  }

  public int getValue() {
    return mValue;
  }

  public String getName() {
    return mName;
  }
}
