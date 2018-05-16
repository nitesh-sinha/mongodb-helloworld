package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Sorts.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.util.Helpers.printJson;

/**
 * Created by nitesinh on 2/21/18.
 */
public class SortSkipLimitTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("sortSkipLimitTest");

        coll.drop();

        // Insert 100 docs
        for(int i=0; i<10; i++) {
            for (int j=0; j<10; j++)
                coll.insertOne(new Document()
                        .append("i", i)
                        .append("j", j));
        }


        Bson projection = fields(include("i", "j"),
                                 excludeId());

        // One way to define sort order
        // sort with i in asc and j in desc order
        // Bson sort = new Document("i", 1).append("j", -1);
        // Another way to define sorts using builder class(this one means sort using i first; for every i sort using j)
        // So output is [(0,9),(0,8)...(0,0)(1,9)(1,8)...(9,0)]
        Bson sort = orderBy(ascending("i"), descending("j"));


        // Use find with filter
        List<Document> allFilteredDocs = coll.find()
                .sort(sort)
                .skip(20) // skip first 20 docs from find output
                .limit(50) // limit output to only 50 docs
                .projection(projection)
                .into(new ArrayList<Document>());
        for(Document curDoc : allFilteredDocs)
            printJson(curDoc);
    }
}
