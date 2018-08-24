package com.mongo.sapient;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.Iterator;

public class ConnectToDB {

    public static void main( String args[] ) {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("QADatabase");
        System.out.println("Database fetched is "+database.getName());

        // Accessing the database
         for (String name : database.listCollectionNames()) {
                 System.out.println(name);
              }

        // Retrieving a collection
        MongoCollection<Document> collection = database.getCollection("Company");
        System.out.println("Collection "+collection.toString()+" selected successfully");

        //Insert a collection
//        Document document = new Document("Company", "MongoDB").append("HQ", "Mongo");
//        collection.insertOne(document);

        //Update a collections
        collection.updateOne(Filters.eq("_id", "5b7fdb9c49763238640c48fc"), Updates.set("Company", "GenyMotion"));
        System.out.println("Document update successfully...");

        // Deleting the documents
        collection.deleteOne(Filters.eq("Company", "MongoDB"));
        System.out.println("Document deleted successfully...");

       // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;

        // Getting the iterator
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }

        // Retrieving a collection
        collection = database.getCollection("post");
        // Dropping a Collection
//        collection.drop();
//        System.out.println("Collection dropped successfully");
    }
}
