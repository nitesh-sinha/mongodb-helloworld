package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nitesinh on 8/20/17.
 */
public class HelloWorldFreemarkerStyle {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        try {
            Template helloTemplate = config.getTemplate("hello.ftl");
            Map<String, Object> helloMap = new HashMap<>();
            StringWriter writer = new StringWriter();
            helloMap.put("name", "Nitesh");
            helloTemplate.process(helloMap, writer);
            System.out.println(writer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
