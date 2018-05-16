package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by nitesinh on 2/21/18.
 */
public class InsertTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("insertTest");

        coll.drop();

        Document nitesh = new Document("name", "Nitesh")
                                .append("age", 30)
                                .append("profession", "dreamer");

        Document kumar = new Document("name", "Kumar")
                .append("age", 25)
                .append("profession", "hacker");
        //coll.insertOne(nitesh);

        coll.insertMany(Arrays.asList(nitesh, kumar));
        // _id field added to document kumar by insertMany() is removed here
        kumar.remove("_id");
        // this insert adds a new unique _id field to kumar document
        // There will be two kumar documents with same name/age/profession but unique _id field.
        coll.insertOne(kumar);
    }
}
