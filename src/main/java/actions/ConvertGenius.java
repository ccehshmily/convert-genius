package actions;

import static spark.Spark.*;

import common.ConvertableType;
import common.ConverterHelper;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;

public class ConvertGenius {
  private static final String LAYOUT_TEMPLATE = "templates/html_common.vtl";
  private static final String RES_FILES = "/public";

  public static void main(String[] args) {
    staticFileLocation(RES_FILES);
    setUpRoot();
    setUpConvert();
  }

  private static void setUpRoot() {
    get("/", (request, response) ->
        {
          HashMap model = new HashMap();
          model.put("template", "templates/converter.vtl");
          model.put("textInputValue1", "");
          model.put("textInputValue2", "");
          model.put("convertableTypes", ConvertableType.values());
          return new ModelAndView(model, LAYOUT_TEMPLATE);
        },
        new VelocityTemplateEngine());
  }

  private static void setUpConvert() {
    get("/convert", (request, response) ->
        {
          String textInput1 = request.queryParams("textInput1");
          String textInput2 = request.queryParams("textInput2");
          int textType1 = Integer.parseInt(request.queryParams("textType1"));
          int textType2 = Integer.parseInt(request.queryParams("textType2"));
          String direction = request.queryParams("direction");
          ConverterHelper converterHelper = new ConverterHelper(
              textInput1, textInput2, textType1, textType2, direction);
          try {
            converterHelper.convert();
          } catch (Exception e) {
            e.printStackTrace();
          }
          HashMap model = new HashMap();
          model.put("template", "templates/converter.vtl");
          model.put("textInputValue1", textInput1);
          model.put("textInputValue2", textInput2);
          model.put("textType1", textType1);
          model.put("textType2", textType2);
          model.put("convertableTypes", ConvertableType.values());
          switch (direction) {
            case "down":
              model.put("textInputValue2", converterHelper.getOutputString());
              break;
            case "up":
              model.put("textInputValue1", converterHelper.getOutputString());
              break;
          }
          return new ModelAndView(model, LAYOUT_TEMPLATE);
        },
        new VelocityTemplateEngine());
  }
}
