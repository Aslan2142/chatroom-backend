package com.aslan2142.chatroom.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/message")
@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService)
    {
        this.messageService = messageService;
    }

    @GetMapping(path = "{uuid}")
    public Message message(@PathVariable("uuid") String uuid)
    {
        return messageService.getMessage(uuid);
    }

    @GetMapping
    public List<Message> messages()
    {
        return messageService.getMessages();
    }

    @GetMapping(params = { "after" })
    public List<Message> messages(@RequestParam String after)
    {
        return messageService.getMessages(after);
    }

    @PostMapping
    public void messageAdd(@RequestBody Message message)
    {
        messageService.addMessage(message);
    }

    @PutMapping(path = "{uuid}")
    public void messageUpdate(@PathVariable("uuid") String uuid, @RequestBody Message updatedMessage)
    {
        messageService.updateMessage(uuid, updatedMessage);
    }

    @DeleteMapping(path = "{uuid}")
    public void messageRemove(@PathVariable("uuid") String uuid)
    {
        messageService.removeMessage(uuid);
    }

    @DeleteMapping
    public void messagesClear()
    {
        messageService.clearMessages();
    }

}
