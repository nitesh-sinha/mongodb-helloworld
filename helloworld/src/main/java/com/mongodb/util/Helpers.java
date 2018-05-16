package com.mongodb.util;

import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;

/**
 * Created by nitesinh on 2/21/18.
 */
public class Helpers {
    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(), new JsonWriterSettings(JsonMode.SHELL, false));
        new DocumentCodec().encode(jsonWriter, document, EncoderContext.builder().isEncodingCollectibleDocument(true).build());

        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }
}
