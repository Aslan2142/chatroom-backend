package com.aslan2142.chatroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class})
@SpringBootApplication
public class ChatroomApplication {

	public static void main(String[] args) {
		MongoController.init();

		SpringApplication.run(ChatroomApplication.class, args);
	}

}
