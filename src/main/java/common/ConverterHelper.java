package common;

import converter.Converter;

import java.util.HashMap;

public final class ConverterHelper {
  private String inputString;
  private String outputString;
  private int inputType;
  private int outputType;

  private static final HashMap<Integer, HashMap<Integer, String>> CONVERTER_MAP;
  static {
    CONVERTER_MAP = new HashMap<Integer, HashMap<Integer, String>>();
    for (ConvertableType convertableType1 : ConvertableType.values()) {
      int id1 = convertableType1.getValue();
      String name1 = convertableType1.getName();
      HashMap<Integer, String> innerMap = new HashMap<Integer, String>();
      for (ConvertableType convertableType2 : ConvertableType.values()) {
        int id2 = convertableType2.getValue();
        String name2 = convertableType2.getName();
        String converterName = "converter." + name1 + "To" + name2 + "Converter";
        innerMap.put(id2, converterName);
      }
      CONVERTER_MAP.put(id1, innerMap);
    }
  }

  public ConverterHelper(String textInput1, String textInput2,
      int textType1, int textType2, String direction) {
    switch (direction) {
      case "down":
        inputString = textInput1;
        outputString = textInput2;
        inputType = textType1;
        outputType = textType2;
        break;
      case "up":
        inputString = textInput2;
        outputString = textInput1;
        inputType = textType2;
        outputType = textType1;
        break;
    }
  }

  public void convert() throws Exception {
    if (inputType == outputType) {
      outputString = inputString;
      return;
    }
    Converter converter = getConverter(inputType, outputType);
    outputString = converter.convert(inputString);
  }

  public String getOutputString() {
    return outputString;
  }

  @SuppressWarnings("unchecked")
  private Converter getConverter(
      int inputType, int outputType) throws Exception {
    String converterName = CONVERTER_MAP.get(inputType).get(outputType);
    return (Converter) Class.forName(converterName).newInstance();
  }
}
