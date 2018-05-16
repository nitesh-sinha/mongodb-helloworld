package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nitesinh on 8/20/17.
 */
public class Fruitpicker {
    static final Configuration config = new Configuration();

    public static void main(String[] args) {
        config.setClassForTemplateLoading(Fruitpicker.class, "/");

        Spark.get("/", (request, response) -> {
            // Freemarker process the template and writes the output html into this object
            StringWriter writer = new StringWriter();
            try {
                Template fruitTemplate = config.getTemplate("favfruit.ftl");
                Map<String, Object> fruitsMap = new HashMap<>();

                fruitsMap.put("fruits", Arrays.asList("banana", "apple", "mango", "grapes"));
                fruitTemplate.process(fruitsMap, writer);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return writer;
        });

        Spark.post("/favorite_fruit", ((request, response) -> {
            String selectedFruitfruit = request.queryParams("selectedFruit");

            if(selectedFruitfruit==null)
                return "Please pick a fruit!";
            else
                return "Wow...your favorite fruit is " + selectedFruitfruit;
        }));
    }
}
