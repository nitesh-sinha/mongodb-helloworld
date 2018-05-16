package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by nitesinh on 2/21/18.
 */
public class QueryWithProjection {
    // Projection is excluding fields from docs which are returned from db
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("queryWithProjectionTest");

        coll.drop();

        // Insert 10 docs each with two random integers
        for(int i=0; i<10; i++)
            coll.insertOne(new Document()
                    .append("x", new Random().nextInt(2))
                    .append("y", new Random().nextInt(100))
                    .append("i", i));

        // Create filter(x==0 && y>10 && y<90)
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        // One way of specifying a projection
        // Here 0=exclude and 1=include that field from the document
        // Bson projection = new Document("y", 1).append("i", 1).append("_id", 0);

        // Another way of specifying a projection
        // Exclude x and _id fields
        // Bson projection = Projections.exclude("x","_id");
        // Exclude _id field but include y,i fields
        Bson projection = fields(include("y", "i"),
                                 exclude("_id"));

        // Use find with filter
        List<Document> allFilteredDocs = coll.find(filter)
                                             .projection(projection)
                                             .into(new ArrayList<Document>());
        for(Document curDoc : allFilteredDocs)
            printJson(curDoc);
    }
}
