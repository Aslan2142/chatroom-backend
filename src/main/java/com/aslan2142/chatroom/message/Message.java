package com.aslan2142.chatroom.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Message {

    private String uuid;
    private String author;
    private String text;
    private String file;

    public Message(
            @JsonProperty("author") String author,
            @JsonProperty("text") String text,
            @JsonProperty("file") String file
    )
    {
        this.uuid = UUID.randomUUID().toString();
        this.author = author;
        this.text = text;
        this.file = file;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getFile() { return file; }

}