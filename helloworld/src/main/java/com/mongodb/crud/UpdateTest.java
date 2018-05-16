package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by nitesinh on 2/21/18.
 */
public class UpdateTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("updateTest");

        coll.drop();

        // Insert 10 docs
        for(int i=0; i<10; i++) {
            coll.insertOne(new Document()
                        .append("_id", i)
                        .append("x", i)
                        .append("y", true));
        }

        // Whole doc is replaced(here y field is lost)
        // coll.replaceOne(Filters.eq("x", 5), new Document("x",20).append("updated",true));

        // One way to create update criteria
        // Doc is updated instead of getting replaced altogether(so y field is not lost; instead "updated" field is added to existing ones)
        // coll.updateOne(Filters.eq("x", 5), new Document("$set", new Document("x", 20).append("updated", true)));

        // Another way to create updates criteria
        coll.updateOne(Filters.eq("x", 5), Updates.combine(Updates.set("x", 20), Updates.set("updated", true)));

        // Upserting a record into the collection(i.e. insert if it does not exist)
        coll.updateOne(Filters.eq("x", 12), Updates.combine(Updates.set("_id", 12),
                                                                             Updates.set("x", 20),
                                                                            Updates.set("updated", true)),
                                                            new UpdateOptions().upsert(true));

        // Update x fields by 1 for all those docs whose x>=4
        coll.updateMany(Filters.gte("x", 4), Updates.inc("x", 1));

        // Use find with filter
        List<Document> allFilteredDocs = coll.find()
                .into(new ArrayList<Document>());
        for(Document curDoc : allFilteredDocs)
            printJson(curDoc);
    }
}
