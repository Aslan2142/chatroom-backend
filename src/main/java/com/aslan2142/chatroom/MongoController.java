package com.aslan2142.chatroom;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoController {

    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;

    public static MongoCollection<Document> messageCollection;

    public static void init()
    {
        mongoClient = MongoClients.create("mongodb://username:password@127.0.0.1:27017/?authSource=admin");
        mongoDatabase = mongoClient.getDatabase("chatdatabase");

        messageCollection = mongoDatabase.getCollection("messages");
    }

}
