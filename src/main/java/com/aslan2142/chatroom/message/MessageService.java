package com.aslan2142.chatroom.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    public static MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    public Message getMessage(String uuid)
    {
        return messageRepository.getMessage(uuid);
    }

    public List<Message> getMessages()
    {
        return messageRepository.getMessages();
    }

    public List<Message> getMessages(String afterUuid)
    {
        if (afterUuid == null || afterUuid.equalsIgnoreCase("")) return messageRepository.getMessages();

        List<Message> messages = new ArrayList<>();

        boolean add = false;
        for (Message message : messageRepository.getMessages())
        {
            if (add) messages.add(message);
            if (message.getUuid().equals(afterUuid)) add = true;
        }

        return messages;
    }

    public void addMessage(Message message)
    {
        messageRepository.addMessage(message);
    }

    public void updateMessage(String uuid, Message updatedMessage)
    {
        messageRepository.updateMessage(uuid, updatedMessage);
    }

    public void removeMessage(String uuid)
    {
        messageRepository.removeMessage(uuid);
    }

    public void clearMessages()
    {
        messageRepository.clearMessages();
    }

}