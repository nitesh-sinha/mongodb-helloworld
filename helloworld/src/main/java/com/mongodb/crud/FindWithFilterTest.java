package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by nitesinh on 2/21/18.
 */
public class FindWithFilterTest  {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("findWithFilterTest");

        coll.drop();

        // Insert 10 docs each with two random integers
        for(int i=0; i<10; i++)
            coll.insertOne(new Document()
                                .append("x", new Random().nextInt(10))
                                .append("y", new Random().nextInt(100)));
        // One way of creating a filter(x==0 && y>10 && y<90)
        // Bson filter = new Document("x", 0)
        //                            .append("y", new Document("$gt", 10).append("$lt", 50));


        // Another way of creating a filter(x==0 && y>10 && y<90)
        Bson filter = and(eq("x", 0), gt("y", 10), lt("y", 90));

        // Use find with filter
        List<Document> allFilteredDocs = coll.find(filter).into(new ArrayList<Document>());
        for(Document curDoc : allFilteredDocs)
            printJson(curDoc);


        // Gets the count of filtered documents in the db
        System.out.print("Count of filtered objects: ");
        long count = coll.count(filter);
        System.out.println(count);
    }
}
