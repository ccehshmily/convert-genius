package actions;

import static spark.Spark.*;

import java.util.HashMap;

import common.ConvertableType;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class ConvertGenius {
  private static final String LAYOUT_TEMPLATE = "templates/html_common.vtl";

  public static void main(String[] args) {
    staticFileLocation("/public");

    get("/", (request, response) ->
        {
          HashMap model = new HashMap();
          model.put("template", "templates/converter.vtl" );
          model.put("textInputValue1", "" );
          model.put("textInputValue2", "" );
          model.put("convertableTypes", ConvertableType.values());
          return new ModelAndView(model, LAYOUT_TEMPLATE);
        },
        new VelocityTemplateEngine());
  }
}
