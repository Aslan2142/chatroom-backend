package com.aslan2142.chatroom.message;

import com.aslan2142.chatroom.MongoController;
import org.bson.Document;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MessageRepository {

    private List<Message> messages = new ArrayList<>();

    public MessageRepository()
    {
        MongoController.messageCollection.find().forEach(doc -> {
            Message message = new Message(doc.getString("author"), doc.getString("text"), doc.getString("file"));
            message.setUuid(doc.getString("uuid"));
            messages.add(message);
        });
    }

    public Message getMessage(String uuid)
    {
        for (Message message : messages)
        {
            if (message.getUuid().equals(uuid)) return message;
        }

        return null;
    }

    public List<Message> getMessages()
    {
        return messages;
    }

    public void addMessage(Message message)
    {
        messages.add(message);

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("uuid", message.getUuid());
        stringObjectMap.put("author", message.getAuthor());
        stringObjectMap.put("text", message.getText());
        stringObjectMap.put("file", message.getFile());
        MongoController.messageCollection.insertOne(new Document(stringObjectMap));
    }

    public void updateMessage(String uuid, Message updatedMessage)
    {
        updatedMessage.setUuid(uuid);

        for (int i = 0; i < messages.size(); i++)
        {
            if (messages.get(i).getUuid().equals(uuid))
            {
                messages.set(i, updatedMessage);

                Map<String, Object> stringObjectMap = new HashMap<>();
                stringObjectMap.put("author", updatedMessage.getAuthor());
                stringObjectMap.put("text", updatedMessage.getText());
                stringObjectMap.put("file", updatedMessage.getFile());
                MongoController.messageCollection.updateOne(new Document("uuid", uuid), new Document("$set", new Document(stringObjectMap)));

                break;
            }
        }
    }

    public void removeMessage(String uuid)
    {
        for (int i = 0; i < messages.size(); i++)
        {
            if (messages.get(i).getUuid().equals(uuid))
            {
                messages.remove(i);
                break;
            }
        }

        MongoController.messageCollection.deleteOne(new Document("uuid", uuid));
    }

    public void clearMessages()
    {
        messages.clear();

        MongoController.messageCollection.deleteMany(new Document());
    }

}