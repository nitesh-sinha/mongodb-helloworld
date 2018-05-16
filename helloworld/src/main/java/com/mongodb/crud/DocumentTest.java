package com.mongodb.crud;

import com.mongodb.DBObject;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by nitesinh on 2/21/18.
 */
public class DocumentTest {
    public static void main(String[] args) {
        Document document = new Document()
                .append("strKey", "Mondodb, hello")
                .append("int", 42)
                .append("long", 1L)
                .append("double", 1.1)
                .append("bool", false)
                .append("date", new Date())
                .append("objectId", new ObjectId())
                .append("null", null)
                .append("embeddedDoc", new Document("x", 0))
                .append("list", Arrays.asList(1,2,3)); // you can have list of lists or list of docs as per need


        // String str = document.getString("str");
        printJson(document);
    }

}
