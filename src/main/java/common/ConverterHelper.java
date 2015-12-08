package common;

import converter.Converter;

import java.util.HashMap;

/**
 * Helper class which handles the core parts of converting between types.
 *
 * The Helper does mainly two things:
 *   1. Determine the direction of the conversion based on the inputs.
 *   2. Find the correct Converter based on the type names, instanciate the
 *     correct class by constructed name and call convert().
 */
public final class ConverterHelper {
  private String inputString;
  private String outputString;
  private int inputType;
  private int outputType;

  // A hashmap to store the already found converters, to avoid instanciate
  // same types of converters multiple times.
  private static HashMap<Integer, HashMap<Integer, Converter>> CONVERTER_MAP =
      new HashMap<Integer, HashMap<Integer, Converter>>();

  public ConverterHelper(String textInput1, String textInput2,
      int textType1, int textType2, String direction) {
    // Determine the convert direction and set proper input and output
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

  // Get the converter name based on the given types.
  @SuppressWarnings("unchecked")
  private Converter getConverter(
      int inputType, int outputType) throws Exception {
    if (findConverter(inputType, outputType)) {
      return CONVERTER_MAP.get(inputType).get(outputType);
    }
    String typeName1, typeName2;
    // TODO: this can be time consuming when the list becomes long,
    //   consider passing in the types based on the category (once we have that)
    for (ConvertableType type : ConvertableType.values()) {
      if (type.getValue() == inputType) {
        typeName1 = type.getName();
      }
      if (type.getValue() == outputType) {
        typeName2 = type.getName();
      }
    }
    String converterName =
        "converter." + typeName1 + "To" + typename2 + "Converter";
    Converter converter = (Converter) Class.forName(converterName).newInstance();
    saveConverter(inputType, outputType, converter);
  }

  private boolean findConverter(int inputType, int outputType) {
    return CONVERTER_MAP.hasKey(inputType) && CONVERTER_MAP.get(inputType).hasKey(outputType);
  }

  private void saveConverter(int inputType, int outputType, Converter converter) {
    HashMap<Integer, Converter> innerMap;
    if (CONVERTER_MAP.hasKey(inputType)) {
      innerMap = CONVERTER_MAP.get(inputType);
    } else {
      innerMap = new HashMap<Integer, Converter>();
    }
    innerMap.put(outputType, converter);
    CONVERTER_MAP.put(inputType, innerMap);
  }
}
