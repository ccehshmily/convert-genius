package common;

import converter.Converter;
import exceptions.ConverterNotFoundException;
import exceptions.InvalidQueryParamsException;

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
      int textType1, int textType2, String direction) throws Exception {
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
      default:
        throw new InvalidQueryParamsException();
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

  // Get the converter name based on the given types. Construct from the type names.
  @SuppressWarnings("unchecked")
  private Converter getConverter(int inputType, int outputType) throws Exception {
    if (hasConverter(inputType, outputType)) {
      return CONVERTER_MAP.get(inputType).get(outputType);
    }
    String typeName1 = "", typeName2 = "";
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
    if (typeName1.isEmpty() || typeName2.isEmpty()) {
      throw new InvalidQueryParamsException();
    }
    String converterName = "converter." + typeName1 + "To" + typeName2 + "Converter";
    Converter converter;
    // Catch ClassNotFoundException when certain conversions are not implemented, throw
    // ConverterNotFoundException which can be handled later.
    try {
      converter = (Converter) Class.forName(converterName).newInstance();
    } catch (ClassNotFoundException e) {
      throw new ConverterNotFoundException(typeName1, typeName2);
    }
    saveConverter(inputType, outputType, converter);
    return converter;
  }

  private boolean hasConverter(int inputType, int outputType) {
    return CONVERTER_MAP.containsKey(inputType) && CONVERTER_MAP.get(inputType).containsKey(outputType);
  }

  private void saveConverter(int inputType, int outputType, Converter converter) {
    HashMap<Integer, Converter> innerMap;
    if (CONVERTER_MAP.containsKey(inputType)) {
      innerMap = CONVERTER_MAP.get(inputType);
    } else {
      innerMap = new HashMap<Integer, Converter>();
    }
    innerMap.put(outputType, converter);
    CONVERTER_MAP.put(inputType, innerMap);
  }
}
