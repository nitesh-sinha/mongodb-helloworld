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
public class DeleteTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("deleteTest");

        coll.drop();

        // Insert 10 docs
        for(int i=0; i<10; i++) {
            coll.insertOne(new Document()
                    .append("_id", i));
        }

        // deleteOne deletes the first occurence of the doc matching the filter criteria
        coll.deleteOne(Filters.eq("_id", 0));

        // coll.deleteMany(Filters.gt("_id", 4));

        // Use find with filter
        List<Document> allFilteredDocs = coll.find()
                .into(new ArrayList<Document>());
        for(Document curDoc : allFilteredDocs)
            printJson(curDoc);
    }
}
