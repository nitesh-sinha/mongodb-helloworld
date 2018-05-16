package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by nitesinh on 8/10/17.
 */
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        Spark.get("/", (request, response) -> {
              return  "Hello world from Spark"; });
    }
}
