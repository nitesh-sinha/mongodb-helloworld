package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nitesinh on 8/20/17.
 */
public class HelloWorldSparkFreemarkerStyle {
    static final Configuration config = new Configuration();

    public static void main(String[] args) {
        config.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        Spark.get("/", (request, response) -> {
            // Freemarker process the template and writes the output html into this object
            StringWriter writer = new StringWriter();
            try {
                Template helloTemplate = config.getTemplate("hello.ftl");
                Map<String, Object> helloMap = new HashMap<>();

                helloMap.put("name", "Foobar");
                helloTemplate.process(helloMap, writer);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return writer;
        });

        Spark.get("/echo/:thing", (request, response) -> {
            return request.params(":thing"); // To access the input sent in request
        });
    }
}
