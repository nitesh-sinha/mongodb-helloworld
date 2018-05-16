package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSON;
import org.bson.BsonDocument;
import org.bson.Document;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Inorder to pass options to the client, use this clientoptions class and pass it to ServerAddress
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(50).build();
        // Instantiate a mongodb client with multiple mongodb servers in list
        // This is a heavy weight object. So, create a single instance of it for the app
        MongoClient client = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017)), options);
        // Another way to instantiate mongo client with URI address of server
        // MongoClient client = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        // These are lightweight and imutable objects
        MongoDatabase db = client.getDatabase("test");
        // To change a parameter of the db object use its "with" methods
        // which return a new instance of the database object; so make sure to save it
        db = db.withReadPreference(ReadPreference.secondary());

        // These are lightweight and imutable objects. MongoCollection is a generic class.
        MongoCollection<Document> coll = db.getCollection("test");
        // To get another collection say of BSon doc type which is typesafe, use this
        MongoCollection<BsonDocument> bsonColl = db.getCollection("test", BsonDocument.class);
    }
}
