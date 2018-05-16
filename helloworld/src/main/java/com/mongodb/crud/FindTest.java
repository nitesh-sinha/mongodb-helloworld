package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.util.Helpers.printJson;

/**
 * Created by nitesinh on 2/21/18.
 */
public class FindTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> coll = db.getCollection("findTest");

        coll.drop();

        // Insert docs
        for(int i=0; i<10; i++)
            coll.insertOne(new Document("doc_number", i));

        // Gets first doc in db
        System.out.println("Find one");
        Document first = coll.find().first();
        printJson(first);

        // Use into() method of find to iterate over docs
        System.out.println("Find all with into: ");
        List<Document> allDocs = coll.find().into(new ArrayList<Document>());
        for(Document curDoc : allDocs)
            printJson(curDoc);


        // Uses mongo cursor to iterate over the docs
        System.out.println("Find all with iteration: ");
        MongoCursor<Document> cursor = coll.find().iterator();

        try {
            while(cursor.hasNext()) {
                printJson(cursor.next());
            }
        } finally {
            cursor.close();
        }

        // Gets the count of documents in the db
        System.out.print("Count: ");
        long count = coll.count();
        System.out.println(count);
    }
}
